package betterplace.betterplacebcd.classes.ed;

public class ListaLigada {
    private Node head;

    public ListaLigada() {
        this.head = new Node(null);
        ;
    }

    public Node getHead() {
        return head;
    }

    void insereNode(Integer valor) {
        Node novoNode = new Node(valor);
        novoNode.setNext(head.getNext()); //Pega qual o próximo item da lista (Estava ligado ao head)
        head.setNext(novoNode); //Define o novo item como sendo o proximo do head
    }

    void exibe() {
        Node atual = head.getNext();
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getNext();
        }
    }

    Node buscaNode(long valor) {
        Node atual = head.getNext();
        while (atual != null) {
            if (atual.getInfo() == valor)
                return atual;
            atual = atual.getNext();
        }
        return null;
    }

    boolean removeNode(long valor) {
        Node atual = head.getNext();
        Node anterior = head;

        while (atual != null) {
            if (atual.getInfo() == valor) {
                anterior.setNext(atual.getNext());
                return true;
            }
            anterior = atual;
            atual = atual.getNext();
        }
        return false;
    }

    public int getTamanho() {
        int tamanho = 0;
        Node atual = head.getNext();
        while (atual != null) {
            tamanho++;
            atual = atual.getNext();
        }

        return tamanho;
    }

    public void inserirAposPrimeiroImpar(Integer num) {
        Node novo = new Node(num);
        Node atual = head.getNext();

        while (atual != null) {
            if (atual.getInfo() % 2 != 0) {
                Node proximo = atual.getNext();
                atual.setNext(novo);
                novo.setNext(proximo);
                return;
            }

            atual = atual.getNext();
        }
        head.setNext(novo);
    }

    public long getElemento(long indice) {
        Node atual = head.getNext();

        for (long i = 0; i <= indice; i++) {
            if (i == indice)
                return atual.getInfo();
            atual = atual.getNext();
        }

        throw new IndexOutOfBoundsException();
    }

    public boolean removeOcorrencias(long num) {
        Node atual = head.getNext();
        Node anterior = head;
        Node salvaAnterior = null;
        boolean removido = false;

        while (atual != null) {
            if (atual.getInfo() == num) {
                salvaAnterior = anterior;
                anterior.setNext(atual.getNext());
                removido = true;
            }
            if (salvaAnterior != null)
                anterior = salvaAnterior;
            else
                anterior = atual;

            atual = atual.getNext();
            salvaAnterior = null;
        }
        return removido;
    }

    public void arrayToList(Integer[] vetor) {
        if (head.getNext() != null)
            throw new IllegalStateException();

        for (int i = 0; i < vetor.length; i++)
            insereNode(vetor[i]);
    }

    public long[] listToArray(Node node){
        int tamanho = getTamanho(node);
        long[] array = new long[tamanho];
        Node atual = node;

        for (int i = 0; i < tamanho; i++) {
            array[i] = atual.getInfo();
            atual = atual.getNext();
        }

        return array;
    }

    public int getTamanho(Node node){
        int tamanho = 0;
        Node atual = node;
        while (atual != null) {
            tamanho++;
            atual = atual.getNext();
        }

        return tamanho;
    }
}