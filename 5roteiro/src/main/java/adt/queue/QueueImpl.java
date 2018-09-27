package adt.queue;

public class QueueImpl<T> implements Queue<T> {

   private T[] array;
   private int tail;

   @SuppressWarnings("unchecked")
   public QueueImpl(int size) {
      array = (T[]) new Object[size];
      tail = -1;
   }

   @Override
   public T head() {
      return this.array[0];
   }

   @Override
   public boolean isEmpty() {
      return (this.tail == -1);
   }

   @Override
   public boolean isFull() {
      return (this.tail == (this.array.length - 1));
   }

   private void shiftLeft() {
      for (int i = 0; i < this.array.length - 1; this.array[i] = this.array[++i])
         ;
      this.array[tail--] = null;
   }

   @Override
   public void enqueue(T element) throws QueueOverflowException {
      if (this.isFull())
         throw new QueueOverflowException();
      if (element != null)
         this.array[++tail] = element;
   }

   @Override
   public T dequeue() throws QueueUnderflowException {
      if (this.isEmpty())
         throw new QueueUnderflowException();
      T retorno = this.array[0];
      this.shiftLeft();
      return retorno;
   }

   public static void main(String[] args) throws QueueOverflowException {
      QueueImpl<Integer> qi = new QueueImpl(4);
      qi.enqueue(1);
      qi.enqueue(2);
      qi.enqueue(3);
      qi.enqueue(4);
      qi.shiftLeft();
   }
}
