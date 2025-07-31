package logico;
 
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
 
public class PacienteCRUD {
 
    // Insertar datos en tabla Persona
    public static boolean insertarPersona(Paciente paciente) {
        String sql = "INSERT INTO Persona (idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, sexo) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = PruebaConexionBBDD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paciente.getIdPersona());
            stmt.setString(2, paciente.getCedula());
            stmt.setString(3, paciente.getNombre());
            stmt.setString(4, paciente.getApellido());
            stmt.setString(5, paciente.getTelefono());
            stmt.setString(6, paciente.getDireccion());
            stmt.setDate(7, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            stmt.setString(8, paciente.getSexo());
 
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
 
        } catch (SQLException e) {
            System.err.println("Error al insertar persona: " + e.getMessage());
            return false;
        }
    }
 
    // Insertar datos en tabla Paciente
    public static boolean insertarPaciente(Paciente paciente) {
        String sql = "INSERT INTO Paciente (idPersona, idPaciente, estatura, peso) VALUES (?, ?, ?, ?)";
        try (Connection conn = PruebaConexionBBDD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setString(1, paciente.getIdPersona());
        	stmt.setString(2, paciente.getIdPersona());
            stmt.setFloat(3, paciente.getEstatura());
            stmt.setFloat(4, paciente.getPeso());
 
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
 
        } catch (SQLException e) {
            System.err.println("Error al insertar paciente: " + e.getMessage());
            return false;
        }
    }
 
    // Método que inserta persona y luego paciente
    public static boolean insertarNuevoPaciente(Paciente paciente) {
        if (insertarPersona(paciente)) {
            return insertarPaciente(paciente);
        } else {
            return false;
        }
    }
 
    // Método para generar código paciente (igual que antes)
    public static String generarCodigoPersona() {
        String sql = "SELECT MAX(CAST(SUBSTRING(idPersona, 3, LEN(idPersona)) AS INT)) AS ultimo_num FROM Persona WHERE idPersona LIKE 'P-%'";
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            int nuevoNumero = 1;
            if (rs.next() && rs.getInt("ultimo_num") > 0) {
                nuevoNumero = rs.getInt("ultimo_num") + 1;
            }
            return "P-" + nuevoNumero;
 
        } catch (SQLException e) {
            System.err.println("Error al generar código de persona: " + e.getMessage());
            return null;
        }
    }
 
}