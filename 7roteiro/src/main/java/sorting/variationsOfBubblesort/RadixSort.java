package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

public class RadixSort<String extends Comparable<String>> extends AbstractSorting<String> {

    public void sort(String[] array, int leftIndex, int rightIndex) {

        for (int i = leftIndex; i > 0; i--) {
            adp(array,0,array.length-1,i);
        }


    }

    public void adp(String[] array, int leftIndex, int rightIndex, int i) {
        if (isValid(array, leftIndex, rightIndex)) {

            int pivot = leftIndex;

            while (pivot < rightIndex - leftIndex) {
                if (array[pivot] .compareTo(array[pivot + 1]) > 0) {
                    Util.swap(array, pivot, pivot + 1);

                    if (pivot > 0) {
                        pivot -= 2;
                    }
                }

                pivot++;
            }
        }
    }

    public boolean isValid(String[] array, int leftIndex, int rightIndex) {
        return (!(array == null || array.length == 0 || leftIndex < 0 || leftIndex >= rightIndex || rightIndex > array.length - 1));
    }
}
