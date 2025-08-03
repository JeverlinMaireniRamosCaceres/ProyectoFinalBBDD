package logico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

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
	
	// SELECCIONAR DESDE LA BDD
	public static ArrayList<Cita> obtenerCitasDesdeBDD() {
	    ArrayList<Cita> citas = new ArrayList<>();
	    String sql = "SELECT * FROM Cita";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            String idCita = rs.getString("idCita");
	            Date fecha = rs.getDate("fecha");
	            Time hora = rs.getTime("hora");
	            String motivo = rs.getString("motivo");
	            String idPaciente = rs.getString("idPaciente");
	            String idMedico = rs.getString("idMedico");

	            // se busca el paciente y el medico
	            Paciente paciente = ClinicaMedica.getInstance().buscarPacienteByIdBDD(idPaciente);
	            Medico medico = ClinicaMedica.getInstance().buscarMedicoById(idMedico);

	            Cita cita = new Cita(idCita, medico, fecha, hora, motivo, paciente);
	            citas.add(cita);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return citas;
	}

	// UPDATE
	public static boolean actualizarCitaBDD(Cita cita) {
	    String sql = "UPDATE Cita SET fecha = ?, hora = ?, motivo = ?, idMedico = ?, idPaciente = ? WHERE idCita = ?";
	    
	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setDate(1, new java.sql.Date(cita.getFecha().getTime()));
	        stmt.setTime(2, cita.getHora());
	        stmt.setString(3, cita.getMotivo());
	        stmt.setString(4, cita.getMedico().getIdPersona());
	        stmt.setString(5, cita.getPaciente().getIdPersona());
	        stmt.setString(6, cita.getIdCita());
	        
	        return stmt.executeUpdate() > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
}
