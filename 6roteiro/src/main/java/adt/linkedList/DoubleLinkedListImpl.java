package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
        DoubleLinkedList<T> {

    protected DoubleLinkedListNode<T> last;

    public DoubleLinkedListImpl() {
        this.last = new DoubleLinkedListNode<>();
        this.head = last;
    }

    @Override
    public void insert(T element) {
        if (element != null) {

            DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), this.last);
            this.last = node;


        }
    }

    @Override
    public void insertFirst(T element) {
        if (element != null) {
            DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>(element, null, new DoubleLinkedListNode<>());
            newHead.setNext(head);

            if (head instanceof DoubleLinkedListNode)
                ((DoubleLinkedListNode<T>) head).setPrevious(newHead);

            if (head.isNIL())
                this.last = newHead;

            this.setHead(newHead);
        }
    }

    @Override
    public void removeFirst() {
        if (!this.head.isNIL())
            this.setHead(this.head.next);
        else
            this.last = (DoubleLinkedListNode<T>) this.head;

        if (this.getHead() instanceof DoubleLinkedList)
            ((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<>());
    }


    @Override
    public void removeLast() {
        if (this.last == null) this.last = new DoubleLinkedListNode<>();

        if (!this.last.isNIL()) {
            this.last = last.getPrevious();

            if (this.last.isNIL())
                head = last;

            last.next = new DoubleLinkedListNode<>();
        }
    }

    public DoubleLinkedListNode<T> getLast() {
        return last;
    }

    public void setLast(DoubleLinkedListNode<T> last) {
        this.last = last;
    }

}
