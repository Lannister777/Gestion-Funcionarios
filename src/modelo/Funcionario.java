package modelo;

import java.util.Date;

/**
 * Clase Modelo (Entity o DTO) que representa a un Funcionario.
 * Sus atributos corresponden a las columnas de la tabla 'funcionarios'.
 */
public class Funcionario {
    private int idFuncionario;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private double salario;
    private Date fechaIngreso;
    
    // Llaves foráneas a otras tablas
    private int idTipoDocumento;
    private int idDependencia;
    private int idCargo;

    // Constructores
    public Funcionario() {
    }

    public Funcionario(int idFuncionario, String numeroDocumento, String nombres, String apellidos, 
                       String correo, String telefono, double salario, Date fechaIngreso, 
                       int idTipoDocumento, int idDependencia, int idCargo) {
        this.idFuncionario = idFuncionario;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.idTipoDocumento = idTipoDocumento;
        this.idDependencia = idDependencia;
        this.idCargo = idCargo;
    }

    // Getters y Setters
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public int getIdTipoDocumento() { return idTipoDocumento; }
    public void setIdTipoDocumento(int idTipoDocumento) { this.idTipoDocumento = idTipoDocumento; }

    public int getIdDependencia() { return idDependencia; }
    public void setIdDependencia(int idDependencia) { this.idDependencia = idDependencia; }

    public int getIdCargo() { return idCargo; }
    public void setIdCargo(int idCargo) { this.idCargo = idCargo; }
}
