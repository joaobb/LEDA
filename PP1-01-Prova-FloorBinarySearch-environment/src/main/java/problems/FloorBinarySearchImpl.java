package problems;

public class FloorBinarySearchImpl implements Floor {

    @Override
    public Integer floor(Integer[] array, Integer x) {
        Integer retorno = - 1;                                                  //Menor do que o menor, ou array vazio ou nulo.

        if (array != null && array.length > 0) {
            sort(array, 0, array.length - 1);
            if (array[array.length - 1] < x) {
                retorno = array[array.length - 1];
            } else if (array[0] > x) {
                retorno = -1;
            } else {
                retorno = meth(array, 0, array.length, x);
            }
        }
        return retorno;
    }

    private int meth(Integer[] array, int leftIndex, int rightIndex, int x) {
        int retorno = 0;
        if (leftIndex < rightIndex) {
            int pivot = (leftIndex + rightIndex) / 2;

            if (array[pivot] == x) {
                retorno = array[pivot];
            } else if (rightIndex - leftIndex == 1 && array[pivot] < x) {
                retorno = array[pivot];
            } else if (array[pivot] < x) {
                retorno = meth(array, pivot, rightIndex, x);
            } else if (array[pivot] > x) {
                retorno = meth(array, leftIndex, pivot, x);
            }
        }
        return retorno;
    }


    public void sort(Integer arr[], int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            for (int i = leftIndex; i < rightIndex; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
            sort(arr, leftIndex, rightIndex - 1);
        }
    }

    public static void swap(Integer arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        FloorBinarySearchImpl fb = new FloorBinarySearchImpl();
        Integer arr[] = new Integer[]{9, 13, 3, 5, 7, 1};
        System.out.println(fb.floor(arr, 4));

    }
}
