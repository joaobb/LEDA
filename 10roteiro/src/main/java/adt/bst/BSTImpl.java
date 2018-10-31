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

    private int height(BTNode<T> node) {
        int altura;
        if (node.isEmpty()) altura = -1;
        else altura = Math.max(this.height(node.getLeft()), this.height(node.getRight())) + 1;
        return altura;
    }

    @Override
    public BSTNode<T> search(T element) {
        return search(element, this.getRoot());
    }

    private BSTNode<T> search(T element, BTNode<T> node) {
        BSTNode<T> result = new BSTNode<T>();

        if (element != null || !node.isEmpty()) {

            if (element.equals(node.getData())) result = (BSTNode<T>) node;
            else if (element.compareTo(node.getData()) > 0) result = search(element, node.getRight());
            else result = search(element, node.getLeft());
        }
        return result;

    }

    @Override
    public void insert(T element) {
        if (element != null)
            insert(element, root);
    }

    public void insert(T element, BSTNode<T> node) {
        if (node.isEmpty()) {
            node.setData(element);
            node.setLeft(new BSTNode<>());
            node.setRight(new BSTNode<>());
            node.getLeft().setParent(node);
            node.getRight().setParent(node);
        } else if (element.compareTo(node.getData()) < 0)
            insert(element, (BSTNode<T>) node.getLeft());
        else
            insert(element, (BSTNode<T>) node.getLeft());
    }

    @Override
    public BSTNode<T> maximum() {
        return maximum(root);
    }

    private BSTNode<T> maximum(BSTNode<T> node) {
        BSTNode<T> result = null;
        if (!node.getRight().isEmpty())
            result = minimum(node.getRight());
        else
            result = node;
        return result;
    }

    @Override
    public BSTNode<T> minimum() {
        return minimum(root);
    }

    private BSTNode<T> minimum(BSTNode<T> node) {
        BSTNode<T> result = null;
        if (!node.getLeft().isEmpty())
            result = minimum(node.getLeft());
        else
            result = node;
        return result;
    }

    private BSTNode<T> minimum(BTNode node) {
        return minimum(node.getLeft());
    }

    @Override
    public BSTNode<T> sucessor(T element) {
        sucessor(element, root);
    }

    private BSTNode<T> sucessor(T element, BSTNode<T> node) {
        BSTNode<T> result = search(element);

        if (search(element) != new BSTNode<>()) {

            if (!result.getRight().isEmpty()) {
                result = minimum(result.getRight());
            } else {
                result = (BSTNode<T>) result.getParent();
                while (!result.isEmpty() && result.getData().compareTo(node.getData()) < 0) {
                    result = (BSTNode<T>) node.getParent();
                }
            }
        }

        return result;
    }

    @Override
    public BSTNode<T> predecessor(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public void remove(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public T[] preOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public T[] order() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public T[] postOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
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
            result = 1 + size((BSTNode<T>) node.getLeft())
                    + size((BSTNode<T>) node.getRight());
        }
        return result;
    }

}
