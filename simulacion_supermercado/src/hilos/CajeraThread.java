package hilos;

import modelo.Cliente;
import modelo.Producto;

import java.util.function.Consumer;

public class CajeraThread extends Thread {
    private String nombreCajera;
    private Cliente cliente;
    private long initialTime;
    private Consumer<String> logger;

    public CajeraThread(String nombreCajera, Cliente cliente, long initialTime, Consumer<String> logger) {
        this.nombreCajera = nombreCajera;
        this.cliente = cliente;
        this.initialTime = initialTime;
        this.logger = logger;
    }

    private void log(String mensaje) {
        if (logger != null) {
            logger.accept(mensaje);
        } else {
            System.out.println(mensaje);
        }
    }

    @Override
    public void run() {
        log("La cajera " + this.nombreCajera + " COMIENZA A PROCESAR AL CLIENTE " 
                + this.cliente.getNombre() + " EN EL TIEMPO: " 
                + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg");

        double costoTotal = 0;
        int tiempoTotalSegundos = 0;

        for (int i = 0; i < this.cliente.getProductosCarrito().size(); i++) {
            Producto producto = this.cliente.getProductosCarrito().get(i);
            
            this.esperarXsegundos(producto.getTiempoProcesamientoSegundos());
            
            costoTotal += producto.getCosto();
            tiempoTotalSegundos += producto.getTiempoProcesamientoSegundos();
            
            log("Cajera " + this.nombreCajera + " procesando producto " + (i + 1) + ": " 
                    + producto.getNombre() + " (Costo: $" + producto.getCosto() + ") -> Tiempo en procesar: " 
                    + producto.getTiempoProcesamientoSegundos() + " seg " 
                    + "-> Tiempo transcurrido: " + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg");
        }

        log("La cajera " + this.nombreCajera + " HA TERMINADO DE PROCESAR AL CLIENTE " 
                + this.cliente.getNombre() + " EN EL TIEMPO: " 
                + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg.\n"
                + "   > Costo total compra: $" + costoTotal 
                + " | Tiempo total empleado: " + tiempoTotalSegundos + " seg.");
    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
