package miPrincipal.iu;
import miPrincipal.servicio.ServicioDatos;
import miPrincipal.modelo.Libro;
import miPrincipal.modelo.Pedido;
import miPrincipal.modelo.Libreria;
import java.util.Scanner;
import utilerias.Fecha;
import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;
import cola.Cola;
import pila.Pila;

public class MenuOpciones{
    static Scanner scanner = new Scanner(System.in);
    static private Libreria libreria = new Libreria();

    public static void agregarLibro(){
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();
        Libro creado = libreria.crearLibro(titulo, autor, isbn);
        if (creado != null)
            System.out.println("Libro agregado: " + creado);
        else
            System.out.println("No fue posible agregar el libro");
    }
    
    public static void mostrarLibros() throws PosicionIlegalException{
        ListaDoble<Libro> lista = libreria.obtenerLibros();
        int n = lista.getTamanio();
        if (n == 0) {
            System.out.println("No hay libros en la lista");
            return;
        }
        System.out.println("Libros en la librería:");
        for (int i = 0; i < n; i++) {
            System.out.println("- " + lista.getValor(i));
        }
    }

    public static void agregarLibroCola(){
        System.out.print("Ingrese el título del libro para la cola: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del libro para la cola: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro para la cola: ");
        String isbn = scanner.nextLine();
        Libro libro = libreria.crearLibro(titulo, autor, isbn);
        boolean ok = libreria.agregarLibroCola(libro);
        System.out.println(ok ? "Libro reservado en cola" : "No se pudo reservar el libro");
    }

    public static Libro obtenerLibroCola(){
        Libro libro = libreria.obtenerLibroCola();
        if (libro != null)
            System.out.println("Se obtuvo de la cola: " + libro);
        else
            System.out.println("La cola está vacía");
        return libro;
    }

    public static void mostrarReservaLibros(){
        ListaDoble<Libro> temp = new ListaDoble<>();
        Libro l;
        boolean vacio = true;
        while ((l = libreria.obtenerLibroCola()) != null) {
            if (vacio) {
                System.out.println("Libros en reserva (cola):");
                vacio = false;
            }
            System.out.println("- " + l);
            temp.agregar(l);
        }
        if (vacio) {
            System.out.println("No hay reservas en la cola");
            return;
        }
        for (int i = 0; i < temp.getTamanio(); i++) {
            try {
                Libro reinsercion = temp.getValor(i);
                libreria.agregarLibroCola(reinsercion);
            } catch (PosicionIlegalException e) {
                System.err.println("Error al reinserter elemento de reserva: " + e.getMessage());
            }
        }
    }

    public static void crearPedido(){
        System.out.print("Ingrese el título del libro para el pedido:");
        String tituloPedido = scanner.nextLine();
        System.out.print("Ingrese el autor del libro para el pedido:");
        String autorPedido = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro para el pedido:");
        String isbnPedido = scanner.nextLine();
        Libro libroPedido = libreria.crearLibro(tituloPedido, autorPedido, isbnPedido);
        Pedido pedido=null;
        if (libroPedido!=null){
            pedido = libreria.crearPedido(libroPedido, new Fecha());
            if (pedido !=null)
                System.out.println("Pedido creado exitosamente: "+pedido);
            else
                System.out.println("No fue posible crear el pedido");
        }else{
            System.out.println("Error: no fue posible crear el Libro");
        }
    }

    public static void devolverLibro() throws PosicionIlegalException{
        System.out.print("Ingrese el ISBN del libro a devolver: ");
        String isbn = scanner.nextLine();
        ListaDoble<Libro> lista = libreria.obtenerLibros();
        Libro encontrado = null;
        for (int i = 0; i < lista.getTamanio(); i++) {
            Libro lb = lista.getValor(i);
            if (lb != null && isbn != null && isbn.equals(lb.getIsbn())) {
                encontrado = lb;
                break;
            }
        }
        if (encontrado == null) {
            System.out.println("No se encontró el libro con ISBN: " + isbn);
            return;
        }
        boolean ok = libreria.devolverLibro(encontrado);
        System.out.println(ok ? "Libro devuelto correctamente" : "No se pudo devolver el libro");
    }

    public static Libro eliminarUltimoLibro() throws PosicionIlegalException{
        Libro eliminado = libreria.eliminarUltimoLibro();
        if (eliminado != null)
            System.out.println("Se eliminó el último libro: " + eliminado);
        else
            System.out.println("No hay libros para eliminar");
        return eliminado;
    }

    public static void deshacerEliminarLibro(){
        try {
            Libro restaurado = libreria.deshacerEliminarLibro();
            if (restaurado != null)
                System.out.println("Se restauró el libro eliminado: " + restaurado);
            else
                System.out.println("No hay eliminación para deshacer");
        } catch (PosicionIlegalException e) {
            System.out.println("Error al deshacer eliminación: " + e.getMessage());
        }
    }
    
}