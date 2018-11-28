package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
        implements RBTree<T> {

    public RBTreeImpl() {
        this.root = new RBNode<T>();
    }

    protected int blackHeight() {
        return this.blackHeight((RBNode<T>) this.getRoot());
    }

    private int blackHeight(RBNode<T> node) {
        int result;

        if (!node.isEmpty()) {
            if (node.getColour() == Colour.BLACK)
                result = 1;
            else
                result = 0;

            result = result + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
        }

        else result = 1;

        return result;

    }

    protected boolean verifyProperties() {
        boolean resp = verifyNodesColour() && verifyNILNodeColour()
                && verifyRootColour() && verifyChildrenOfRedNodes()
                && verifyBlackHeight();

        return resp;
    }

    /**
     * The colour of each node of a RB tree is black or red. This is guaranteed
     * by the type Colour.
     */
    private boolean verifyNodesColour() {
        return true; // already implemented
    }

    /**
     * The colour of the root must be black.
     */
    private boolean verifyRootColour() {
        return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
        // implemented
    }

    /**
     * This is guaranteed by the constructor.
     */
    private boolean verifyNILNodeColour() {
        return true; // already implemented
    }

    /**
     * Verifies the property for all RED nodes: the children of a red node must
     * be BLACK.
     */
    private boolean verifyChildrenOfRedNodes() {
        return this.verifyChildrenOfRedNodes((RBNode<T>) this.getRoot(), true);
    }

    private boolean verifyChildrenOfRedNodes(RBNode<T> node, boolean verify) {
        boolean result = verify;

        if (!node.isEmpty()) {
            if (node.getColour() == Colour.RED) {
                if (!node.getLeft().isEmpty() && ((RBNode<T>) node.getLeft()).getColour() == Colour.RED) {
                    result = false;
                }
                if (!node.getRight().isEmpty() && ((RBNode<T>) node.getRight()).getColour() == Colour.RED) {
                    result = false;
                }
            }
            return (verifyChildrenOfRedNodes((RBNode<T>) node.getLeft(), result)
                    && verifyChildrenOfRedNodes((RBNode<T>) node.getRight(), result));
        } else {
            return result;
        }
    }

    /**
     * Verifies the black-height property from the root.
     */
    private boolean verifyBlackHeight() {
        return this.blackHeight((RBNode<T>) this.getRoot().getLeft()) == this.blackHeight((RBNode<T>) this.getRoot().getRight());
    }

    @Override
    public void insert(T value) {
        if (value != null) {
            this.insert((RBNode<T>) root, value);
        }
    }

    private void insert(RBNode<T> node, T value) {
        if (node.isEmpty()) {
            node.setData(value);
            node.setLeft(new RBNode<T>());
            node.getLeft().setParent(node);
            node.setRight(new RBNode<T>());
            node.getRight().setParent(node);
            node.setColour(Colour.RED);

            if (node.getParent() == null) {
                node.setParent(new BSTNode<>());
            }

            fixUpCase1(node);

        } else {
            if (node.getData().compareTo(value) > 0) {
                insert((RBNode<T>) node.getLeft(), value);

            } else if (node.getData().compareTo(value) < 0) {
                insert((RBNode<T>) node.getRight(), value);
            }
        }
    }

    @Override
    public RBNode<T>[] rbPreOrder() {
        RBNode<T>[] result = new RBNode[this.size()];
        rbPreOrder(result, (RBNode<T>) this.getRoot(), 0);
        return result;
    }

    private int rbPreOrder(RBNode<T>[] array, RBNode<T> node, int index) {
        if (!node.isEmpty()) {
            array[index++] = node;
            index = this.rbPreOrder(array, (RBNode<T>) node.getLeft(), index);
            index = this.rbPreOrder(array, (RBNode<T>) node.getRight(), index);
        }

        return index;
    }

    // FIXUP methods
    protected void fixUpCase1(RBNode<T> node) {
        if (node.equals(this.getRoot()))
            node.setColour(Colour.BLACK);
        else
            this.fixUpCase2(node);
    }

    protected void fixUpCase2(RBNode<T> node) {
        if (((RBNode<T>) node.getParent()).getColour() != Colour.BLACK)
            this.fixUpCase3(node);
    }

    protected void fixUpCase3(RBNode<T> node) {
        RBNode<T> grandFather = (RBNode<T>) node.getParent().getParent();
        RBNode<T> uncle = (RBNode<T>) grandFather.getRight();
        if (uncle.equals(node)) uncle = (RBNode<T>) grandFather.getLeft();

        if (uncle.getColour() == Colour.RED) {
            ((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
            uncle.setColour(Colour.BLACK);
            grandFather.setColour(Colour.RED);
            this.fixUpCase1(grandFather);
        } else {
            fixUpCase4(node);
        }
    }

    protected void fixUpCase4(RBNode<T> node) {
        RBNode<T> next = node;
        RBNode<T> father = (RBNode<T>) node.getParent();
        RBNode<T> grandFather = (RBNode<T>) father.getParent();

        if (node.equals(father.getRight()) && father.equals(grandFather.getLeft())) {
            Util.leftRotation(father);
            next = (RBNode<T>) node.getLeft();
        } else if (node.equals(father.getLeft()) && father.equals(grandFather.getRight())) {
            Util.leftRotation(father);
            next = (RBNode<T>) node.getLeft();
        }

        fixUpCase5(next);
    }

    protected void fixUpCase5(RBNode<T> node) {
        RBNode<T> father = (RBNode<T>) node.getParent();
        RBNode<T> grandFather = (RBNode<T>) father.getParent();
        father.setColour(Colour.BLACK);
        grandFather.setColour(Colour.RED);

        if (node.equals(father.getLeft()))
            Util.rightRotation(grandFather);
        else
            Util.leftRotation(grandFather);
    }
}














