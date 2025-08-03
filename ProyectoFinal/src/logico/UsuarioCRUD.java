package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UsuarioCRUD {
    
	// INSERT
	public static boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (idUsuario, nombre, contrasenia, idRol) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = PruebaConexionBBDD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getCodigo());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getContrasena());
            stmt.setInt(4, usuario.getRol());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }
	
	// BUSCAR NUMERO SECUENCIA IDUSUARIO
	public static String generarCodigoUsuario() {
	    String sql = "SELECT MAX(CAST(SUBSTRING(idUsuario, 3, LEN(idUsuario)) AS INT)) AS ultimo_num FROM Usuario WHERE idUsuario LIKE 'U-%'";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        
	        int nuevoNumero = 1;
	        if (rs.next() && rs.getInt("ultimo_num") > 0) {
	            nuevoNumero = rs.getInt("ultimo_num") + 1;
	        }
	        
	        return "U-" + nuevoNumero;
	        
	    } catch (SQLException e) {
	        System.err.println("Error al generar código de usuario: " + e.getMessage());
	        return null;
	    }
	}
	
	// UPDATE
	public static boolean actualizarUsuario(Usuario usuario) {
	    String sql = "UPDATE Usuario SET nombre = ?, contrasenia = ?, idRol = ? WHERE idUsuario = ?";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, usuario.getNombre());
	        stmt.setString(2, usuario.getContrasena());
	        stmt.setInt(3, usuario.getRol()); 
	        stmt.setString(4, usuario.getCodigo());
	        
	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + e.getMessage(), 
	                                    "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	}
    
    // Obtener el id del rol por el nombre del rol
    public static int obtenerIdRol(String nombreRol) {
        String sql = "SELECT idRol FROM Rol WHERE nombre = ?";
        
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombreRol);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("idRol");
            }
            return -1;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

	
	
}
