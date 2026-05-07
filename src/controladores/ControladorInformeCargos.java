package controladores;

import dao.Conexion;
import dao.ResultadoVotosDAO;
import modelo.Partido;
import modelo.ResultadoVotos;
import servicios.DhondtService;
import servicios.PartidoService;
import vista.paneles.InformeCargos;
import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import vista.componentes.Estilo;

/**
 * Controlador que gestiona la generacion del informe de reparticion de cargos.
 * Coordina el flujo completo del algoritmo D'Hondt y luego genera y embebe
 * el informe JasperReports dentro del mismo panel en un hilo separado
 * para evitar bloquear la interfaz grafica.
 */
public class ControladorInformeCargos {

    private final InformeCargos vista;

    /** Ruta al archivo fuente del informe dentro del classpath. */
    private static final String RUTA_JRXML = "/informes/informeCargos.jrxml";

    /** Opciones de porcentaje minimo disponibles para filtrar partidos. */
    private static final String[] OPCIONES_PORCENTAJE = java.util.stream.IntStream.rangeClosed(1, 100)
    .mapToObj(i -> i + "%")
    .toArray(String[]::new);

    /**
     * Inicializa el controlador, carga las opciones del combo y registra los eventos.
     * @param vista panel del informe de cargos
     */
    public ControladorInformeCargos(InformeCargos vista) {
        this.vista = vista;
        cargarComboPorcentaje();
        iniciarEventos();
    }

    /**
     * Popula el combo de porcentaje con las opciones predefinidas.
     */
    private void cargarComboPorcentaje() {
        vista.getCmbPorcentaje().removeAllItems();
        for (String opcion : OPCIONES_PORCENTAJE) {
            vista.getCmbPorcentaje().addItem(opcion);
        }
    }

    /**
     * Registra los listeners de los componentes interactivos del panel.
     */
    private void iniciarEventos() {
        vista.getBtnAceptar().addActionListener(e -> calcular());
        Estilo.configurarTeclado(vista.getTxtCantCargos());
        Estilo.configurarTeclado(vista.getCmbPorcentaje());
        Estilo.configurarTeclado(vista.getBtnAceptar());
    }

    /**
     * Valida los datos, ejecuta el algoritmo D'Hondt, persiste los resultados
     * y genera el informe en un hilo separado para no bloquear la UI.
     */
    private void calcular() {
        int cargos;
        try {
            cargos = parsearCargos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista,
                "Ingrese un numero valido de cargos.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cargos <= 0) {
            JOptionPane.showMessageDialog(vista,
                "La cantidad de cargos debe ser mayor a 0.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String porcentaje = (String) vista.getCmbPorcentaje().getSelectedItem();

        List<Partido> todos = PartidoService.obtenerPartidos();
        if (todos.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                "No hay partidos cargados en el sistema.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int totalVotos = calcularTotalVotos(todos);

        List<Partido> filtrados = DhondtService.filtrarPartidos(todos, porcentaje);
        if (filtrados.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                "Ningun partido supera el porcentaje minimo seleccionado.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Calculo D'Hondt
        List<ResultadoVotos> cocientes = DhondtService.calcularCocientes(filtrados, totalVotos, cargos);
        List<ResultadoVotos> resultados = DhondtService.obtenerResultados(cocientes, cargos);

        // Persistencia
        ResultadoVotosDAO.insertarResultados(resultados);
        DhondtService.guardarDivisionVotos(cargos, todos, filtrados, cocientes, resultados);

        // Deshabilita el boton mientras genera el informe
        vista.getBtnAceptar().setEnabled(false);

        // Genera el informe en un hilo separado para no bloquear la UI
        new SwingWorker<JasperPrint, Void>() {

            /**
             * Se ejecuta en un hilo separado: compila y llena el informe.
             * @return JasperPrint listo para visualizar
             * @throws Exception si ocurre un error de compilacion o llenado
             */
            @Override
            protected JasperPrint doInBackground() throws Exception {
                return generarInforme();
            }

            /**
             * Se ejecuta en el hilo de Swing cuando doInBackground termina:
             * embebe el informe en el panel o muestra el error si fallo.
             */
            @Override
            protected void done() {
                try {
                    JasperPrint print = get();
                    vista.mostrarInforme(print);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista,
                        "Error al generar el informe: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                    vista.getBtnAceptar().setEnabled(true);
                }
            }
        }.execute();
    }

    /**
     * Compila el archivo .jrxml y lo llena con los datos de la base de datos.
     * Usa la conexion JDBC de UCanAccess directamente, igual que el resto del sistema.
     * @return JasperPrint listo para ser visualizado
     * @throws Exception si ocurre un error de compilacion, conexion o llenado
     */
    private JasperPrint generarInforme() throws Exception {
        Connection conn = Conexion.conectar();
        JasperReport reporte = JasperCompileManager.compileReport(
            getClass().getResourceAsStream(RUTA_JRXML)
        );
        return JasperFillManager.fillReport(reporte, null, conn);
    }

    /**
     * Parsea y retorna la cantidad de cargos ingresada en el campo de texto.
     * @return cantidad de cargos como entero
     * @throws NumberFormatException si el valor ingresado no es un numero valido
     */
    private int parsearCargos() {
        return Integer.parseInt(vista.getTxtCantCargos().getText().trim());
    }

    /**
     * Calcula la suma total de votos de todos los partidos.
     * @param partidos lista completa de partidos
     * @return total de votos, o 0 si la lista esta vacia
     */
    private int calcularTotalVotos(List<Partido> partidos) {
        int total = 0;
        for (Partido p : partidos) {
            total += p.getVotos();
        }
        return total;
    }
}