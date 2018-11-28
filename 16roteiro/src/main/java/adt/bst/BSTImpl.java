package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.getRoot());
	}

	public int height(BTNode<T> node) {
		int altura = -1;
		if (!node.isEmpty())
			altura = Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight())) + 1;
		return altura;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.getRoot());
	}

	private BSTNode<T> search(T element, BTNode<T> node) {
		BSTNode<T> result = new BSTNode<>();

		if (element != null && !node.isEmpty()) {

			if (node.getData().equals(element))
				result = (BSTNode<T>) node;
			else if (node.getData().compareTo(element) < 0)
				result = search(element, node.getRight());
			else
				result = search(element, node.getLeft());
		}
		return result;

	}

	@Override
	public void insert(T element) {
		if (element != null)
			this.insert(element, root);
	}

	public void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else if (element.compareTo(node.getData()) > 0)
			insert(element, (BSTNode<T>) node.getRight());
		else
			insert(element, (BSTNode<T>) node.getLeft());

	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if (!node.isEmpty()) {
			if (node.getRight().isEmpty()) {
				result = node;
			} else
				result = maximum((BSTNode<T>) node.getRight());
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = null;

		//Node isnt empty
		if (!node.isEmpty()) {
			//Doesnt have left leg
			if (node.getLeft().isEmpty()) {
				result = node;
			} else {
				result = minimum((BSTNode<T>) node.getLeft());
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty()) {
			node = null;
		} else {
			node = sucessor(node);
		}
		return node;
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> result = minimum((BSTNode<T>) node.getRight());

		if (result == null) {
			result = (BSTNode<T>) node.getParent();
			while (result != null && result.getData().compareTo(node.getData()) < 0) {
				result = (BSTNode<T>) result.getParent();
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty()) {
			node = null;
		} else {
			node = predecessor(node);
		}
		return node;
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> result = maximum((BSTNode<T>) node.getLeft());

		if (result == null) {
			result = (BSTNode<T>) node.getParent();
			while (result != null && result.getData().compareTo(node.getData()) > 0) {
				result = (BSTNode<T>) result.getParent();
			}
		}
		return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.isLeaf()) {
			node.setData(null);
		} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			if (node.getParent() == null) {
				node.getLeft().setParent(null);
				root = (BSTNode<T>) node.getLeft();
			} else if (node.getParent().getLeft().equals(node)) {
				node.getParent().setLeft(node.getLeft());
			} else {
				node.getParent().setRight(node.getLeft());
			}
			node.getLeft().setParent(node.getParent());
		} else if (!node.getRight().isEmpty() && node.getLeft().isEmpty()) {
			if (node.getParent() == null) {
				node.getRight().setParent(null);
				root = (BSTNode<T>) node.getRight();
			} else if (node.getParent().getLeft().equals(node)) {
				node.getParent().setLeft(node.getRight());
			} else {
				node.getParent().setRight(node.getRight());
			}
			node.getRight().setParent(node.getParent());
		} else {
			BSTNode<T> nodeAux = this.sucessor(node.getData());
			node.setData(nodeAux.getData());
			remove(nodeAux);
		}
	}

	@Override
	public T[] preOrder() {
		T[] arreio = (T[]) new Comparable[this.size()];

		preOrder(arreio, 0, getRoot());

		return arreio;
	}

	private int preOrder(T[] arreio, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			arreio[i++] = node.getData();
			i = preOrder(arreio, i, (BSTNode<T>) node.getLeft());
			i = preOrder(arreio, i, (BSTNode<T>) node.getRight());
		}
		return i;
	}

	@Override
	public T[] order() {
		T[] arreio = (T[]) new Comparable[this.size()];

		order(arreio, 0, getRoot());

		return arreio;
	}

	private int order(T[] arreio, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			i = order(arreio, i, (BSTNode<T>) node.getLeft());
			arreio[i++] = node.getData();
			i = order(arreio, i, (BSTNode<T>) node.getRight());
		}
		return i;
	}
	@Override
	public T[] postOrder() {
		T[] arreio = (T[]) new Comparable[this.size()];

		postOrder(arreio, 0, getRoot());

		return arreio;
	}

	private int postOrder(T[] arreio, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			arreio[i++] = node.getData();
			i = preOrder(arreio, i, (BSTNode<T>) node.getRight());
			i = preOrder(arreio, i, (BSTNode<T>) node.getLeft());
		}
		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

	private BSTNode<T> NILBilder() {
		return new BSTNode<>();
	}
}