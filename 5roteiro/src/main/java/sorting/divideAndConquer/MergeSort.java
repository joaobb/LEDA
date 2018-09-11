package sorting.divideAndConquer;

import sorting.AbstractSorting;
import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (!(array == null || array.length == 0 || leftIndex < 0 || leftIndex >= rightIndex || rightIndex > array.length - 1)) {

            int middleIndex = (leftIndex + rightIndex) / 2;

            sort(array, leftIndex, middleIndex);
            sort(array, middleIndex + 1, rightIndex);
            merge(array, leftIndex, rightIndex, middleIndex);
        }
    }

    private void merge(T[] array, int leftIndex, int rightIndex, int middleIndex) {
        T[] auxiliar = Arrays.copyOf(array, rightIndex + 1);

        int l = leftIndex;
        int m = middleIndex + 1;
        int oriArrInd = leftIndex;

        while (l <= middleIndex && m <= rightIndex) {
            if (auxiliar[l].compareTo(auxiliar[m]) <= 0) {
                array[oriArrInd++] = auxiliar[l++];
            } else {
                array[oriArrInd++] = auxiliar[m++];
            }
        }

        while (l <= middleIndex) {
            array[oriArrInd++] = auxiliar[l++];
        }

        while (m <= rightIndex) {
            array[oriArrInd++] = auxiliar[m++];
        }
    }
}