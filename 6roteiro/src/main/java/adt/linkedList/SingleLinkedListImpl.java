package adt.linkedList;

import java.util.Arrays;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

    protected SingleLinkedListNode<T> head;

    public SingleLinkedListImpl() {
        this.head = new SingleLinkedListNode<T>();
    }

    @Override
    public boolean isEmpty() {
        return this.getHead().isNIL();
    }

    @Override
    public int size() {
        int size = 0;

        if (!this.isEmpty()) {
            SingleLinkedListNode<T> aux = this.getHead();

            while (!aux.isNIL()) {
                size++;
                aux = aux.getNext();
            }
        }
        return size;
    }

    @Override
    public T search(T element) {
        T retorno = null;

        if (element != null && !this.isEmpty()) {
            SingleLinkedListNode<T> aux = this.getHead();
            while (!aux.isNIL()) {
                if (aux.getData().equals(element)) {
                    retorno = aux.getData();
                    break;
                } else
                    aux = aux.getNext();
            }
        }

        return retorno;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            SingleLinkedListNode<T> aux = this.head;

            if (this.isEmpty()) {
                SingleLinkedListNode<T> newHead = new SingleLinkedListNode<>(element, new SingleLinkedListNode<>());
                this.setHead(newHead);
            } else {
                while (!aux.isNIL()) {
                    aux = aux.getNext();
                }

                aux.setData(element);
                aux.setNext(new SingleLinkedListNode<>());
            }
        }
    }

    @Override
    public void remove(T element) {

        if (element != null && !this.isEmpty()) {

            if (this.head.getData().equals(element)) {
                this.head = this.head.getNext();
            } else {
                SingleLinkedListNode<T> aux = this.head;
                SingleLinkedListNode<T> previous = new SingleLinkedListNode<>();
                while (!aux.isNIL() && !aux.getData().equals(element)) {
                    previous = aux;
                    aux = aux.next;
                }
                if (!aux.isNIL()) {
                    previous.next = aux.next;
                }
            }
        }
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[this.size()];

        SingleLinkedListNode<T> aux = this.head;
        int count = 0;

        while (!aux.isNIL()) {
            result[count++] = aux.data;
            aux = aux.getNext();
        }
        return result;
    }

    public void inverter() {
        SingleLinkedListNode zap;
        SingleLinkedListNode aux = this.head;
        SingleLinkedListNode newHead = new SingleLinkedListNode();

        while (!aux.isNIL()) {
            zap = aux.next;
            aux.setNext(newHead);
            newHead = aux;
            aux = zap;
        }
        this.head = newHead;
        System.out.println(Arrays.toString(this.toArray()));
    }

    public SingleLinkedListNode<T> getHead() {
        return head;
    }

    public void setHead(SingleLinkedListNode<T> head) {
        this.head = head;
    }

    public static void main(String[] args) {
        LinkedList<Integer> lista1;

        lista1 = new SingleLinkedListImpl<>();

        // Lista com 3 elementos.
        lista1.insert(3);
        lista1.insert(2);
        lista1.insert(1);

        ((SingleLinkedListImpl<Integer>) lista1).inverter();

    }

}
