package adt.bst.extended;
import adt.bst.BSTImpl;
import adt.bst.BSTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		ArrayList<T> aux = new ArrayList<>(Arrays.asList(array));
		Collections.sort(aux, getComparator());
		return (T[]) aux.toArray();
	}

	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.isEmpty()) return array;
		reverseOrder(array, root, 0);
		return array;
	}

	private int reverseOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.getRight().isEmpty() && node.getRight() instanceof BSTNode) {
			index = reverseOrder(array, (BSTNode<T>) node.getRight(), index);
		}

		array[index++] = node.getData();

		if (!node.getLeft().isEmpty() && node.getLeft() instanceof BSTNode) {
			index = reverseOrder(array, (BSTNode<T>) node.getLeft(), index);
		}

		return index;
	}
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
