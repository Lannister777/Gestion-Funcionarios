import vista.FrmSupermercado;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Lanzar la interfaz gráfica en lugar de la simulación de consola
        SwingUtilities.invokeLater(() -> {
            FrmSupermercado frm = new FrmSupermercado();
            frm.setVisible(true);
        });
    }
}
