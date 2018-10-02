package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
        DoubleLinkedList<T> {

    protected DoubleLinkedListNode<T> last;

    @Override
    public void insertFirst(T element) {
        if (element != null && this.head instanceof DoubleLinkedList) {
            DoubleLinkedListNode<T> currentHead = (DoubleLinkedListNode<T>) this.getHead();
            DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
            newHead.setNext(currentHead);
            newHead.setPrevious(new DoubleLinkedListNode<>());
            newHead.setData(element);
            currentHead.setPrevious(newHead);
            this.setHead(newHead);

            if (this.head.isNIL()) {
                this.last = newHead;
            }
            head = newHead;
        }
    }

    @Override
    public void removeFirst() {
        if (this.getHead() instanceof DoubleLinkedList) {
            DoubleLinkedListNode<T> currentHead = (DoubleLinkedListNode<T>) this.getHead();

            if (!currentHead.isNIL()) {
                currentHead = (DoubleLinkedListNode<T>) currentHead.getNext();
                if (currentHead.isNIL()) {
                    last = currentHead;
                }
                currentHead.setPrevious(new DoubleLinkedListNode<>());
            }
        }
    }

    @Override
    public void removeLast() {
        if (!this.last.isNIL()) {
            this.last = this.last.getPrevious();
            if (this.last.isNIL()) {
                this.head = last;
            }
            this.last.next = new DoubleLinkedListNode<>();
        }
    }

    public DoubleLinkedListNode<T> getLast() {
        return last;
    }

    public void setLast(DoubleLinkedListNode<T> last) {
        this.last = last;
    }

}
