package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnfermedadCRUD {

	// INSERT
	public static boolean insertarEnfermedad(Enfermedad e) {
	    String sql = "INSERT INTO Enfermedad (idEnfermedad, nombre, sintomas, idTipoEnfermedad) VALUES (?, ?, ?, ?)";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, e.getIdEnfermedad());
	        stmt.setString(2, e.getNombre());
	        stmt.setString(3, e.getSintomas());
	        stmt.setInt(4, e.getTipo());

	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (SQLException ex) {
	        System.err.println("Error al insertar enfermedad: " + ex.getMessage());
	        return false;
	    }
	}

    
    // BUSCAR NUMERO SECUENCIA CODIGO
    public static String generarCodigoEnfermedad() {
        String sql = "SELECT MAX(CAST(SUBSTRING(idEnfermedad, 3, LEN(idEnfermedad)) AS INT)) AS ultimo_num FROM Enfermedad WHERE idEnfermedad LIKE 'E-%'";
        
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            int nuevoNumero = 1;
            if (rs.next() && rs.getInt("ultimo_num") > 0) {
                nuevoNumero = rs.getInt("ultimo_num") + 1;
            }
            
            return "E-" + nuevoNumero;
            
        } catch (SQLException e) {
            System.err.println("Error al generar código de enfermedad: " + e.getMessage());
            return null;
        }
    }
    
    public static ArrayList<Enfermedad> obtenerEnfermedades() {
        ArrayList<Enfermedad> lista = new ArrayList<>();
        String sql = "SELECT e.idEnfermedad, e.nombre, e.sintomas, e.idTipoEnfermedad, te.nombre AS tipo " +
                     "FROM Enfermedad e " +
                     "JOIN Tipo_Enfermedad te ON e.idTipoEnfermedad = te.idTipoEnfermedad";

        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Enfermedad enf = new Enfermedad();
                enf.setIdEnfermedad(rs.getString("idEnfermedad"));
                enf.setNombre(rs.getString("nombre"));
                enf.setSintomas(rs.getString("sintomas"));
                enf.setTipo(rs.getInt("idTipoEnfermedad"));     // ID numérico
                enf.setTipoNombre(rs.getString("tipo"));        // Nombre del tipo
                lista.add(enf);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener enfermedades: " + e.getMessage());
        }

        return lista;
    }

    public static Enfermedad buscarEnfermedadPorCodigo(String id) {
        String sql = "SELECT e.idEnfermedad, e.nombre, e.sintomas, e.idTipoEnfermedad, te.nombre AS tipo " +
                     "FROM Enfermedad e " +
                     "JOIN Tipo_Enfermedad te ON e.idTipoEnfermedad = te.idTipoEnfermedad " +
                     "WHERE e.idEnfermedad = ?";

        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Enfermedad enf = new Enfermedad();
                enf.setIdEnfermedad(rs.getString("idEnfermedad"));
                enf.setNombre(rs.getString("nombre"));
                enf.setSintomas(rs.getString("sintomas"));
                enf.setTipo(rs.getInt("idTipoEnfermedad"));
                enf.setTipoNombre(rs.getString("tipo"));
                return enf;
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar enfermedad por código: " + e.getMessage());
        }

        return null; // si no se encontró
    }


}
