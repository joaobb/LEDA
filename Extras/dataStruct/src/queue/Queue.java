package queue;

public interface Queue <T> {
    public T head();
    public boolean isEmpty();
    public boolean isFull();
    public void shipLeft();
    public void enqueue(T element) throws RuntimeException;
    public T dequeue() throws RuntimeException;

}
