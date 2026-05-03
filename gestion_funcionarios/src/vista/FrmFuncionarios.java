package vista;

import dao.FuncionarioDAO;
import dao.FuncionarioDAOImpl;
import excepciones.DAOException;
import modelo.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Interfaz Gráfica (Vista) para gestionar las operaciones CRUD de Funcionarios.
 */
public class FrmFuncionarios extends JFrame {

    private JTextField txtId, txtDocumento, txtNombres, txtApellidos, txtCorreo, txtTelefono, txtSalario, txtFechaIngreso;
    private JComboBox<String> cmbTipoDoc, cmbDependencia, cmbCargo;
    private JTable tblFuncionarios;
    private DefaultTableModel modeloTabla;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnLimpiar, btnListar;
    
    private FuncionarioDAO dao;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public FrmFuncionarios() {
        dao = new FuncionarioDAOImpl();
        sdf.setLenient(false);
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setTitle("Gestión de Funcionarios");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel superior - Formulario
        JPanel panelForm = new JPanel(new GridLayout(6, 4, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Funcionario"));

        panelForm.add(new JLabel("ID (Automático):"));
        txtId = new JTextField();
        txtId.setEditable(false);
        panelForm.add(txtId);

        panelForm.add(new JLabel("N° Documento:"));
        txtDocumento = new JTextField();
        panelForm.add(txtDocumento);

        panelForm.add(new JLabel("Nombres:"));
        txtNombres = new JTextField();
        panelForm.add(txtNombres);

        panelForm.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panelForm.add(txtApellidos);

        panelForm.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelForm.add(txtCorreo);

        panelForm.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelForm.add(txtTelefono);

        panelForm.add(new JLabel("Salario:"));
        txtSalario = new JTextField();
        panelForm.add(txtSalario);

        panelForm.add(new JLabel("Fecha Ingreso (yyyy-MM-dd):"));
        txtFechaIngreso = new JTextField();
        panelForm.add(txtFechaIngreso);

        panelForm.add(new JLabel("Tipo Documento:"));
        cmbTipoDoc = new JComboBox<>(new String[]{"Cédula de Ciudadanía", "Cédula de Extranjería", "Pasaporte"});
        panelForm.add(cmbTipoDoc);

        panelForm.add(new JLabel("Dependencia:"));
        cmbDependencia = new JComboBox<>(new String[]{"Recursos Humanos", "Tecnología y Sistemas", "Finanzas y Contabilidad", "Operaciones"});
        panelForm.add(cmbDependencia);

        panelForm.add(new JLabel("Cargo:"));
        cmbCargo = new JComboBox<>(new String[]{"Director", "Desarrollador de Software", "Contador", "Analista de Datos", "Asistente Administrativo"});
        panelForm.add(cmbCargo);

        add(panelForm, BorderLayout.NORTH);

        // Panel central - Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnListar = new JButton("Listar / Refrescar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnListar);

        add(panelBotones, BorderLayout.CENTER);

        // Panel inferior - Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Doc", "Nombres", "Apellidos", "Correo", "Teléfono", "Salario", "Ingreso", "T.Doc", "Dep.", "Cargo"}, 0);
        tblFuncionarios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tblFuncionarios);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        add(scrollPane, BorderLayout.SOUTH);

        // Eventos de botones
        btnGuardar.addActionListener(e -> guardar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnListar.addActionListener(e -> cargarDatos());

        // Evento de tabla al seleccionar
        tblFuncionarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblFuncionarios.getSelectedRow() != -1) {
                seleccionarRegistro();
            }
        });
    }

    private void cargarDatos() {
        try {
            modeloTabla.setRowCount(0); // Limpiar tabla
            List<Funcionario> lista = dao.listar();
            for (Funcionario f : lista) {
                Object[] fila = {
                        f.getIdFuncionario(), f.getNumeroDocumento(), f.getNombres(), f.getApellidos(),
                        f.getCorreo(), f.getTelefono(), f.getSalario(), f.getFechaIngreso(),
                        f.getIdTipoDocumento(), f.getIdDependencia(), f.getIdCargo()
                };
                modeloTabla.addRow(fila);
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void seleccionarRegistro() {
        int fila = tblFuncionarios.getSelectedRow();
        txtId.setText(tblFuncionarios.getValueAt(fila, 0).toString());
        txtDocumento.setText(tblFuncionarios.getValueAt(fila, 1).toString());
        txtNombres.setText(tblFuncionarios.getValueAt(fila, 2).toString());
        txtApellidos.setText(tblFuncionarios.getValueAt(fila, 3).toString());
        txtCorreo.setText(tblFuncionarios.getValueAt(fila, 4).toString());
        
        Object telefono = tblFuncionarios.getValueAt(fila, 5);
        txtTelefono.setText(telefono != null ? telefono.toString() : "");
        
        txtSalario.setText(tblFuncionarios.getValueAt(fila, 6).toString());
        txtFechaIngreso.setText(tblFuncionarios.getValueAt(fila, 7).toString());
        
        // Ajustamos los índices de los ComboBox restando 1 ya que el ID de base de datos comienza en 1
        cmbTipoDoc.setSelectedIndex((int) tblFuncionarios.getValueAt(fila, 8) - 1);
        cmbDependencia.setSelectedIndex((int) tblFuncionarios.getValueAt(fila, 9) - 1);
        cmbCargo.setSelectedIndex((int) tblFuncionarios.getValueAt(fila, 10) - 1);
    }

    private void guardar() {
        if (!validarCampos()) return;
        try {
            Funcionario f = leerDatosFormulario();
            dao.insertar(f);
            JOptionPane.showMessageDialog(this, "Funcionario guardado exitosamente.");
            limpiarCampos();
            cargarDatos();
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario de la tabla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validarCampos()) return;
        try {
            Funcionario f = leerDatosFormulario();
            f.setIdFuncionario(Integer.parseInt(txtId.getText()));
            dao.actualizar(f);
            JOptionPane.showMessageDialog(this, "Funcionario actualizado exitosamente.");
            limpiarCampos();
            cargarDatos();
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(Integer.parseInt(txtId.getText()));
                JOptionPane.showMessageDialog(this, "Funcionario eliminado exitosamente.");
                limpiarCampos();
                cargarDatos();
            } catch (DAOException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtDocumento.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtSalario.setText("");
        txtFechaIngreso.setText("");
        cmbTipoDoc.setSelectedIndex(0);
        cmbDependencia.setSelectedIndex(0);
        cmbCargo.setSelectedIndex(0);
        tblFuncionarios.clearSelection();
    }

    private boolean validarCampos() {
        if (txtDocumento.getText().trim().isEmpty() || txtNombres.getText().trim().isEmpty() ||
            txtApellidos.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty() ||
            txtSalario.getText().trim().isEmpty() || txtFechaIngreso.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            Double.parseDouble(txtSalario.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El salario debe ser un valor numérico.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            sdf.parse(txtFechaIngreso.getText().trim());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener el formato yyyy-MM-dd.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private Funcionario leerDatosFormulario() {
        Funcionario f = new Funcionario();
        f.setNumeroDocumento(txtDocumento.getText().trim());
        f.setNombres(txtNombres.getText().trim());
        f.setApellidos(txtApellidos.getText().trim());
        f.setCorreo(txtCorreo.getText().trim());
        f.setTelefono(txtTelefono.getText().trim());
        f.setSalario(Double.parseDouble(txtSalario.getText().trim()));
        
        try {
            Date date = sdf.parse(txtFechaIngreso.getText().trim());
            f.setFechaIngreso(date);
        } catch (ParseException ignored) {}

        // Sumamos 1 al index porque en la DB las llaves primarias de parámetros inician en 1
        f.setIdTipoDocumento(cmbTipoDoc.getSelectedIndex() + 1);
        f.setIdDependencia(cmbDependencia.getSelectedIndex() + 1);
        f.setIdCargo(cmbCargo.getSelectedIndex() + 1);

        return f;
    }
}
