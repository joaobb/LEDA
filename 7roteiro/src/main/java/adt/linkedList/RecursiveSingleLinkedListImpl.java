package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return (this.data == null);
	}

	@Override
	public int size() {
		if (isEmpty()) return 0;
		return 1 + this.next.size();
	}

	@Override
	public T search(T element) {
		T retorno = null;
		if (element != null && !this.isEmpty()) {
			if (this.data.equals(element))
				retorno = this.data;
			else
				retorno = this.next.search(element);
		}
		return retorno;
	}

	@Override
	public void insert(T element) {
		if (element != null)
			if (isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<>());
			} else {
				this.next.insert(element);
			}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (this.getNext().data.equals(element)) {
				this.setNext(this.getNext().getNext());
			}
		}
	}

	public List<T> toArrayAux(List<T> array, RecursiveSingleLinkedListImpl<T> RecursiveSingleLinkedList) {
		if (!this.isEmpty()) {
			array.add(this.data);
			this.getNext().toArrayAux(array, this);
		}
		else return array;
		return array;
	}
	@Override
	public T[] toArray() {
		List<T> result = new ArrayList<>();
		toArrayAux(result, this);
		return result.toArray((T[]) new Object[this.size()]);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
