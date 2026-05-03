import conexion.Conexion;
import dao.FuncionarioDAO;
import dao.FuncionarioDAOImpl;
import excepciones.DAOException;
import modelo.Funcionario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestConsola {

    public static void main(String[] args) {
        System.out.println("====== INICIANDO PRUEBA DEL SISTEMA ======");
        
        // 1. Probar la conexión directa a la base de datos
        System.out.println("\n1. Probando conexión a MySQL...");
        try (Connection conn = Conexion.getConnection()) {
            if (conn != null) {
                System.out.println("   [Éxito] -> ¡Conexión establecida correctamente con la base de datos 'gestion_funcionarios'!");
            }
        } catch (SQLException e) {
            System.err.println("   [Error] -> Ocurrió un problema conectando a la base de datos.");
            System.err.println("   Detalle: " + e.getMessage());
            System.err.println("   (Asegúrate de que el servidor MySQL o XAMPP esté encendido y que la base de datos exista).");
            return; // Termina la prueba si no hay conexión
        }

        // 2. Probar el Patrón DAO (Lectura de datos)
        System.out.println("\n2. Probando la capa DAO (Lectura de Funcionarios)...");
        FuncionarioDAO dao = new FuncionarioDAOImpl();
        try {
            List<Funcionario> lista = dao.listar();
            System.out.println("   [Éxito] -> Se ejecutó la consulta en la tabla 'funcionarios'.");
            System.out.println("   -> Cantidad de funcionarios encontrados: " + lista.size());
            
            if (lista.size() > 0) {
                System.out.println("   -> Listado:");
                for (Funcionario f : lista) {
                    System.out.println("      - ID: " + f.getIdFuncionario() + " | Nombre: " + f.getNombres() + " " + f.getApellidos() + " | Doc: " + f.getNumeroDocumento());
                }
            } else {
                System.out.println("   -> La tabla está vacía o no insertaste los datos iniciales.");
            }
            
        } catch (DAOException e) {
            System.err.println("   [Error] -> Ocurrió un problema interactuando con el DAO de Funcionarios.");
            System.err.println("   Detalle: " + e.getMessage());
        }

        System.out.println("\n====== PRUEBA FINALIZADA ======");
    }
}
