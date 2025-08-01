package logico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


	
}
