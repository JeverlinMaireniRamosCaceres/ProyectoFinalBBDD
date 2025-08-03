package logico;
 
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
 
 
public class PacienteCRUD {
 
	// INSERT
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
            conn.setAutoCommit(false); // comienzo de la transaccion

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
                if (conn != null) conn.rollback(); // revertir si una insercion falla
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

 
    // GENERAR SECUENCIA CODIGO PACIENTE
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
    
    // UPDATE
    public static boolean actualizarPaciente(Paciente paciente) {
        String sqlPersona = "UPDATE Persona SET cedula = ?, nombre = ?, apellido = ?, telefono = ?, " +
                           "direccion = ?, fechaNacimiento = ?, sexo = ? WHERE idPersona = ?";
        
        String sqlPaciente = "UPDATE Paciente SET estatura = ?, peso = ? WHERE idPaciente = ?";

        Connection conn = null;
        try {
            conn = PruebaConexionBBDD.getConnection();
            conn.setAutoCommit(false); // inicio de la transaccion

            // Actualizar Persona
            try (PreparedStatement psPersona = conn.prepareStatement(sqlPersona)) {
                psPersona.setString(1, paciente.getCedula());
                psPersona.setString(2, paciente.getNombre());
                psPersona.setString(3, paciente.getApellido());
                psPersona.setString(4, paciente.getTelefono());
                psPersona.setString(5, paciente.getDireccion());
                psPersona.setDate(6, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
                psPersona.setString(7, String.valueOf(paciente.getSexo()));
                psPersona.setString(8, paciente.getIdPersona());
                psPersona.executeUpdate();
            }

            //  Actualizar Paciente
            try (PreparedStatement psPaciente = conn.prepareStatement(sqlPaciente)) {
                psPaciente.setFloat(1, paciente.getEstatura());
                psPaciente.setFloat(2, paciente.getPeso());
                psPaciente.setString(3, paciente.getIdPersona());
                psPaciente.executeUpdate();
            }

            conn.commit(); // confirmacion de los cambios
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) {}
            }
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar paciente: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) {}
            }
        }
    }
 
}