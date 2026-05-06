package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexion a la base de datos Access mediante el driver UCanAccess.
 */
public class Conexion {
    
    /**
     * Establece y retorna una conexion a la base de datos Access.
     * @return conexion activa, o null si ocurre un error
     */
    public static Connection conectar() {
        try {
            //String url = "jdbc:ucanaccess://C:/Users/sheyla/Desktop/votacion.accdb";
            String url = "jdbc:ucanaccess://C:/Users/cielo/Desktop/votacion.accdb";
            Connection conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            System.out.println("Error de conexiin " + e);
            return null;
        }
    }
}