package dao;

import modelo.DivisionVotos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO que gestiona la persistencia de la tabla completa de division de votos,
 * usada para generar el informe de division de votos.
 */
public class DivisionVotosDAO {
    
    /**
     * Reemplaza los datos anteriores e inserta la nueva tabla de division de votos.
     * Incluye todos los partidos y todos sus cocientes, marcando cuales ocuparon cargo.
     * @param divisiones lista completa de filas de division de votos
     */
    public static void insertarDivisionVotos(List<DivisionVotos> divisiones) {
        String sql = "INSERT INTO divisionvotos (fk_idPartido, orden, votosPorCargo, indice, entraPorcentaje, ocupaCargo) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.createStatement().executeUpdate("DELETE FROM divisionvotos");

            for (DivisionVotos d : divisiones) {
                ps.setInt(1, d.getFk_idPartido());      // referencia al partido
                ps.setInt(2, d.getOrden());             // orden global, 0 si no entro al ranking
                ps.setDouble(3, d.getVotosPorCargo());  // cociente: votos / indice
                ps.setInt(4, d.getIndice());            // divisor usado (1, 2, 3...)
                ps.setBoolean(5, d.isEntraPorcentaje()); // si supero el umbral de porcentaje
                ps.setBoolean(6, d.isOcupaCargo());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar divisiin de votos: " + e);
        }
    }
    
}