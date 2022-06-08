package betterplace.betterplacebcd.classes;

import javax.naming.event.ObjectChangeListener;

public class PilhaObj<T> {

    private T[] pilha;
    private int topo;

    public PilhaObj(int capacidade) {
        pilha = (T[]) new Object[capacidade];
        topo = -1;
    }

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return topo == pilha.length-1;
    }

    public void push(T info) {
        if (isFull())
            throw new IllegalStateException();

        pilha[++topo] = info;
    }

    public T pop() {
        if (isEmpty())
            throw new IllegalStateException("Pilha vazia");

        return pilha[topo--];
    }

    public T peek() {
        return pilha[topo];
    }

    public void exibe() {

    }

}
