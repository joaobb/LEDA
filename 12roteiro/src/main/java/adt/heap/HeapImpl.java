package adt.heap;

import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator.
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

    protected T[] heap;
    protected int index = -1;
    /**
     * O comparador é utilizado para fazer as comparações da heap. O ideal é
     * mudar apenas o comparator e mandar reordenar a heap usando esse
     * comparator. Assim os metodos da heap não precisam saber se vai funcionar
     * como max-heap ou min-heap.
     */
    protected Comparator<T> comparator;

    private static final int INITIAL_SIZE = 20;
    private static final int INCREASING_FACTOR = 10;

    /**
     * Construtor da classe. Note que de inicio a heap funciona como uma
     * min-heap.
     */
    @SuppressWarnings("unchecked")
    public HeapImpl(Comparator<T> comparator) {
        this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
        this.comparator = comparator;
    }

    // /////////////////// METODOS IMPLEMENTADOS
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Deve retornar o indice que representa o filho a esquerda do elemento
     * indexado pela posicao i no vetor
     */
    private int left(int i) {
        return (i * 2 + 1);
    }

    /**
     * Deve retornar o indice que representa o filho a direita do elemento
     * indexado pela posicao i no vetor
     */
    private int right(int i) {
        return (i * 2 + 1) + 1;
    }

    @Override
    public boolean isEmpty() {
        return (index == -1);
    }

    @Override
    public T[] toArray() {
        ArrayList<T> resp = new ArrayList<T>();
        for (T elem : this.heap) {
            if (elem != null) {
                resp.add(elem);
            }
        }
        return (T[]) resp.toArray(new Comparable[0]);
    }

    // ///////////// METODOS A IMPLEMENTAR

    /**
     * Valida o invariante de uma heap a partir de determinada posicao, que pode
     * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
     * (comparados usando o comparator) elementos na parte de cima da heap.
     */
    private void heapify(int position) {
        int left = left(position);
        int right = right(position);
        int largest;
        if (left < this.size() && getComparator().compare(getHeap()[left], this.getHeap()[position]) > 0)
            largest = left;
        else largest = position;
        if (right < this.size() && getComparator().compare(this.getHeap()[right], this.getHeap()[largest]) > 0)
            largest = right;

        if (largest != position) {
            Util.swap(getHeap(), largest, position);
            this.heapify(largest);
        }
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            // ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
            if (index == heap.length - 1) {
                heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
            }
            int index = ++this.index;

            while (index > 0 && getComparator().compare(this.heap[this.parent(index)], element) < 0) {
                this.getHeap()[index] = this.getHeap()[parent(index)];
                index = parent(index);
            }
            this.getHeap()[index] = element;
        }
    }

    @Override
    public void buildHeap(T[] array) {
        this.heap = Arrays.copyOf(array, array.length);
        this.index = array.length - 1;
        for (int i = array.length / 2; i >= 0; i--) {
            this.heapify(i);
        }
    }

    @Override
    public T extractRootElement() {
        if (this.size() < 0) {
            throw new RuntimeException("heap underflow");
        }
        T max = this.getHeap()[0];
        this.getHeap()[0] = this.getHeap()[this.index];
        this.index--;
        this.heapify(0);
        this.getHeap()[index + 1] = null;
        return max;
    }

    @Override
    public T rootElement() {
        T result = null;
        if (size() != 0) result = this.getHeap()[0];
        return result;
    }

    @Override
    public T[] heapsort(T[] array) {
        Comparator<T> originalComp = this.getComparator();

        this.comparator = Comparator.naturalOrder();

        buildHeap(array);

        for (int i = array.length - 1; i >= 1; i--) {
            Util.swap(this.getHeap(), 0, i);
            this.index--;
            this.heapify(0);
        }

        array = Arrays.copyOf(this.getHeap(), array.length);
        this.index = -1;
        this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
        this.comparator = originalComp;
        return array;
    }

    @Override
    public int size() {
        return this.index + 1;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public T[] getHeap() {
        return heap;
    }

}
