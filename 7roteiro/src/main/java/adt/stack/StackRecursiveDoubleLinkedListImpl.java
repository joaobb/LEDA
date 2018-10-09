package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

    protected DoubleLinkedList<T> top;
    protected int size;

    public StackRecursiveDoubleLinkedListImpl(int size) {
        if (size < 0) size = 0;
        this.size = size;
        this.top = new RecursiveDoubleLinkedListImpl<T>();
    }

    @Override
    public void push(T element) throws StackOverflowException {
        if (this.isFull()) throw new StackOverflowException();
        this.top.insert(element);
        this.size++;
    }

    @Override
    public T pop() throws StackUnderflowException {
        if (this.isEmpty()) throw new StackUnderflowException();
        T element = this.top();
        this.top.removeLast();
        return element;
    }

    @Override
    public T top() {
        return ((RecursiveDoubleLinkedListImpl<T>) top).getData();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return this.size == this.top.size();
    }

}
