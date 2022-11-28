package com.bp.feedbcd.classesed;

public class FilaObj<T> {

    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        fila = (T[]) new Object[capacidade];
        tamanho = 0;
    }
    public boolean isEmpty() {
        return tamanho == 0;
    }
    public boolean isFull() {
        return tamanho == fila.length;
    }
    public void insert(T info) {
        if(isFull())
            throw new IllegalStateException();

        fila[tamanho++] = info;
    }
    public T peek() {
        return fila[0];
    }
    public T poll() {
        T primeiro = peek();

        for (int i = 0; i < tamanho-1; i++) {
            fila[i] = fila[i+1];
        }
        fila[--tamanho] = null;
       return primeiro;
    }
    public void exibe() {
        for (int i = 0; i < tamanho; i++)
            System.out.println(fila[i]);
    }
    public int getTamanho() {
        return tamanho;
    }
}