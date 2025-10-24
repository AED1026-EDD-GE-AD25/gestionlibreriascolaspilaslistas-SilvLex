package miPrincipal.modelo;

import utilerias.Fecha;

public class Pedido {
    private Libro libro;
    private Fecha fechaPedido;

    public Pedido(Libro libro, Fecha fechaPedido) {
        this.libro = libro;
        this.fechaPedido = fechaPedido;
    }

    public Libro getLibro() {
        return libro;
    }

    public Fecha getFechaPedido() {
        return fechaPedido;
    }
}