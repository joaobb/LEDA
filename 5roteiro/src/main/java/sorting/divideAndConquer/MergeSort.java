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
        if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
            int meio = ((rightIndex + leftIndex) / 2);
            sort(array, leftIndex, meio);
            sort(array, meio + 1, rightIndex);
            merge(array, leftIndex, meio, rightIndex);
        }
    }

    public void merge(T[] array, int rightIndex, int meio, int leftIndex) {
        T[] mergedArray = Arrays.copyOf(array, leftIndex + 1);

        //Índices auxiliares
        int i = rightIndex;
        int j = meio + 1;
        int k = rightIndex;

        //Junção das listas ordenadas
        while (i <= meio && j <= leftIndex) {
            if (mergedArray[i].compareTo(mergedArray[j]) < 0) {
                array[k++] = mergedArray[i++];
            } else {
                array[k++] = mergedArray[j++];
            }
        }

        while (i <= meio) {
            array[k] = mergedArray[i];
            i++;
            k++;
        }

        while (j <= rightIndex) {

            array[k] = mergedArray[j];
            j++;
            k++;
        }
    }
}
