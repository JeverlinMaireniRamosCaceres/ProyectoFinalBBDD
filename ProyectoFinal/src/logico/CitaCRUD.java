package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CitaCRUD {

	// INSERTAR
	public static boolean insertarCita(Cita cita) {
	    String sql = "INSERT INTO Cita (idCita, fecha, hora, motivo, idPaciente, idMedico) VALUES (?, ?, ?, ?, ?, ?)";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, cita.getIdCita());
	        stmt.setDate(2, new java.sql.Date(cita.getFecha().getTime()));
	        stmt.setTime(3, cita.getHora()); 
	        stmt.setString(4, cita.getMotivo());
	        stmt.setString(5, cita.getPaciente().getIdPersona());
	        stmt.setString(6, cita.getMedico().getIdPersona());
	        
	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;
	        
	    } catch (SQLException e) {
	        System.err.println("Error al insertar cita: " + e.getMessage());
	        return false;
	    }
	}
	
	// GENERAR SECUENCIA CODIGO CITA
	public static String generarCodigoCita() {
	    String sql = "SELECT MAX(CAST(SUBSTRING(idCita, 3, LEN(idCita)) AS INT)) AS ultimo_num FROM Cita WHERE idCita LIKE 'C-%'";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        
	        int nuevoNumero = 1;
	        if (rs.next() && rs.getInt("ultimo_num") > 0) {
	            nuevoNumero = rs.getInt("ultimo_num") + 1;
	        }
	        
	        return "C-" + nuevoNumero;
	        
	    } catch (SQLException e) {
	        System.err.println("Error al generar código de cita: " + e.getMessage());
	        return null;
	    }
	}


	
}
