package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Partido;

/**
 * DAO que gestiona las operaciones CRUD de la tabla partidos en la base de datos.
 */
public class PartidoDAO {
    
    /**
     * Inserta un nuevo partido en la base de datos.
     * @param txtA nombre del partido
     * @param txtB cantidad de votos
     */
    public static void crearPartido(String txtA, int txtB){
        try (Connection conn = Conexion.conectar()){
            String sql = "INSERT INTO partidos(nombre,votos) VALUES (?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtA);
            ps.setInt(2, txtB);
            ps.executeUpdate();
            System.out.println("Creado correctamente.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Obtiene todos los partidos almacenados en la base de datos.
     * @return lista de partidos, vacia si no hay ninguno o si ocurre un error
     */
    public static List<Partido> mostrarPartidos(){
        List<Partido> lista = new ArrayList<>();

        try (Connection conn = Conexion.conectar()){
            String sql = "SELECT * FROM partidos;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Construye un objeto Partido por cada fila del resultado
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int votos = rs.getInt("votos");
                lista.add(new Partido(id, nombre, votos));
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lista; // retorna lista vacia si ocurrii un error
    }
    
    /**
     * Actualiza el nombre y los votos de un partido existente.
     * @param id identificador del partido a actualizar
     * @param nombre nuevo nombre
     * @param votos nueva cantidad de votos
     */
    public static void actualizarPartido(int id, String nombre, int votos) {
        
        try (Connection conn = Conexion.conectar()){
            String sql = "UPDATE partidos SET nombre = ?, votos = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, votos);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Elimina un partido de la base de datos por su identificador.
     * @param id identificador del partido a eliminar
     */
    public static void eliminarPartido(int id){
        try (Connection conn = Conexion.conectar()){
            String sql = "DELETE FROM partidos WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Eliminado correctamente");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}