package dao;

import excepciones.DAOException;
import modelo.Funcionario;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Funcionario
 * implementando el patrón Data Access Object (DAO).
 */
public interface FuncionarioDAO {
    
    /**
     * Obtiene la lista de todos los funcionarios registrados.
     * @return Lista de objetos Funcionario.
     * @throws DAOException Si ocurre un error de acceso a datos.
     */
    List<Funcionario> listar() throws DAOException;
    
    /**
     * Inserta un nuevo funcionario en la base de datos.
     * @param funcionario Objeto que contiene los datos a insertar.
     * @throws DAOException Si ocurre un error de acceso a datos.
     */
    void insertar(Funcionario funcionario) throws DAOException;
    
    /**
     * Actualiza los datos de un funcionario existente.
     * @param funcionario Objeto con los nuevos datos y el ID del funcionario a actualizar.
     * @throws DAOException Si ocurre un error de acceso a datos.
     */
    void actualizar(Funcionario funcionario) throws DAOException;
    
    /**
     * Elimina un funcionario basándose en su identificador.
     * @param idFuncionario ID del funcionario a eliminar.
     * @throws DAOException Si ocurre un error de acceso a datos.
     */
    void eliminar(int idFuncionario) throws DAOException;
    
    /**
     * Busca y retorna un funcionario específico mediante su ID.
     * @param idFuncionario Identificador del funcionario.
     * @return Objeto Funcionario o null si no se encuentra.
     * @throws DAOException Si ocurre un error de acceso a datos.
     */
    Funcionario buscarPorId(int idFuncionario) throws DAOException;
}
