package modelo;

import java.util.List;

public class Cliente {
    private String nombre;
    private List<Producto> productosCarrito;

    public Cliente(String nombre, List<Producto> productosCarrito) {
        this.nombre = nombre;
        this.productosCarrito = productosCarrito;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getProductosCarrito() {
        return productosCarrito;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProductosCarrito(List<Producto> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }
}
