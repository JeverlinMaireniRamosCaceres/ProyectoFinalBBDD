package logico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class VacunaCRUD {

	// INSERTAR VACUNA
    public static boolean insertarVacuna(Vacuna vacuna) {
        String sql = "INSERT INTO Vacuna (idVacuna, nombre, cant, fechaVencimiento, idFabricante, idTipoVacuna) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vacuna.getIdVacuna());
            stmt.setString(2, vacuna.getNombreVacuna());
            stmt.setInt(3, vacuna.getCantidad());
            stmt.setDate(4, new java.sql.Date(vacuna.getFecha().getTime()));
            stmt.setInt(5, vacuna.getFabricante());
            stmt.setInt(6, vacuna.getTipo());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error insertando vacuna: " + e.getMessage());
            return false;
        }
    }
    
    // GENERAR CODIGO VACUNA
    public static String generarCodigoVacuna() {
        String sql = "SELECT MAX(CAST(SUBSTRING(idVacuna, 3, LEN(idVacuna)) AS INT)) AS ultimo_num FROM Vacuna WHERE idVacuna LIKE 'V-%'";
        
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            int nuevoNumero = 1;
            if (rs.next() && rs.getInt("ultimo_num") > 0) {
                nuevoNumero = rs.getInt("ultimo_num") + 1;
            }
            
            return "V-" + nuevoNumero;
            
        } catch (SQLException e) {
            System.err.println("Error al generar código de vacuna: " + e.getMessage());
            return null;
        }
    }
    
    // SELECT VACUNA
    public static ArrayList<Vacuna> obtenerVacunasDesdeBDD() {
        ArrayList<Vacuna> listaVacunas = new ArrayList<>();
        String sql = "SELECT * FROM Vacuna";

        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String idVacuna = rs.getString("idVacuna");
                java.sql.Date fechaSQL = rs.getDate("fechaVencimiento");
                Date fecha = new Date(fechaSQL.getTime()); 
                String nombreVacuna = rs.getString("nombre");
                int idTipo = rs.getInt("idTipoVacuna");
                int idFabricante = rs.getInt("idFabricante");
                int cantidad = rs.getInt("cant");

                Vacuna vacuna = new Vacuna(idVacuna, fecha, nombreVacuna, idTipo, idFabricante, cantidad);
                listaVacunas.add(vacuna);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener vacunas: " + e.getMessage());
        }

        return listaVacunas;
    }
    
    // ACTUALIZAR LA VACUNA
    public static boolean actualizarVacuna(Vacuna vacuna) {
        String sql = "UPDATE Vacuna SET nombre = ?, fechaVencimiento = ?, idTipoVacuna = ?, idFabricante = ?, cant = ? WHERE idVacuna = ?";
        
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vacuna.getNombreVacuna());
            stmt.setDate(2, new java.sql.Date(vacuna.getFecha().getTime()));
            stmt.setInt(3, vacuna.getTipo() + 1); // +1 porque en BD los IDs empiezan en 1
            stmt.setInt(4, vacuna.getFabricante() + 1); // +1 por la misma razón
            stmt.setInt(5, vacuna.getCantidad());
            stmt.setString(6, vacuna.getIdVacuna());
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar vacuna: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // DELETE
    public static boolean eliminarVacuna(String idVacuna) {
        // se verifica si tiene registros en Historial_Vacuna
        if (tieneAplicacionesRegistradas(idVacuna)) {
            JOptionPane.showMessageDialog(null, 
                "No se puede eliminar la vacuna porque tiene aplicaciones registradas en pacientes.",
                "Error de integridad", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // si no tiene registros, se elimina
        String sql = "DELETE FROM Vacuna WHERE idVacuna = ?";
        
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idVacuna);
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            return false;
        }
    }
    
    private static boolean tieneAplicacionesRegistradas(String idVacuna) {
        String sql = "SELECT COUNT(*) AS total FROM Historial_Vacuna WHERE idVacuna = ?";
        
        try (Connection conn = PruebaConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idVacuna);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al verificar aplicaciones de vacuna: " + e.getMessage());
        }
        
        return false;
    }


	
}
