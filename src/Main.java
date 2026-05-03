import vista.FrmFuncionarios;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase principal que inicializa e invoca la interfaz gráfica de la aplicación.
 */
public class Main {
    public static void main(String[] args) {
        // Ejecución en el hilo seguro de Swing
        SwingUtilities.invokeLater(() -> {
            try {
                // Para que la vista tome el estilo nativo del sistema operativo (Opcional pero recomendable)
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Si falla, Swing usa el estilo por defecto, no detiene la ejecución
            }
            
            FrmFuncionarios vista = new FrmFuncionarios();
            vista.setVisible(true);
        });
    }
}
