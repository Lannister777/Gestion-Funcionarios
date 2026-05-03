package excepciones;

/**
 * Excepción personalizada para manejar errores en la capa de acceso a datos (DAO).
 * Esto permite aislar y encapsular las SQLExceptions, lanzando esta excepción que es
 * específica del modelo de negocio, mejorando así la estructura de la aplicación.
 */
public class DAOException extends Exception {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
