package servicios;

import dao.DivisionVotosDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.DivisionVotos;
import modelo.Partido;
import modelo.ResultadoVotos;

/**
 * Servicio que implementa el algoritmo D'Hondt para la reparticion de cargos
 * entre partidos politicos segin su cantidad de votos.
 */
public class DhondtService {
    
    /**
     * Filtra los partidos que superan el umbral minimo de votos requerido.
     * @param partidos lista completa de partidos
     * @param porcentaje porcentaje minimo como String, ej: "5%"
     * @return lista de partidos que superan el umbral, vacia si el total de votos es 0
     */
    public static List<Partido> filtrarPartidos(List<Partido> partidos, String porcentaje){
        int totalVotos = 0;
        for (Partido p : partidos) {
            totalVotos += p.getVotos();
        }
        if (totalVotos == 0) return new ArrayList<>();
        
        String seleccion = porcentaje.replace("%", "");
        // convierte "5%" a 0.05 para comparar con el porcentaje de cada partido
        double umbral = Double.parseDouble(seleccion) / 100.0;
        
        List<Partido> filtrados = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getVotos() / (double) totalVotos >= umbral) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }
    
    /**
     * Calcula todos los cocientes D'Hondt para los partidos filtrados.
     * Por cada partido divide sus votos entre 1, 2, 3... hasta N cargos,
     * ordena todos los cocientes de mayor a menor y asigna un numero de orden global.
     * @param partidos partidos que pasaron el filtro de porcentaje
     * @param totalVotos total de votos de todos los partidos, usado para calcular porcentajes
     * @param cargos cantidad de cargos a repartir
     * @return lista de cocientes ordenada de mayor a menor con orden global asignado
     */
    public static List<ResultadoVotos> calcularCocientes(List<Partido> partidos, int totalVotos, int cargos) {
        List<ResultadoVotos> cocientes = new ArrayList<>();
        for (Partido p : partidos) {
            for (int i = 1; i <= cargos; i++) {
            // Cada fila representa votos/i para el partido p
                cocientes.add(new ResultadoVotos(
                    p.getId(),
                    p.getVotos() / i,   // cociente D'Hondt
                    0,                  // orden se asigna despues del sort
                    i,                  // indice divisor
                    (p.getVotos() / (double) totalVotos) * 100.0, // porcentaje del partido
                    totalVotos
                ));
            }
        }
        
        // Ordena de mayor a menor cociente para determinar quien ocupa cada cargo
        cocientes.sort((a, b) -> Integer.compare(b.getCantidadVotos(), a.getCantidadVotos()));
        
        // Verifica si el cargo N y el cargo N+1 tienen el mismo cociente (empate en el corte)
        if (cocientes.size() > cargos) {
            int ultimoCargo = cocientes.get(cargos - 1).getCantidadVotos();
            int siguiente   = cocientes.get(cargos).getCantidadVotos();
            if (ultimoCargo == siguiente) {
                JOptionPane.showMessageDialog(null,
                    "Hay un empate en el ultimo cargo. El resultado puede no ser definitivo.",
                    "Empate detectado", JOptionPane.WARNING_MESSAGE);
            }
        }
        // Asigna el numero de orden global a cada cociente segun su posicion
        for (int i = 0; i < cocientes.size(); i++) {
            cocientes.get(i).setOrden(i + 1);
        }
        return cocientes;
    }
    
    /**
     * Obtiene los primeros N cocientes que ocupan cargo.
     * @param cocientes lista completa de cocientes ordenados
     * @param cargos cantidad de cargos a repartir
     * @return sublista con los cocientes ganadores
     */
    public static List<ResultadoVotos> obtenerResultados(List<ResultadoVotos> cocientes, int cargos) {
        // subList toma los primeros N elementos, o todos si hay menos que N
        return new ArrayList<>(cocientes.subList(0, Math.min(cargos, cocientes.size())));
    }
    
    /**
     * Arma y guarda la tabla completa de division de votos para el informe correspondiente.
     * Incluye todos los partidos (incluso los que no pasaron el filtro) con todos sus
     * cocientes, marcando cuales entraron al porcentaje y cuales ocuparon un cargo.
     * @param cargos cantidad de cargos
     * @param todosLosPartidos lista completa de partidos sin filtrar
     * @param partidos lista de partidos que pasaron el filtro
     * @param cocientes lista completa de cocientes con ordenes asignados
     * @param resultados lista de cocientes que ocuparon cargo
     */
    public static void guardarDivisionVotos(int cargos, List<Partido> todosLosPartidos,
            List<Partido> partidos, List<ResultadoVotos> cocientes, List<ResultadoVotos> resultados) {

        // Mapa de orden global: idPartido -> indice -> nroOrden
        // Permite buscar el orden de cualquier cociente en O(1)
        Map<Integer, Map<Integer, Integer>> ordenGlobal = new HashMap<>();
        for (ResultadoVotos r : cocientes) {
            // si el partido aun no tiene entradas en el mapa, crea un HashMap vacio para il
            // luego asocia el indice divisor con su numero de orden global
            ordenGlobal
                    .computeIfAbsent(r.getFk_idPartido(), k -> new HashMap<>()) // mapa interno del partido
                    .put(r.getIndice(), r.getOrden()); // guarda: indice -> orden
        }

        List<DivisionVotos> divisiones = new ArrayList<>();
        for (Partido p : todosLosPartidos) {
            boolean entra = partidos.contains(p); // true si supero el umbral de porcentaje
            for (int i = 1; i <= cargos; i++) {
                double votosPorCargo = p.getVotos() / (double) i;
                final int ix = i; // copia de i como constante necesaria para usar en la lambda
                // Verifica si este cociente especifico ocupo un carg. Si hay coincidencia, true
                boolean ocupa = resultados.stream()
                    .anyMatch(r -> r.getFk_idPartido() == p.getId() && r.getIndice() == ix);
                int orden = entra && ordenGlobal.containsKey(p.getId())
                    ? ordenGlobal.get(p.getId()).getOrDefault(i, 0)
                    : 0;
                divisiones.add(new DivisionVotos(p.getId(), orden, votosPorCargo, i, entra, ocupa));
            }
        }
        DivisionVotosDAO.insertarDivisionVotos(divisiones);
    }
    
}