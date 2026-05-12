package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO que agrupa operaciones que afectan multiples tablas a la vez.
 */
public class SistemaDAO {
    
    /**
     * Elimina todos los datos de las tablas de resultados y partidos en una
     * sola transaccion. Si alguna operacion falla, ningun cambio se aplica.
     */
    public static void eliminarDatos() {
        try (Connection conn = Conexion.conectar();
             Statement st = conn.createStatement()) {
            conn.setAutoCommit(false); // inicia la transaccion manualmente

            st.executeUpdate("DELETE FROM divisionvotos");
            st.executeUpdate("DELETE FROM resultadovotos");
            st.executeUpdate("DELETE FROM partidos");

            conn.commit(); // confirma los cambios solo si todo salio bien
        } catch (SQLException e) {
            System.out.println("Error al eliminar datos: " + e);
        }
    }
}