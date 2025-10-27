package listaDoble;

public class ListaDoble<T> {
    // atributos
    private Nodo<T> cabeza;
    private int tamanio;

    // constructor por defecto
    public ListaDoble() {
        cabeza = null;
        tamanio = 0;
    }

    public int getTamanio() {
        return tamanio;
    }

    // Métodos personalizados
    // confirma si la lista está vacía
    public boolean esVacia() {
        return cabeza == null;
    }

    // agrega un nuevo nodo al final de la lista
    public void agregar(T valor) {
        Nodo<T> nuevo = new Nodo<>();
        nuevo.setValor(valor);
        if (esVacia()) {
            cabeza = nuevo;
        } else {
            Nodo<T> aux = cabeza;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        tamanio++;
    }

    /*
     * Inserta un nuevo nodo en la lista
     * @param valor: valor a agregar
     * @param pos: indica la posicion en donde se insertará el nodo
     * @throws PosicionIlegalException en caso de que la posicion no exista
     */
    public void insertar(T valor, int pos) throws PosicionIlegalException {
        if (pos >= 0 && pos <= tamanio) {
            Nodo<T> nuevo = new Nodo<>();
            nuevo.setValor(valor);
            if (pos == 0) {
                nuevo.setSiguiente(cabeza);
                cabeza = nuevo;
            } else {
                Nodo<T> aux = cabeza;
                for (int i = 0; i < pos - 1; i++) {
                    aux = aux.getSiguiente();
                }
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
            }
            tamanio++;
        } else {
            throw new PosicionIlegalException();
        }
    }

    /*
     * Elimina un nodo de una determinada posicion
     * @param pos: posicion a eliminar
     * @throws PosicionIlegalException
     */
    public T remover(int pos) throws PosicionIlegalException {
        if (pos >= 0 && pos < tamanio) {
            T valor;
            if (pos == 0) {
                valor = cabeza.getValor();
                cabeza = cabeza.getSiguiente();
            } else {
                Nodo<T> aux = cabeza;
                for (int i = 0; i < pos - 1; i++) {
                    aux = aux.getSiguiente();
                }
                Nodo<T> eliminar = aux.getSiguiente();
                valor = eliminar.getValor();
                aux.setSiguiente(eliminar.getSiguiente());
            }
            tamanio--;
            return valor;
        } else {
            throw new PosicionIlegalException();
        }
    }

    /*
     * Elimina un nodo de la lista buscándolo por su valor, si lo encuentra retorna
     * la posición y lo elimina, si no lo encuentra retorna -1
     */
    public int remover(T valor) throws PosicionIlegalException {
        int posicion = 0;
        Nodo<T> actual = cabeza;
        Nodo<T> anterior = null;

        while (actual != null) {
            T v = actual.getValor();
            if ((valor == null && v == null) || (valor != null && valor.equals(v))) {
                if (anterior == null) {
                    cabeza = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                tamanio--;
                return posicion;
            }
            anterior = actual;
            actual = actual.getSiguiente();
            posicion++;
        }

        return -1;
    }

    /*
     * Devuelve el valor de una determinada posicion
     * @param pos: posicion
     * @return : el valor de tipo T
     * @throws PosicionIlegalException
     */
    public T getValor(int pos) throws PosicionIlegalException {
        if (pos >= 0 && pos < tamanio) {
            Nodo<T> aux = cabeza;
            for (int i = 0; i < pos; i++) {
                aux = aux.getSiguiente();
            }
            return aux.getValor();
        } else { // es una posicion inválida
            throw new PosicionIlegalException();
        }
    }

    public void limpiar() {
        cabeza = null;
        tamanio = 0;
    }

    /*
     * Regresa todos los datos de la lista en forma de String
     */
    @Override
    public String toString() {
        if (esVacia()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo<T> aux = cabeza;
        while (aux != null) {
            sb.append(aux.getValor());
            if (aux.getSiguiente() != null) {
                sb.append(", ");
            }
            aux = aux.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }

    /*
     * busca un valor en la lista
     * @return true si contiene ese valor
     * si no regresa false
     */
    public boolean contiene(T valor) {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            T v = aux.getValor();
            if ((valor == null && v == null) || (valor != null && valor.equals(v))) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }
}