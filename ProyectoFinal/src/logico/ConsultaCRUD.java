package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaCRUD {

	// INSERTAR
    public static boolean insertarConsulta(Consulta consulta) {
        String sql = "INSERT INTO Consulta (idConsulta, fecha, diagnostico, indicacion, idPaciente, idMedico, importante) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = PruebaConexionBBDD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consulta.getIdConsulta());
            stmt.setDate(2, new java.sql.Date(consulta.getFecha().getTime()));
            stmt.setString(3, consulta.getDiagnostico());
            stmt.setString(4, consulta.getIndicacion());
            stmt.setString(5, consulta.getPaciente().getIdPersona());
            stmt.setString(6, consulta.getMedico().getIdPersona());
            stmt.setBoolean(7, consulta.isImportante());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar consulta: " + e.getMessage());
            return false;
        }
    }

    // INSERTAR CONSULTA ENFERMEDAD
    public static boolean insertarConsultaEnfermedad(String idConsulta, String idEnfermedad) {
        String sql = "INSERT INTO Consulta_Enfermedad (idConsulta, idEnfermedad) VALUES (?, ?)";

        try (Connection conn = PruebaConexionBBDD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idConsulta);
            stmt.setString(2, idEnfermedad);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar en Consulta_Enfermedad: " + e.getMessage());
            return false;
        }
    }
    
    // GENERAR CODIGO CONSULTA
    public static String generarCodigoConsulta() {
        String sql = "SELECT MAX(CAST(SUBSTRING(idConsulta, 6, LEN(idConsulta)) AS INT)) AS ultimo_num FROM Consulta WHERE idConsulta LIKE 'Cons-%'";
        
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            int nuevoNumero = 1;
            if (rs.next() && rs.getInt("ultimo_num") > 0) {
                nuevoNumero = rs.getInt("ultimo_num") + 1;
            }
            
            return "Cons-" + nuevoNumero;
            
        } catch (SQLException e) {
            System.err.println("Error al generar código de consulta: " + e.getMessage());
            return null;
        }
    }
    
    public static boolean insertarPacienteEnfermedad(String idPaciente, String idEnfermedad, boolean curado) {
        String sql = "INSERT INTO paciente_enfermedad (idPaciente, idEnfermedad, curado) VALUES (?, ?, ?)";

        try (Connection conn = PruebaConexionBBDD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPaciente);
            stmt.setString(2, idEnfermedad);
            stmt.setBoolean(3, curado);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar en paciente_enfermedad: " + e.getMessage());
            return false;
        }
    }


	
}
