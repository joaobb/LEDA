package adt.queue;

public class CircularQueue<T> implements Queue<T> {

    private T[] array;
    private int tail;
    private int head;
    private int elements;

    public CircularQueue(int size) {
        array = (T[]) new Object[size];
        head = -1;
        tail = -1;
        elements = 0;
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {
        if (this.isFull()) throw new QueueOverflowException();
        if (element != null) {
            this.tail = ++this.tail % this.array.length;
            this.array[this.tail] = element;
            }
        if (this.head == -1) this.head = this.tail;
        this.elements++;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (this.isEmpty()) throw new QueueUnderflowException();
        T retorno = this.array[head];
        this.array[head] = null;
        this.head = ++this.tail % this.array.length;
        this.elements--;
        return retorno;
    }

    @Override
    public T head() {
        T retorno;
        if (this.isEmpty()) retorno = null;
        else retorno = this.array[this.head % (this.array.length - 1)];
        return retorno;
    }

    @Override
    public boolean isEmpty() {
        return this.elements == 0;
    }

    @Override
    public boolean isFull() {
        return this.elements == this.array.length;
    }

}
