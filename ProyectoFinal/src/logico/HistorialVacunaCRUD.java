package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistorialVacunaCRUD {

	// INSERTAR HISTORIAL VACUNA
	public static boolean registrarHistorialVacuna(String idHistorial, java.util.Date fechaAplicacion, String idVacuna, String idPaciente) {
	    String sql = "INSERT INTO Historial_Vacuna (idHistorialVacuna, fechaAplicacion, idVacuna, idPaciente) VALUES (?, ?, ?, ?)";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, idHistorial);
	        stmt.setDate(2, new java.sql.Date(fechaAplicacion.getTime()));
	        stmt.setString(3, idVacuna);
	        stmt.setString(4, idPaciente);
	        
	        int filas = stmt.executeUpdate();
	        return filas > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	// GENERAR CODIGO HISTORIAL VACUNA
	public static String generarCodigoHistorialVacuna() {
	    String sql = "SELECT MAX(CAST(SUBSTRING(idHistorialVacuna, 4, LEN(idHistorialVacuna)) AS INT)) AS ultimo_num FROM Historial_Vacuna WHERE idHistorialVacuna LIKE 'HV-%'";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        
	        int nuevoNumero = 1;
	        if (rs.next() && rs.getInt("ultimo_num") > 0) {
	            nuevoNumero = rs.getInt("ultimo_num") + 1;
	        }
	        
	        return "HV-" + nuevoNumero;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
