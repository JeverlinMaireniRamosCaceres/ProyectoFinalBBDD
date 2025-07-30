package logico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerificarLogin {

	public static Usuario obtenerUsuario(String nombreUsuario, String contrasena) {
	    
	    String sql = "SELECT u.idUsuario, u.nombre, u.contrasenia, u.idRol " +
                "FROM Usuario u " +
                "WHERE u.nombre = ? AND u.contrasenia = ?";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, nombreUsuario);
	        stmt.setString(2, contrasena);

	        ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario user = new Usuario(
                    rs.getString("idUsuario"),
                    rs.getString("nombre"),
                    rs.getString("contrasenia"),
                    rs.getInt("idRol") // ahora con int
                );
                return user;
            }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
}
