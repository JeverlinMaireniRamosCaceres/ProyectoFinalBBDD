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
 
    public static boolean insertarNuevoMedicoSinUsuario(Medico medico) {
        String codigo = generarCodigoMedico();
        if (codigo == null) return false;

        medico.setIdPersona(codigo);

        String sqlPersona = "INSERT INTO Persona (idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, sexo) "
                          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlMedico = "INSERT INTO Medico (idMedico, idPersona, exequatur, idUsuario) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement psPersona = null;
        PreparedStatement psMedico = null;

        try {
            conn = PruebaConexionBBDD.getConnection();
            conn.setAutoCommit(false); // 🔹 Inicia la transacción

            // Insertar Persona
            psPersona = conn.prepareStatement(sqlPersona);
            psPersona.setString(1, medico.getIdPersona());
            psPersona.setString(2, medico.getCedula());
            psPersona.setString(3, medico.getNombre());
            psPersona.setString(4, medico.getApellido());
            psPersona.setString(5, medico.getTelefono());
            psPersona.setString(6, medico.getDireccion());
            psPersona.setDate(7, new Date(medico.getFechaNacimiento().getTime()));
            psPersona.setString(8, medico.getSexo());
            psPersona.executeUpdate();

            // Insertar Médico
            psMedico = conn.prepareStatement(sqlMedico);
            psMedico.setString(1, medico.getIdPersona()); // mismo id para medico y persona
            psMedico.setString(2, medico.getIdPersona());
            psMedico.setInt(3, medico.getExequatur());
            if (medico.getUsuario() != null) {
                psMedico.setString(4, medico.getUsuario().getCodigo());
            } else {
                psMedico.setNull(4, java.sql.Types.VARCHAR);
            }
            psMedico.executeUpdate();

            conn.commit(); // Confirmar si todo va bien
            return true;

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback(); // Deshacer si hay error
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
            }
            System.err.println("Error al insertar medico/persona: " + e.getMessage());
            return false;

        } finally {
            try {
                if (psPersona != null) psPersona.close();
                if (psMedico != null) psMedico.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

 
    // Metodo para actualizar el idUsuario en Medico una vez creado el usuario
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
    
    public static boolean actualizarUsuarioMedicoPorCedula(String cedula, String idUsuario) {
        String sql = "UPDATE m " +
                     "SET m.idUsuario = ? " +
                     "FROM Medico m " +
                     "JOIN Persona p ON m.idPersona = p.idPersona " +
                     "WHERE p.cedula = ?";
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idUsuario);
            stmt.setString(2, cedula);;

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch(SQLException e) {
            System.err.println("Error al actualizar usuario de medico por c�dula: " + e.getMessage());
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
            System.err.println("Error al eliminar especialidades del medico: " + e.getMessage());
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
            System.err.println("Error al obtener especialidad del medico: " + e.getMessage());
        }
 
        return null;
    }
 
    public static boolean insertarMedicoCompleto(Medico medico, int idEspecialidad) {
        String sqlPersona = "INSERT INTO Persona (idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, sexo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlMedico = "INSERT INTO Medico (idMedico, idPersona, exequatur, idUsuario) VALUES (?, ?, ?, ?)";
        String sqlMedicoEspecialidad = "INSERT INTO Medico_Especialidad (idMedico, idEspecialidad) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement psPersona = null;
        PreparedStatement psMedico = null;
        PreparedStatement psMedEsp = null;

        try {
            conn = PruebaConexionBBDD.getConnection();
            conn.setAutoCommit(false);  // Empezar transacci�n

            // Insertar Persona
            psPersona = conn.prepareStatement(sqlPersona);
            psPersona.setString(1, medico.getIdPersona());
            psPersona.setString(2, medico.getCedula());
            psPersona.setString(3, medico.getNombre());
            psPersona.setString(4, medico.getApellido());
            psPersona.setString(5, medico.getTelefono());
            psPersona.setString(6, medico.getDireccion());
            psPersona.setDate(7, new java.sql.Date(medico.getFechaNacimiento().getTime()));
            psPersona.setString(8, medico.getSexo());
            psPersona.executeUpdate();

            // Insertar Medico
            psMedico = conn.prepareStatement(sqlMedico);
            psMedico.setString(1, medico.getIdPersona());
            psMedico.setString(2, medico.getIdPersona());
            psMedico.setInt(3, medico.getExequatur());
            if (medico.getUsuario() != null) {
                psMedico.setString(4, medico.getUsuario().getCodigo());
            } else {
                psMedico.setNull(4, java.sql.Types.VARCHAR);
            }
            psMedico.executeUpdate();

            // Insertar Especialidad
            psMedEsp = conn.prepareStatement(sqlMedicoEspecialidad);
            psMedEsp.setString(1, medico.getIdPersona());
            psMedEsp.setInt(2, idEspecialidad);
            psMedEsp.executeUpdate();

            conn.commit();  // Confirmar transacci�n

            return true;

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback(); // Rollback si falla
            } catch (SQLException ex) {
                System.err.println("Error en rollback: " + ex.getMessage());
            }
            System.err.println("Error insertando m�dico completo: " + e.getMessage());
            return false;

        } finally {
            try {
                if (psPersona != null) psPersona.close();
                if (psMedico != null) psMedico.close();
                if (psMedEsp != null) psMedEsp.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos: " + e.getMessage());
            }
        }
    }

 
 
}