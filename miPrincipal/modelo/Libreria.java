package miPrincipal.modelo;

import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;
import pila.Pila;
import cola.Cola;
import utilerias.Fecha;
import miPrincipal.servicio.ServicioDatos;
import java.util.Scanner;

public class Libreria{
    ServicioDatos dataService;
    ListaDoble<Libro> listaLibros;
    Cola<Libro> colaLibros;
    Pila<Libro> pilaLibrosEliminados;
    Scanner scanner; 

    public Libreria(){
        dataService = new ServicioDatos();
        scanner = new Scanner(System.in);
        listaLibros = new ListaDoble<>();
        colaLibros = new Cola<>();
        pilaLibrosEliminados = new Pila<>();
    }

    public void agregarLibro(String titulo, String autor, String isbn){
        Libro nuevoLibro = new Libro(titulo, autor, isbn);
        dataService.agregarALista(nuevoLibro);
        listaLibros.agregar(nuevoLibro);
    }

    public void agregarLibro(Libro libro){
        if (libro == null) return;
        listaLibros.agregar(libro);
        dataService.agregarALista(libro);
    }

    public ListaDoble<Libro> obtenerLibros(){
       return listaLibros;
    }

    public boolean agregarLibroCola(Libro libro){
        if (libro == null) return false;
        boolean ok = colaLibros.encolar(libro);
        if (ok) dataService.agregarACola(libro);
        return ok;
    }

    public Libro obtenerLibroCola(){
        Libro l = colaLibros.desencolar();
        if (l != null) dataService.removerDeCola();
        return l;
    }

    public Libro obtenerLibroPila(){
        return pilaLibrosEliminados.cima();
    }

    public Libro crearLibro(String titulo, String autor, String isbn){
        Libro libro = new Libro(titulo, autor, isbn);
        agregarLibro(libro);
        return libro;
    }

    public void crearLibro(){
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();
        agregarLibro(titulo, autor, isbn);
    }

    public Pedido crearPedido(Libro libro, Fecha fecha){
        if (libro == null || fecha == null) return null;
        return new Pedido(libro, fecha);
    }

    public boolean devolverLibro(Libro libro) throws PosicionIlegalException {
        if (libro == null) return false;
        int tamanio = listaLibros.getTamanio();
        ListaDoble<Libro> nueva = new ListaDoble<>();
        boolean eliminado = false;
        for (int i = 0; i < tamanio; i++) {
            Libro actual = listaLibros.getValor(i);
            if (!eliminado && libro.equals(actual)) {
                eliminado = true;
                continue; 
            }
            nueva.agregar(actual);
        }
        listaLibros = nueva;
        return eliminado;
    }

    public Libro eliminarUltimoLibro() throws PosicionIlegalException{
        int tamanio = listaLibros.getTamanio();
        if (tamanio == 0) return null;
        int ultimoIdx = tamanio - 1;
        Libro eliminado = listaLibros.getValor(ultimoIdx);
        ListaDoble<Libro> nueva = new ListaDoble<>();
        for (int i = 0; i < ultimoIdx; i++) {
            nueva.agregar(listaLibros.getValor(i));
        }
        listaLibros = nueva;

        pilaLibrosEliminados.apilar(eliminado);
        return eliminado;
    }

    public Libro deshacerEliminarLibro() throws PosicionIlegalException{
        Libro desdePila = pilaLibrosEliminados.retirar();
        if (desdePila == null) return null;
        listaLibros.agregar(desdePila);
        return desdePila;
    }

    public void buscarLibro(String isbn) {
    }

}