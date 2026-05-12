package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.ResultadoVotos;

/**
 * DAO que gestiona la persistencia de los resultados del calculo D'Hondt
 * en la tabla ResultadoVotos de la base de datos.
 */
public class ResultadoVotosDAO {
    
    public static void insertarResultados(List<ResultadoVotos> resultados) {
        String sql = "INSERT INTO ResultadoVotos (fk_idPartido, cantidadVotos, orden, indice, porcentaje, totalVotos) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Limpia los resultados del calculo anterior antes de insertar los nuevos
            conn.createStatement().executeUpdate("DELETE FROM resultadovotos");

            for (ResultadoVotos r : resultados) {
                ps.setInt(1, r.getFk_idPartido());  // referencia al partido
                ps.setInt(2, r.getCantidadVotos()); // cociente D'Hondt (votos / indice)
                ps.setInt(3, r.getOrden());         // posicion en el ranking global
                ps.setInt(4, r.getIndice());        // divisor usado (1, 2, 3...)
                ps.setDouble(5, r.getPorcentaje()); // porcentaje del partido sobre el total
                ps.setInt(6, r.getTotalVotos());    // total de votos de todos los partidos
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar resultados: " + e);
        }
    }
}