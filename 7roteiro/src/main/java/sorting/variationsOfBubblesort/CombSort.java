package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (isValid(array, leftIndex, rightIndex)) {
            int gap = (rightIndex - leftIndex);
            int i;
            boolean swapped;

            do {

                if (gap > 1) {
                    gap = (int) (gap / 1.25);
                }

                i = leftIndex;

                swapped = false;

                while (i + gap <= rightIndex) {
                    if (array[i].compareTo(array[gap + i]) > 0) {
                        Util.swap(array, i, gap + i);
                        swapped = true;
                    }
                    i++;
                }
            } while (gap > 1 || swapped);
        }
    }

    public boolean isValid(T[] array, int leftIndex, int rightIndex) {
        return (!(array == null || array.length == 0 || leftIndex < 0 || leftIndex >= rightIndex || rightIndex > array.length - 1));
    }
}

