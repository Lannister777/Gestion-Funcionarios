package dao;

import conexion.Conexion;
import excepciones.DAOException;
import modelo.Funcionario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación concreta de FuncionarioDAO usando JDBC.
 * Encapsula la lógica de acceso a la tabla 'funcionarios'.
 */
public class FuncionarioDAOImpl implements FuncionarioDAO {

    @Override
    public List<Funcionario> listar() throws DAOException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        
        // El bloque try-with-resources garantiza el cierre automático de recursos
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("id_funcionario"));
                f.setNumeroDocumento(rs.getString("numero_documento"));
                f.setNombres(rs.getString("nombres"));
                f.setApellidos(rs.getString("apellidos"));
                f.setCorreo(rs.getString("correo"));
                f.setTelefono(rs.getString("telefono"));
                f.setSalario(rs.getDouble("salario"));
                f.setFechaIngreso(rs.getDate("fecha_ingreso"));
                f.setIdTipoDocumento(rs.getInt("id_tipo_documento"));
                f.setIdDependencia(rs.getInt("id_dependencia"));
                f.setIdCargo(rs.getInt("id_cargo"));
                lista.add(f);
            }
        } catch (SQLException e) {
            // Capturamos la SQLException y lanzamos nuestra excepción de dominio personalizada
            throw new DAOException("Error al listar los funcionarios", e);
        }
        return lista;
    }

    @Override
    public void insertar(Funcionario f) throws DAOException {
        String sql = "INSERT INTO funcionarios (numero_documento, nombres, apellidos, correo, telefono, salario, fecha_ingreso, id_tipo_documento, id_dependencia, id_cargo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, f.getNumeroDocumento());
            ps.setString(2, f.getNombres());
            ps.setString(3, f.getApellidos());
            ps.setString(4, f.getCorreo());
            ps.setString(5, f.getTelefono());
            ps.setDouble(6, f.getSalario());
            ps.setDate(7, new Date(f.getFechaIngreso().getTime()));
            ps.setInt(8, f.getIdTipoDocumento());
            ps.setInt(9, f.getIdDependencia());
            ps.setInt(10, f.getIdCargo());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al insertar el funcionario", e);
        }
    }

    @Override
    public void actualizar(Funcionario f) throws DAOException {
        String sql = "UPDATE funcionarios SET numero_documento=?, nombres=?, apellidos=?, correo=?, telefono=?, salario=?, fecha_ingreso=?, id_tipo_documento=?, id_dependencia=?, id_cargo=? WHERE id_funcionario=?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, f.getNumeroDocumento());
            ps.setString(2, f.getNombres());
            ps.setString(3, f.getApellidos());
            ps.setString(4, f.getCorreo());
            ps.setString(5, f.getTelefono());
            ps.setDouble(6, f.getSalario());
            ps.setDate(7, new Date(f.getFechaIngreso().getTime()));
            ps.setInt(8, f.getIdTipoDocumento());
            ps.setInt(9, f.getIdDependencia());
            ps.setInt(10, f.getIdCargo());
            ps.setInt(11, f.getIdFuncionario());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el funcionario", e);
        }
    }

    @Override
    public void eliminar(int idFuncionario) throws DAOException {
        String sql = "DELETE FROM funcionarios WHERE id_funcionario=?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, idFuncionario);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el funcionario", e);
        }
    }

    @Override
    public Funcionario buscarPorId(int idFuncionario) throws DAOException {
        Funcionario f = null;
        String sql = "SELECT * FROM funcionarios WHERE id_funcionario=?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, idFuncionario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    f = new Funcionario();
                    f.setIdFuncionario(rs.getInt("id_funcionario"));
                    f.setNumeroDocumento(rs.getString("numero_documento"));
                    f.setNombres(rs.getString("nombres"));
                    f.setApellidos(rs.getString("apellidos"));
                    f.setCorreo(rs.getString("correo"));
                    f.setTelefono(rs.getString("telefono"));
                    f.setSalario(rs.getDouble("salario"));
                    f.setFechaIngreso(rs.getDate("fecha_ingreso"));
                    f.setIdTipoDocumento(rs.getInt("id_tipo_documento"));
                    f.setIdDependencia(rs.getInt("id_dependencia"));
                    f.setIdCargo(rs.getInt("id_cargo"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar el funcionario con ID: " + idFuncionario, e);
        }
        return f;
    }
}
