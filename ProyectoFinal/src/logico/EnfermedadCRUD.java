package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
