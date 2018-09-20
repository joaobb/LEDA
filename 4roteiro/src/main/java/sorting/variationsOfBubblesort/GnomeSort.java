package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (isValid(array, leftIndex, rightIndex)) {

            int pivot = leftIndex;

            while (pivot < rightIndex - leftIndex) {
                if (array[pivot].compareTo(array[pivot + 1]) > 0) {
                    Util.swap(array, pivot, pivot + 1);

                    if (pivot > 0) {
                        pivot -= 2;
                    }
                }

                pivot++;
            }
        }
    }

    public boolean isValid(T[] array, int leftIndex, int rightIndex) {
        return (!(array == null || array.length == 0 || leftIndex < 0 || leftIndex >= rightIndex || rightIndex > array.length - 1));
    }
}
