package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistorialMedicoCRUD {

	// GENERAR SECUENCIA DEL CODIGO DEL HISTORIAL MEDICO
	public static String generarCodigoHistorialMedico() {
	    String sql = "SELECT MAX(CAST(SUBSTRING(idHistorialMedico, 4, LEN(idHistorialMedico)) AS INT)) AS ultimo_num " +
	                 "FROM Historial_Medico WHERE idHistorialMedico LIKE 'HM-%'";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        
	        int nuevoNumero = 1;
	        if (rs.next() && !rs.wasNull()) {  // Verifica si hay registros
	            nuevoNumero = rs.getInt("ultimo_num") + 1;
	        }
	        
	        return "HM-" + nuevoNumero;
	        
	    } catch (SQLException e) {
	        System.err.println("Error al generar código de historial médico: " + e.getMessage());
	        return null;
	    }
	}
    
	public static boolean agregarConsultaAHistorial(String idConsulta, String idPaciente, String motivoImportancia) {
	    String sql = "INSERT INTO Historial_Medico (idHistorialMedico, idConsulta, fecha, idPaciente, motivoImportancia) " +
	                 "VALUES (?, ?, GETDATE(), ?, ?)";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        // Generar código secuencial
	        String idHistorial = generarCodigoHistorialMedico();
	        if (idHistorial == null) {
	            return false;  // Fallo al generar código
	        }
	        
	        stmt.setString(1, idHistorial);
	        stmt.setString(2, idConsulta);
	        stmt.setString(3, idPaciente);
	        stmt.setString(4, motivoImportancia);
	        
	        return stmt.executeUpdate() > 0;
	        
	    } catch (SQLException e) {
	        System.err.println("Error al agregar al historial médico: " + e.getMessage());
	        return false;
	    }
	}
	
}
