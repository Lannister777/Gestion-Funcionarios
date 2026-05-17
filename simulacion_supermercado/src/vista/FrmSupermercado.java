package vista;

import hilos.CajeraThread;
import modelo.Cliente;
import modelo.Producto;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FrmSupermercado extends JFrame {

    private List<Cliente> clientesPendientes;
    private int contadorClientes = 1;

    private JTextArea txtLog;
    private JPanel panelClientes;
    private JTextField txtCostoProducto;
    private JTextField txtTiempoProducto;
    private JTextField txtNombreProducto;
    private JList<String> listProductosTemp;
    private DefaultListModel<String> modelProductosTemp;
    private List<Producto> carritoTemp;

    public FrmSupermercado() {
        clientesPendientes = new ArrayList<>();
        carritoTemp = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        setTitle("Simulación de Supermercado - Concurrencia");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel izquierdo: Creación de clientes y productos
        JPanel panelIzquierdo = new JPanel(new BorderLayout(5, 5));
        panelIzquierdo.setPreferredSize(new Dimension(300, 0));
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Subpanel para crear producto
        JPanel panelProducto = new JPanel(new GridLayout(4, 2, 5, 5));
        panelProducto.setBorder(BorderFactory.createTitledBorder("Añadir Producto al Carrito"));
        
        panelProducto.add(new JLabel("Nombre:"));
        txtNombreProducto = new JTextField();
        panelProducto.add(txtNombreProducto);
        
        panelProducto.add(new JLabel("Costo ($):"));
        txtCostoProducto = new JTextField();
        panelProducto.add(txtCostoProducto);
        
        panelProducto.add(new JLabel("Tiempo (seg):"));
        txtTiempoProducto = new JTextField();
        panelProducto.add(txtTiempoProducto);

        JButton btnAddProducto = new JButton("Agregar Producto");
        btnAddProducto.addActionListener(e -> agregarProductoTemporal());
        panelProducto.add(new JLabel("")); // Espacio
        panelProducto.add(btnAddProducto);

        // Subpanel carrito temporal
        modelProductosTemp = new DefaultListModel<>();
        listProductosTemp = new JList<>(modelProductosTemp);
        JScrollPane scrollCarrito = new JScrollPane(listProductosTemp);
        scrollCarrito.setBorder(BorderFactory.createTitledBorder("Carrito del Cliente Actual"));
        
        JButton btnCrearCliente = new JButton("Crear Cliente con Carrito");
        btnCrearCliente.addActionListener(e -> crearCliente());

        JPanel panelCreacion = new JPanel(new BorderLayout(5, 5));
        panelCreacion.add(panelProducto, BorderLayout.NORTH);
        panelCreacion.add(scrollCarrito, BorderLayout.CENTER);
        panelCreacion.add(btnCrearCliente, BorderLayout.SOUTH);

        panelIzquierdo.add(panelCreacion, BorderLayout.CENTER);
        add(panelIzquierdo, BorderLayout.WEST);

        // Panel central: Log de la simulación
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollLog = new JScrollPane(txtLog);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Log de Simulación"));
        
        add(scrollLog, BorderLayout.CENTER);

        // Panel derecho: Clientes en cola y Botón de Inicio
        JPanel panelDerecho = new JPanel(new BorderLayout(10, 10));
        panelDerecho.setPreferredSize(new Dimension(200, 0));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelClientes = new JPanel();
        panelClientes.setLayout(new BoxLayout(panelClientes, BoxLayout.Y_AXIS));
        JScrollPane scrollClientes = new JScrollPane(panelClientes);
        scrollClientes.setBorder(BorderFactory.createTitledBorder("Clientes en Cola"));

        JButton btnIniciarSimulacion = new JButton("INICIAR SIMULACIÓN");
        btnIniciarSimulacion.setBackground(new Color(34, 139, 34));
        btnIniciarSimulacion.setForeground(Color.WHITE);
        btnIniciarSimulacion.setFont(new Font("Arial", Font.BOLD, 14));
        btnIniciarSimulacion.addActionListener(e -> iniciarSimulacion());

        JButton btnLimpiar = new JButton("Limpiar Todo");
        btnLimpiar.addActionListener(e -> limpiarTodo());

        JPanel panelAcciones = new JPanel(new GridLayout(2, 1, 5, 5));
        panelAcciones.add(btnIniciarSimulacion);
        panelAcciones.add(btnLimpiar);

        panelDerecho.add(scrollClientes, BorderLayout.CENTER);
        panelDerecho.add(panelAcciones, BorderLayout.SOUTH);

        add(panelDerecho, BorderLayout.EAST);
    }

    private void agregarProductoTemporal() {
        try {
            String nombre = txtNombreProducto.getText().trim();
            if (nombre.isEmpty()) throw new Exception("El nombre no puede estar vacío");
            
            double costo = Double.parseDouble(txtCostoProducto.getText().trim());
            int tiempo = Integer.parseInt(txtTiempoProducto.getText().trim());

            Producto p = new Producto(nombre, costo, tiempo);
            carritoTemp.add(p);
            modelProductosTemp.addElement(nombre + " - $" + costo + " - " + tiempo + "s");

            txtNombreProducto.setText("");
            txtCostoProducto.setText("");
            txtTiempoProducto.setText("");
            txtNombreProducto.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Costo y Tiempo deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearCliente() {
        if (carritoTemp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe agregar al menos un producto al carrito.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombreCliente = "Cliente " + contadorClientes++;
        List<Producto> productosFinales = new ArrayList<>(carritoTemp);
        Cliente c = new Cliente(nombreCliente, productosFinales);
        
        clientesPendientes.add(c);
        
        JLabel lblCliente = new JLabel(nombreCliente + " (" + productosFinales.size() + " prod)");
        lblCliente.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelClientes.add(lblCliente);
        panelClientes.revalidate();
        panelClientes.repaint();

        // Limpiar para el siguiente cliente
        carritoTemp.clear();
        modelProductosTemp.clear();
        log("Se ha agregado a la cola el " + nombreCliente);
    }

    private void iniciarSimulacion() {
        if (clientesPendientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay clientes en la cola para procesar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        log("\n====== INICIO DE LA SIMULACIÓN DE COBRO ======");
        long initialTime = System.currentTimeMillis();

        List<CajeraThread> cajeras = new ArrayList<>();
        
        // Crear un hilo por cada cliente pendiente
        for (int i = 0; i < clientesPendientes.size(); i++) {
            Cliente c = clientesPendientes.get(i);
            String nombreCajera = "Cajera " + (i + 1);
            
            CajeraThread hilo = new CajeraThread(nombreCajera, c, initialTime, msg -> {
                // Utilizar SwingUtilities para asegurar que los hilos no choquen con la UI
                SwingUtilities.invokeLater(() -> log(msg));
            });
            cajeras.add(hilo);
        }

        // Iniciar todos los hilos
        for (CajeraThread cajera : cajeras) {
            cajera.start();
        }

        // Hilo auxiliar para esperar a que todos terminen y calcular el tiempo total
        new Thread(() -> {
            try {
                for (CajeraThread cajera : cajeras) {
                    cajera.join();
                }
                long finalTime = System.currentTimeMillis();
                SwingUtilities.invokeLater(() -> {
                    log("==============================================");
                    log("TIEMPO TOTAL DE PROCESAMIENTO DE TODAS LAS COMPRAS: " + ((finalTime - initialTime) / 1000) + " segundos.");
                    log("==============================================\n");
                });
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Limpiar la cola de pendientes
        clientesPendientes.clear();
        panelClientes.removeAll();
        panelClientes.revalidate();
        panelClientes.repaint();
        contadorClientes = 1;
    }

    private void limpiarTodo() {
        clientesPendientes.clear();
        carritoTemp.clear();
        modelProductosTemp.clear();
        txtLog.setText("");
        panelClientes.removeAll();
        panelClientes.revalidate();
        panelClientes.repaint();
        contadorClientes = 1;
    }

    private void log(String msj) {
        txtLog.append(msj + "\n");
        // Auto-scroll al final
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new FrmSupermercado().setVisible(true);
        });
    }
}
