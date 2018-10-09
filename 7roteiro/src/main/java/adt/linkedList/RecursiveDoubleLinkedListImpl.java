package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
        RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

    protected RecursiveDoubleLinkedListImpl<T> previous;

    public RecursiveDoubleLinkedListImpl() {

    }

    public RecursiveDoubleLinkedListImpl(T data,
                                         RecursiveSingleLinkedListImpl<T> next,
                                         RecursiveDoubleLinkedListImpl<T> previous) {
        super(data, next);
        this.previous = previous;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            if (this.isEmpty()) {
                this.data = element;
                this.setNext(new RecursiveDoubleLinkedListImpl<T>());
            } else {
                this.next.insert(element);
            }
        }
    }

    @Override
    public void insertFirst(T element) {
        if (element != null) {
            RecursiveDoubleLinkedListImpl<T> actual = new RecursiveDoubleLinkedListImpl<>(this.getData(), this.getNext(), this);

            this.setNext(new RecursiveDoubleLinkedListImpl<T>());
            this.setData(element);
            this.setNext(actual);
        }
    }

    @Override
    public void removeFirst() {
        if (!this.isEmpty()) {
            this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
            this.setData(this.getNext().getData());

            if (isEmpty()) this.setNext(new RecursiveDoubleLinkedListImpl<T>());
            else this.setNext((this.getNext().getNext()));
        }
    }

    @Override
    public void removeLast() {
        if (!isEmpty()) {
            if (this.getNext().isEmpty()) {
                this.setData(null);
                this.setNext(new RecursiveDoubleLinkedListImpl<T>());
            } else {
                ((RecursiveDoubleLinkedListImpl<T>) next).removeLast();
            }
        }
    }

    public RecursiveDoubleLinkedListImpl<T> getPrevious() {
        return previous;
    }

    public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
        this.previous = previous;
    }

    public T getData() {
        return this.data;
    }
}
