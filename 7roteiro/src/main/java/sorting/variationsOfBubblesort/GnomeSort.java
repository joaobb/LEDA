package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (valid(array, leftIndex, rightIndex)) {

         int pivot = leftIndex;

         while (pivot < rightIndex) {
            if (array[pivot].compareTo(array[pivot + 1]) > 0) {
               Util.swap(array, pivot++, pivot);

               for (int j = pivot - 1; j > leftIndex; j--) {
                  if (array[j].compareTo(array[j - 1]) < 0) {
                     Util.swap(array, j, j - 1);
                  }
               }
            } else {
               pivot++;
            }
         }
      }
   }

   public boolean valid(T[] array, int leftIndex, int rightIndex) {
      boolean retorno = false;
      if (!(array == null || array.length == 0 || leftIndex < 0 || leftIndex >= rightIndex || rightIndex > array.length - 1)) {
         retorno = true;
      }
      return retorno;
   }
}
