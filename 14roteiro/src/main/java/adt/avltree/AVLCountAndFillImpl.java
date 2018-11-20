package adt.avltree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int variation = this.calculateBalance(node);
			if (Math.abs(variation) > 1) {
				if (variation < -1) {
					int rightVariation = this.calculateBalance((BSTNode<T>) node.getRight());
					if (rightVariation > 0) {
						this.rightRotation((BSTNode<T>) node.getRight());
						this.leftRotation(node);
						this.RLcounter++;
					} else {
						this.leftRotation(node);
						this.RRcounter++;
					}
				} else {
					int leftVariation = this.calculateBalance((BSTNode<T>) node.getLeft());
					if (leftVariation < 0) {
						this.leftRotation((BSTNode<T>) node.getLeft());
						this.rightRotation(node);
						this.LRcounter++;
					} else {
						this.rightRotation(node);
						this.LLcounter++;
					}
				}
			}
		}

	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null) {
			Arrays.sort(array);
			Queue<Integer[]> fila = new LinkedList<Integer[]>();

			Integer[] limit = { 0, array.length - 1 };
			fila.add(limit);

			while (!fila.isEmpty()) {
				Integer meio = (fila.peek()[0] + fila.peek()[1]) / 2;
				insert(array[meio]);
				Integer[] left = { fila.peek()[0], meio - 1 };
				Integer[] rigth = { meio + 1, fila.peek()[1] };
				Integer diferenca = fila.peek()[1] - fila.peek()[0];
				if (diferenca > 1) {
					fila.add(left);
					fila.add(rigth);
				}
				if (diferenca == 1) {
					insert(array[fila.peek()[1]]);
				}
				fila.poll();
			}
		}
	}

}