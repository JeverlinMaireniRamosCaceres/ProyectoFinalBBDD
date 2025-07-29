package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexionBBDD {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ClinicaMedica;trustServerCertificate=true";
    private static final String USUARIO = "usuario_java";
    private static final String CONTRASENIA = "123";

    // M�todo est�tico reutilizable para obtener la conexi�n
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
    }

    // M�todo main solo para prueba
    public static void main(String[] args) {
        try (Connection conexion = getConnection()) {
            System.out.println("�Conexi�n exitosa!");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}
