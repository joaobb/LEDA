package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

   /**
    * Metodo que particiona um array em dois e realiza chamadas recursivas para que este seja ordenado.
    *
    * @param array      the target of the sorting algorithm
    * @param leftIndex  where the sorting should begin
    * @param rightIndex where the sorting should stop
    */
   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (!(array == null || array.length == 0 || leftIndex < 0 || leftIndex > rightIndex || rightIndex > array.length - 1)) {
         int pivot = partition(array, leftIndex, rightIndex);

         sort(array, leftIndex, pivot - 1);
         sort(array, pivot + 1, rightIndex);
      }
   }

   /**
    * Metodo que tem funcao de escolher um pivot para o array, e separar este de forma que todos os elementos "maiores"
    * que este pivot fiquem do lado direito, e os menores do lado esquerdo desteç
    *
    * @param array      the target of the sorting algorithm
    * @param leftIndex  where the sorting should start
    * @param rightIndex where the sorting should stop
    * @return posicao do pivot.
    */
   private int partition(T[] array, int leftIndex, int rightIndex) {
      T pivot = array[leftIndex];

      int le = leftIndex + 1;
      int ri = rightIndex;

      while (le <= ri) {
         if (array[le].compareTo(pivot) <= 0) {
            le++;
         }
         else if (array[ri].compareTo(pivot) >= 0) {
            ri--;
         }
         else {
            Util.swap(array, le++, ri--);
         }
      }
      Util.swap(array, leftIndex, ri);
      return ri;
   }
}
