package modelo;

public class Producto {
    private String nombre;
    private double costo;
    private int tiempoProcesamientoSegundos;

    public Producto(String nombre, double costo, int tiempoProcesamientoSegundos) {
        this.nombre = nombre;
        this.costo = costo;
        this.tiempoProcesamientoSegundos = tiempoProcesamientoSegundos;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCosto() {
        return costo;
    }

    public int getTiempoProcesamientoSegundos() {
        return tiempoProcesamientoSegundos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setTiempoProcesamientoSegundos(int tiempoProcesamientoSegundos) {
        this.tiempoProcesamientoSegundos = tiempoProcesamientoSegundos;
    }
}
