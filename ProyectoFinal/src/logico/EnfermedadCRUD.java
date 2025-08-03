package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class EnfermedadCRUD {

	// INSERT
	public static boolean insertarEnfermedad(Enfermedad e) {
	    String sql = "INSERT INTO Enfermedad (idEnfermedad, nombre, sintomas, idTipoEnfermedad) VALUES (?, ?, ?, ?)";

	    try (Connection conn = PruebaConexionBBDD.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, e.getIdEnfermedad());
	        stmt.setString(2, e.getNombre());
	        stmt.setString(3, e.getSintomas());
	        stmt.setInt(4, e.getTipo());

	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (SQLException ex) {
	        System.err.println("Error al insertar enfermedad: " + ex.getMessage());
	        return false;
	    }
	}

    
    // BUSCAR NUMERO SECUENCIA CODIGO
    public static String generarCodigoEnfermedad() {
        String sql = "SELECT MAX(CAST(SUBSTRING(idEnfermedad, 3, LEN(idEnfermedad)) AS INT)) AS ultimo_num FROM Enfermedad WHERE idEnfermedad LIKE 'E-%'";
        
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            int nuevoNumero = 1;
            if (rs.next() && rs.getInt("ultimo_num") > 0) {
                nuevoNumero = rs.getInt("ultimo_num") + 1;
            }
            
            return "E-" + nuevoNumero;
            
        } catch (SQLException e) {
            System.err.println("Error al generar código de enfermedad: " + e.getMessage());
            return null;
        }
    }
    
    public static ArrayList<Enfermedad> obtenerEnfermedades() {
        ArrayList<Enfermedad> lista = new ArrayList<>();
        String sql = "SELECT e.idEnfermedad, e.nombre, e.sintomas, e.idTipoEnfermedad, te.nombre AS tipo " +
                     "FROM Enfermedad e " +
                     "JOIN Tipo_Enfermedad te ON e.idTipoEnfermedad = te.idTipoEnfermedad";

        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Enfermedad enf = new Enfermedad();
                enf.setIdEnfermedad(rs.getString("idEnfermedad"));
                enf.setNombre(rs.getString("nombre"));
                enf.setSintomas(rs.getString("sintomas"));
                enf.setTipo(rs.getInt("idTipoEnfermedad"));    
                enf.setTipoNombre(rs.getString("tipo"));  
                lista.add(enf);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener enfermedades: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR ENFERMEDAD POR CODIGO
    public static Enfermedad buscarEnfermedadPorCodigo(String id) {
        String sql = "SELECT e.idEnfermedad, e.nombre, e.sintomas, e.idTipoEnfermedad, te.nombre AS tipo " +
                     "FROM Enfermedad e " +
                     "JOIN Tipo_Enfermedad te ON e.idTipoEnfermedad = te.idTipoEnfermedad " +
                     "WHERE e.idEnfermedad = ?";

        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Enfermedad enf = new Enfermedad();
                enf.setIdEnfermedad(rs.getString("idEnfermedad"));
                enf.setNombre(rs.getString("nombre"));
                enf.setSintomas(rs.getString("sintomas"));
                enf.setTipo(rs.getInt("idTipoEnfermedad"));
                enf.setTipoNombre(rs.getString("tipo"));
                return enf;
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar enfermedad por código: " + e.getMessage());
        }

        return null; 
    }
    
    // OBTENER ENFERMEDADES CON PACIENTE (PARA CONTROL ENFERMEDADES)
    public static ArrayList<Object[]> obtenerEnfermedadesConPacientes() {
        ArrayList<Object[]> lista = new ArrayList<>();
        String sql = "SELECT e.idEnfermedad, e.nombre, te.nombre AS tipo, " +
                "(SELECT COUNT(*) FROM Paciente_Enfermedad pe WHERE pe.idEnfermedad = e.idEnfermedad AND pe.curado = 0) AS pacientes " + 
                "FROM Enfermedad e " +
                "JOIN Tipo_Enfermedad te ON e.idTipoEnfermedad = te.idTipoEnfermedad " +
                "ORDER BY e.nombre";

        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("idEnfermedad");    // cod
                fila[1] = rs.getString("nombre");          // bombre enfermedad
                fila[2] = rs.getString("tipo");           // tipo enfermedad
                fila[3] = rs.getInt("pacientes");         // cant pacientes
                lista.add(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener enfermedades con pacientes: " + e.getMessage());
        }

        return lista;
    }
    
    // OBTENER PACIENTES POR ENFERMEDAD (PARA CONTROL ENFERMEDAD)
    public static ArrayList<Object[]> obtenerPacientesPorEnfermedad(String idEnfermedad) {
        ArrayList<Object[]> lista = new ArrayList<>();
        String sql = "SELECT per.cedula, per.nombre, per.apellido, per.telefono " +
                     "FROM Paciente_Enfermedad pe " +
                     "JOIN Paciente p ON pe.idPaciente = p.idPaciente " +
                     "JOIN Persona per ON p.idPersona = per.idPersona " +
                     "WHERE pe.idEnfermedad = ? AND pe.curado = 0 " +
                     "ORDER BY per.nombre, per.apellido";

        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idEnfermedad);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("cedula");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getString("telefono");
                lista.add(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener pacientes por enfermedad: " + e.getMessage());
        }

        return lista;
    }
    
    // UPDATE
    public static boolean actualizarEnfermedad(Enfermedad enfermedad) {
        String sql = "UPDATE Enfermedad SET nombre = ?, sintomas = ?, idTipoEnfermedad = ? WHERE idEnfermedad = ?";
        
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, enfermedad.getNombre());
            stmt.setString(2, enfermedad.getSintomas());
            stmt.setInt(3, enfermedad.getTipo());  
            stmt.setString(4, enfermedad.getIdEnfermedad());
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar enfermedad: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // obteniendo el id del tipo enfermedad mediante el nombre del tipo
    private static int obtenerIdTipoEnfermedad(String nombreTipo) {
        String sql = "SELECT idTipoEnfermedad FROM Tipo_Enfermedad WHERE nombre = ?";
        
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombreTipo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("idTipoEnfermedad");
            }
            return -1;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
