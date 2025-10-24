package pila;

import java.util.ArrayDeque;

public class Pila<T> {
    private ArrayDeque<T> stack = new ArrayDeque<>();

    public void apilar(T item) {
        stack.push(item);
    }

    public T retirar() {
        return stack.isEmpty() ? null : stack.pop();
    }

    public T cima() {
        return stack.isEmpty() ? null : stack.peek();
    }

    public boolean vacia() {
        return stack.isEmpty();
    }

    public int tamanio() {
        return stack.size();
    }
}