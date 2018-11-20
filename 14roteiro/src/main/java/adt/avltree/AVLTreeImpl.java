package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * Performs consistency validations within a AVL Tree instance
 *
 * @param <T>
 * @author Claudio Campelo
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	@Override
	public void insert(T element) {
		insert(element, getRoot());
	}

	public void insert(T element, BSTNode<T> node) {
		if (element != null) {
			if (node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BSTNode<T>());
				node.setRight(new BSTNode<T>());
				node.getLeft().setParent(node);
				node.getRight().setParent(node);
			} else {
				if (element.compareTo(node.getData()) > 0) {
					insert(element, (BSTNode<T>) node.getRight());
				} else {
					insert(element, (BSTNode<T>) node.getLeft());
				}
			}
			this.rebalance(node);
			this.preOrder();
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = super.search(element);
		if (!node.isEmpty()) {
			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.isLeaf()) {
			node.setData(null);
			this.rebalanceUp(node);
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
			this.rebalanceUp(node);
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
			this.rebalanceUp(node);
		} else {
			BSTNode<T> nodeAux = sucessor(node.getData());
			node.setData(nodeAux.getData());
			remove(nodeAux);
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int size = 0;
		if (!node.isEmpty()) {
			size = super.height((BSTNode<T>) node.getLeft()) - super.height((BSTNode<T>) node.getRight());
		}
		return size;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (!node.isEmpty() && !(Math.abs(balance) <= 1)) {
			if (balance > 0) {
				int banlaceFilho = calculateBalance((BSTNode<T>) node.getLeft());
				if (banlaceFilho < 0) {
					leftRotation((BSTNode<T>) node.getLeft());
				}
				rightRotation(node);
			} else {
				int banlaceFilho = calculateBalance((BSTNode<T>) node.getRight());
				if (banlaceFilho > 0) {
					rightRotation((BSTNode<T>) node.getRight());
				}
				leftRotation(node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (!this.isEmpty()) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while (parent != null && !parent.isEmpty()) {
				rebalance(parent);
				parent = (BSTNode<T>) parent.getParent();
			}
		}
	}

	void leftRotation(BSTNode<T> node) {

		BSTNode<T> aux = Util.leftRotation(node);
		if (root.equals(node)) {
			root = aux;
		}
	}

	void rightRotation(BSTNode<T> node) {

		BSTNode<T> aux = Util.rightRotation(node);
		if (this.root.equals(node)) {
			root = aux;
		}
	}
}