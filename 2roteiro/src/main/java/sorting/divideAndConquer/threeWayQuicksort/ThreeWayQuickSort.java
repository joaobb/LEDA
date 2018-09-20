package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
        AbstractSorting<T> {

    /**
     * No algoritmo de quicksort, selecionamos um elemento como pivot,
     * particionamos o array colocando os menores a esquerda do pivot
     * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia
     * recursivamente na particao a esquerda do pivot e na particao a direita do pivot.
     * <p>
     * Considerando um array com muitos elementos repetidos, a estrategia do quicksort
     * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria
     * eh conhecida como quicksort tree way e consiste da seguinte ideia:
     * - selecione o pivot e particione o array de forma que
     * * arr[l..i] contem elementos menores que o pivot
     * * arr[i+1..j-1] contem elementos iguais ao pivot.
     * * arr[j..r] contem elementos maiores do que o pivot.
     * <p>
     * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
     * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
     * recursivamente.
     **/
    @Override
    public void sort(T array[], int leftIndex, int rigthIndex) {
        if (!(array == null || array.length == 0 || leftIndex < 0 || leftIndex > rigthIndex || rigthIndex > array.length - 1)) {

            int arr[] = partition(array, leftIndex, rigthIndex);
            int i = arr[0];
            int j = arr[1];

            sort(array, leftIndex, j);
            sort(array, i, rigthIndex);

        }
    }

    public int[] partition(T array[], int leftIndex, int rightIndex) {
        int i = leftIndex - 1;
        int j = rightIndex;
        int p = leftIndex - 1;
        int q = rightIndex;
        T pivot = array[rightIndex];

        while (true) {
            while (array[++i].compareTo(pivot) < 0) ;

            // From right, find the first element smaller than or
            // equal to v
            while (pivot.compareTo(array[--j]) < 0) {
                if (j == leftIndex) break;
            }

            if (i >= j) break;

            Util.swap(array, i, j);

            if (array[i] == pivot) {
                Util.swap(array, ++p, i);
            }

            if (array[j] == pivot) {
                Util.swap(array, j, --q);
            }
        }

        Util.swap(array, i, rightIndex);

        j = i - 1;
        for (int k = leftIndex; k < p; k++, j--)
            Util.swap(array, k, j);

        i++;
        for (int k = rightIndex - 1; k > q; k--, i++)
            Util.swap(array, i, k);

        return new int[]{i, j};
    }
}