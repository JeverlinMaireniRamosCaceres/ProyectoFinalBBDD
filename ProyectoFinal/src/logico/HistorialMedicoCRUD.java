package logico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	
	/*public static ArrayList<Consulta> obtenerHistorialPorPacienteBDD(String idPaciente) {
	    ArrayList<Consulta> consultas = new ArrayList<>();
	    
	    // Consulta SQL optimizada para obtener todos los datos necesarios
	    String sql = "SELECT c.idConsulta, c.fecha, c.diagnostico, c.indicacion, c.importante, "
	               + "m.idMedico, m.idPersona, m.exequatur, m.idUsuario, "
	               + "per.nombre AS nombreMedico, per.apellido AS apellidoMedico, per.cedula, "
	               + "per.telefono, per.direccion, per.fechaNacimiento, per.sexo, "
	               + "p.idPaciente, per_pac.nombre AS nombrePaciente, per_pac.apellido AS apellidoPaciente "
	               + "FROM Consulta c "
	               + "JOIN Medico m ON c.idMedico = m.idMedico "
	               + "JOIN Persona per ON m.idPersona = per.idPersona "
	               + "JOIN Paciente p ON c.idPaciente = p.idPaciente "
	               + "JOIN Persona per_pac ON p.idPersona = per_pac.idPersona "
	               + "WHERE c.idPaciente = ? "
	               + "ORDER BY c.fecha DESC";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, idPaciente);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            // 1. Crear objeto Persona para el médico
	            String idPersonaMedico = rs.getString("idPersona");
	            
	            // 2. Crear objeto Medico con constructor completo
	            Medico medico = new Medico(
	                idPersonaMedico,
	                rs.getString("cedula"),
	                rs.getString("nombreMedico"),
	                rs.getString("apellidoMedico"),
	                rs.getString("telefono"),
	                rs.getString("direccion"),
	                rs.getDate("fechaNacimiento"),
	                rs.getString("sexo"),
	                rs.getInt("exequatur")
	            );
	            medico.setIdPersona(rs.getString("idMedico"));
	            
	            // 3. Crear objeto Paciente con datos mínimos
	            Paciente paciente = new Paciente(
	                rs.getString("idPaciente"), // idPersona
	                "", // cedula (no disponible en resultset)
	                rs.getString("nombrePaciente"),
	                rs.getString("apellidoPaciente"),
	                "", // telefono
	                "", // direccion
	                null, // fechaNacimiento
	                "", // sexo
	                0, // estatura
	                0  // peso
	            );
	            
	            // 4. Crear Consulta con constructor completo
	            Consulta consulta = new Consulta(
	                rs.getString("idConsulta"),
	                rs.getDate("fecha"),
	                medico,
	                paciente,
	                rs.getString("diagnostico"),
	                rs.getString("indicacion"),
	                rs.getBoolean("importante")
	            );
	            
	            consultas.add(consulta);
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Error al obtener historial médico: " + e.getMessage());
	        JOptionPane.showMessageDialog(null, 
	            "Error al cargar historial médico: " + e.getMessage(),
	            "Error", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    return consultas;
	}*/
	public static ArrayList<Consulta> obtenerHistorialPorPacienteBDD(String idPaciente) {
	    ArrayList<Consulta> consultas = new ArrayList<>();
	    
	    String sql = "SELECT c.idConsulta, c.fecha, c.diagnostico, c.indicacion, c.importante, "
	               + "m.idMedico, m.idPersona, m.exequatur, "
	               + "per.nombre AS nombreMedico, per.apellido AS apellidoMedico, per.cedula, "
	               + "per.telefono, per.direccion, per.fechaNacimiento, per.sexo, "
	               + "p.idPaciente, per_pac.nombre AS nombrePaciente, per_pac.apellido AS apellidoPaciente "
	               + "FROM Consulta c "
	               + "JOIN Medico m ON c.idMedico = m.idMedico "
	               + "JOIN Persona per ON m.idPersona = per.idPersona "
	               + "JOIN Paciente p ON c.idPaciente = p.idPaciente "
	               + "JOIN Persona per_pac ON p.idPersona = per_pac.idPersona "
	               + "WHERE c.idPaciente = ? "
	               + "ORDER BY c.fecha DESC";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, idPaciente);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            // Crear Medico
	            Medico medico = new Medico(
	                rs.getString("idPersona"),
	                rs.getString("cedula"),
	                rs.getString("nombreMedico"),
	                rs.getString("apellidoMedico"),
	                rs.getString("telefono"),
	                rs.getString("direccion"),
	                rs.getDate("fechaNacimiento"),
	                rs.getString("sexo"),
	                rs.getInt("exequatur")
	            );
	            medico.setIdPersona(rs.getString("idMedico"));
	            
	            // Crear Paciente con datos mínimos
	            Paciente paciente = new Paciente(
	                rs.getString("idPaciente"),
	                "", // cedula
	                rs.getString("nombrePaciente"),
	                rs.getString("apellidoPaciente"),
	                "", // telefono
	                "", // direccion
	                null, // fechaNacimiento
	                "", // sexo
	                0, // estatura
	                0  // peso
	            );
	            
	            // Obtener fecha de la consulta desde la BD
	            Date fechaConsulta = rs.getDate("fecha");
	            
	            // Crear Consulta con la fecha correcta
	            Consulta consulta = new Consulta(
	                rs.getString("idConsulta"),
	                fechaConsulta, // Usamos la fecha de la BD
	                medico,
	                paciente,
	                rs.getString("diagnostico"),
	                rs.getString("indicacion"),
	                rs.getBoolean("importante")
	            );
	            consulta.setFecha(fechaConsulta);
	            consultas.add(consulta);
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Error al obtener historial médico: " + e.getMessage());
	        JOptionPane.showMessageDialog(null, 
	            "Error al cargar historial médico: " + e.getMessage(),
	            "Error", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    return consultas;
	}
	
}
