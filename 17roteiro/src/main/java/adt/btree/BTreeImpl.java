package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

   protected BNode<T> root;
   protected int order;

   public BTreeImpl(int order) {
      this.order = order;
      this.root = new BNode<T>(order);
   }

   @Override
   public BNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return this.root.isEmpty();
   }

   @Override
   public int height() {
      int result = -1;
      if (!this.getRoot().isEmpty()) {
         result = height(this.getRoot());
      }
      return result;
   }

   private int height(BNode<T> node) {
      int result = 0;
      if (!node.isLeaf()) {
         result = 1 + this.height(node.children.getFirst());
      }
      return result;
   }

   @Override
   public BNode<T>[] depthLeftOrder() {
      BNode<T>[] result = new BNode[this.size() - 1];
      depthLeftOrder(this.getRoot(), result, 0);
      return result;
   }

   private int depthLeftOrder(BNode<T> node, BNode<T>[] array, int index) {
      array[index] = node;
      index++;
      for (int i = 0; i < node.children.size(); i++) {
         index = this.depthLeftOrder(node.children.get(i), array, index);
      }
      return index;
   }

   @Override
   public int size() {
      return this.size(this.getRoot());
   }

   private int size(BNode<T> node) {
      int size = node.getElements().size();

      for (int i = 0; i < node.getChildren().size(); i++) {
         size += this.size(node.getChildren().get(i));
      }
      return size;
   }

   @Override
   public BNodePosition<T> search(T element) {
      return search(this.getRoot(), element);
   }

   private BNodePosition<T> search(BNode<T> node, T elem) {
      int ind = 0;

      while (ind <= node.size() && elem.compareTo(node.elements.get(ind++)) > 0) {
         ind++;
      }
      if (ind <= node.size() && elem.equals(node.elements.get(ind))) {
         return new BNodePosition<>(node, ind);
      } else if (node.isLeaf()) {
         return new BNodePosition<>(null, -1);
      } else {
         return this.search(node.getChildren().get(ind), elem);
      }
   }

   @Override
   public void insert(T element) {
      if (element != null) {
         insert(this.getRoot(), element);
      }
   }

   private void insert(BNode<T> node, T element) {
      if (node.isLeaf()) {
         node.addElement(element);
      } else {
         int ind = 0;
         while (ind < node.size() && node.getElementAt(ind).compareTo(element) < 0) {
            ind++;
         }
      }
      if (node.elements.size() >= node.order) {
         System.out.println();
         split(node);
      }
   }

   private void split(BNode<T> node) {
      int median = (int) Math.ceil(node.size() / 2.0) - 1;
      T elemMed = node.getElements().get((int) Math.ceil(node.size() / 2.0) - 1);

      BNode<T> left = new BNode<>(node.size());
      BNode<T> right = new BNode<>(node.size());

      for (T elem : node.getElements()) {
         if (elem.compareTo(elemMed) < 0)
            left.addElement(elem);
         else if (elem.compareTo(elemMed) > 0)
            right.addElement(elem);
      }

      if (node == this.getRoot()) {
         BNode<T> newRoot = new BNode<T>(node.getOrder());
         newRoot.addElement(elemMed);
         node.setParent(newRoot);
         root = newRoot;

         for (int i = 0; i < node.getChildren().size(); i++) {
            if (i <= median) {
               left.addChild(i, node.getChildren().get(i));
            } else {
               right.addChild(i - median - 1, node.getChildren().get(i));
            }
         }
         newRoot.addChild(0, left);
         newRoot.addChild(1, right);
         newRoot.getChildren().get(0).setParent(newRoot);
         newRoot.getChildren().get(1).setParent(newRoot);
      } else {
         promote(node);
      }
   }

   private void promote(BNode<T> node) {
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   // NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
   @Override
   public BNode<T> maximum(BNode<T> node) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   @Override
   public BNode<T> minimum(BNode<T> node) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   @Override
   public void remove(T element) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

}
