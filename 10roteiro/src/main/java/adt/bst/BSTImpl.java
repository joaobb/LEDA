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
      int altura = -1;
      if (!node.isEmpty())
         altura = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
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
      if (!node.isEmpty()) {
         if (node.getLeft().isEmpty()) {
            result = node;
         } else
            result = maximum((BSTNode<T>) node.getLeft());
      }
      return result;
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      BSTNode<T> result = null;

      if (element != null)
         result = sucessor(element, root);
      return result;
   }

   private BSTNode<T> sucessor(T element, BSTNode<T> node) {
      BSTNode<T> result = search(element);

      if (!result.equals(new BSTNode<>())) {

         if (!result.getRight().isEmpty()) {
            result = minimum((BSTNode<T>) result.getRight());
         } else {
            result = (BSTNode<T>) result.getParent();
            while (!result.isEmpty() && result.getData().compareTo(node.getData()) < 0) {
               result = (BSTNode<T>) node.getParent();
            }
         }
      } else
         result = null;

      return result;
   }

   @Override
   public BSTNode<T> predecessor(T element) {
      BSTNode<T> result = null;

      if (element != null)
         result = predecessor(element, root);
      return result;
   }

   private BSTNode<T> predecessor(T element, BSTNode<T> node) {
      BSTNode<T> result = search(element);

      //Tree contains the given element
      if (!search(element).equals(new BSTNode<>())) {
         //Node contains left child
         if (!result.getLeft().isEmpty()) {
            result = maximum((BSTNode<T>) result.getLeft());
         } else {
            result = (BSTNode<T>) result.getParent();
            while (result != null && result.getData().compareTo(node.getData()) < 0) {
               result = (BSTNode<T>) node.getParent();
            }
         }
      } else
         result = null;

      return result;
   }

   @Override
   public void remove(T element) {
      if (element != null && !search(element).equals(new BSTNode<T>())) {
         BSTNode<T> toBeRemoved = search(element);

         if (toBeRemoved.isLeaf()) {
            toBeRemoved.setData(null);
         }
         //The node with the given element is the tree root
         else if (toBeRemoved.getParent().equals(NILBilder())) {
            if (!toBeRemoved.getRight().isEmpty()) {
               root = (BSTNode<T>) toBeRemoved.getRight();
            } else {
               root = (BSTNode<T>) toBeRemoved.getRight();
            }
            root.setParent(NILBilder());
         } else {
            //Node with given element is not the root
            if (!toBeRemoved.getRight().equals(NILBilder())) {
               toBeRemoved.getParent().setRight(toBeRemoved.getRight());
               toBeRemoved.getRight().setParent(toBeRemoved.getParent());
            } else {
               toBeRemoved.getParent().setLeft(toBeRemoved.getLeft());
               toBeRemoved.getLeft().setParent(toBeRemoved.getParent());
            }
         }
      }
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
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }

   private BSTNode<T> NILBilder() {
      return new BSTNode<>();
   }
}
