package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de manejar la conexión a la base de datos MySQL/MariaDB.
 * Utiliza el patrón Singleton asumiendo que es una aplicación de escritorio simple,
 * aunque proporcionaremos un método estático para obtener conexiones por demanda.
 */
public class Conexion {
    
    // Parámetros de conexión requeridos
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_funcionarios";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    /**
     * Método para establecer y obtener la conexión a la base de datos.
     * @return Objeto Connection con la conexión activa.
     * @throws SQLException Si ocurre un error al conectar.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Se puede omitir en versiones recientes de JDBC, pero es buena práctica indicarlo
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver de MySQL: " + e.getMessage());
        }
    }
}
