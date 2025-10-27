package cola;

import java.util.ArrayDeque;

public class Cola<T> {
    private ArrayDeque<T> q = new ArrayDeque<>();

    public boolean encolar(T item) {
        return q.offer(item);
    }

    public T desencolar() {
        return q.isEmpty() ? null : q.poll();
    }

    public T frente() {
        return q.peek();
    }

    public boolean vacia() {
        return q.isEmpty();
    }

    public int tamanio() {
        return q.size();
    }
}