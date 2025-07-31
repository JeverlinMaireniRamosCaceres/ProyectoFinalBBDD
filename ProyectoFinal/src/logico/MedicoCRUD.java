package logico;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
public class MedicoCRUD {
 
    // Insertar datos en tabla Persona (datos básicos de la persona que es médico)
    public static boolean insertarPersona(Medico medico) {
        String sql = "INSERT INTO Persona (idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, sexo) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = PruebaConexionBBDD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medico.getIdPersona());
            stmt.setString(2, medico.getCedula());
            stmt.setString(3, medico.getNombre());
            stmt.setString(4, medico.getApellido());
            stmt.setString(5, medico.getTelefono());
            stmt.setString(6, medico.getDireccion());
            stmt.setDate(7, new Date(medico.getFechaNacimiento().getTime()));
            stmt.setString(8, medico.getSexo());
 
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
 
        } catch (SQLException e) {
            System.err.println("Error al insertar persona: " + e.getMessage());
            return false;
        }
    }
 
    // Insertar datos en tabla Medico
    public static boolean insertarMedico(Medico medico) {
        String sql = "INSERT INTO Medico (idMedico, idPersona, exequatur, idUsuario) VALUES (?, ?, ?, ?)";
        try (Connection conn = PruebaConexionBBDD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medico.getIdPersona()); // mismo id para medico y persona
            stmt.setString(2, medico.getIdPersona());
            stmt.setInt(3, medico.getExequatur());
            // Aquí puede ser null si el usuario aún no existe, o el código asignado
            if(medico.getUsuario() != null) {
                stmt.setString(4, medico.getUsuario().getCodigo());
            } else {
                stmt.setNull(4, java.sql.Types.VARCHAR);
            }
 
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
 
        } catch (SQLException e) {
            System.err.println("Error al insertar medico: " + e.getMessage());
            return false;
        }
    }
 
    // Método para generar código único para médico (y persona)
    public static String generarCodigoMedico() {
        String sql = "SELECT MAX(CAST(SUBSTRING(idMedico, 3, LEN(idMedico)) AS INT)) AS ultimo_num FROM Medico WHERE idMedico LIKE 'M-%'";
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            int nuevoNumero = 1;
            if (rs.next() && rs.getInt("ultimo_num") > 0) {
                nuevoNumero = rs.getInt("ultimo_num") + 1;
            }
            return "M-" + nuevoNumero;
 
        } catch (SQLException e) {
            System.err.println("Error al generar código de medico: " + e.getMessage());
            return null;
        }
    }
 
    // Método que inserta persona y médico (sin usuario) — para usar cuando sólo creas médico
    public static boolean insertarNuevoMedicoSinUsuario(Medico medico) {
        // Generar código único para médico y persona
        String codigo = generarCodigoMedico();
        if (codigo == null) return false;
 
        // Asignar código a medico
        medico.setIdPersona(codigo);
 
        // Insertar Persona
        if (!insertarPersona(medico)) return false;
 
        // Insertar Médico (sin usuario aún)
        return insertarMedico(medico);
    }
 
    // Método para actualizar el idUsuario en Medico una vez creado el usuario
    public static boolean actualizarUsuarioMedico(String idMedico, String idUsuario) {
        String sql = "UPDATE Medico SET idUsuario = ? WHERE idMedico = ?";
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idUsuario);
            stmt.setString(2, idMedico);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch(SQLException e) {
            System.err.println("Error al actualizar usuario de medico: " + e.getMessage());
            return false;
        }
    }
    public static List<Especialidad> obtenerEspecialidades() {
        List<Especialidad> lista = new ArrayList<>();
        String sql = "SELECT idEspecialidad, nombre FROM Especialidad";
 
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
 
            while (rs.next()) {
                int id = rs.getInt("idEspecialidad");
                String nombre = rs.getString("nombre");
                lista.add(new Especialidad(id, nombre));
            }
 
        } catch (SQLException e) {
            System.err.println("Error al obtener especialidades: " + e.getMessage());
        }
 
        return lista;
    }
    public static boolean insertarMedicoEspecialidad(String idMedico, int idEspecialidad) {
        String sql = "INSERT INTO Medico_Especialidad (idMedico, idEspecialidad) VALUES (?, ?)";
 
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
 
            stmt.setString(1, idMedico);
            stmt.setInt(2, idEspecialidad);
 
            int filas = stmt.executeUpdate();
            return filas > 0;
 
        } catch (SQLException e) {
            System.err.println("Error al insertar en Medico_Especialidad: " + e.getMessage());
            return false;
        }
    }
    public static void eliminarEspecialidadesDelMedico(String idMedico) {
        String sql = "DELETE FROM Medico_Especialidad WHERE idMedico = ?";
 
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
 
            stmt.setString(1, idMedico);
            stmt.executeUpdate();
 
        } catch (SQLException e) {
            System.err.println("Error al eliminar especialidades del médico: " + e.getMessage());
        }
    }
 
    public static Especialidad obtenerEspecialidadDelMedico(String idMedico) {
        String sql = "SELECT e.idEspecialidad, e.nombre " +
                     "FROM Medico_Especialidad me " +
                     "JOIN Especialidad e ON me.idEspecialidad = e.idEspecialidad " +
                     "WHERE me.idMedico = ?";
 
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
 
            stmt.setString(1, idMedico);
            ResultSet rs = stmt.executeQuery();
 
            if (rs.next()) {
                int id = rs.getInt("idEspecialidad");
                String nombre = rs.getString("nombre");
                return new Especialidad(id, nombre);
            }
 
        } catch (SQLException e) {
            System.err.println("Error al obtener especialidad del médico: " + e.getMessage());
        }
 
        return null;
    }
 
 
 
}