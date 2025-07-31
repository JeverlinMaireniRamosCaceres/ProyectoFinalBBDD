package logico;
 
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
 
public class PacienteCRUD {
 
 

    public static boolean insertarNuevoPaciente(Paciente paciente) {
        String codigo = generarCodigoPersona();
        if (codigo == null) return false;

        paciente.setIdPersona(codigo);

        String sqlPersona = "INSERT INTO Persona (idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, sexo) "
                          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlPaciente = "INSERT INTO Paciente (idPaciente, idPersona, estatura, peso) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement psPersona = null;
        PreparedStatement psPaciente = null;

        try {
            conn = PruebaConexionBBDD.getConnection();
            conn.setAutoCommit(false); // 🔹 Inicia la transacción

            // Insertar Persona
            psPersona = conn.prepareStatement(sqlPersona);
            psPersona.setString(1, paciente.getIdPersona());
            psPersona.setString(2, paciente.getCedula());
            psPersona.setString(3, paciente.getNombre());
            psPersona.setString(4, paciente.getApellido());
            psPersona.setString(5, paciente.getTelefono());
            psPersona.setString(6, paciente.getDireccion());
            psPersona.setDate(7, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            psPersona.setString(8, paciente.getSexo());
            psPersona.executeUpdate();

            // Insertar Paciente
            psPaciente = conn.prepareStatement(sqlPaciente);
            psPaciente.setString(1, paciente.getIdPersona());
            psPaciente.setString(2, paciente.getIdPersona());
            psPaciente.setFloat(3, paciente.getEstatura());
            psPaciente.setFloat(4, paciente.getPeso());
            psPaciente.executeUpdate();

            conn.commit(); 
            return true;

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback(); // ❌ Revertir si algo falla
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
            }
            System.err.println("Error al insertar pacientepersona: " + e.getMessage());
            return false;

        } finally {
            try {
                if (psPersona != null) psPersona.close();
                if (psPaciente != null) psPaciente.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
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
            System.err.println("Error al generar codigo de persona: " + e.getMessage());
            return null;
        }
    }
 
}