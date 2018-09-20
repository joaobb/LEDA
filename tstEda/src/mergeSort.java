import java.util.Arrays;

public class mergeSort {

    void sort(int arr[], int left, int right) {
        if (!(arr == null || arr.length == 0 || left < 0 || left >= right || right > arr.length - 1)) {

            int mid = (right + left) / 2;

            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, right, mid);
            System.out.println(Arrays.toString(arr));
        }
    }
    void merge(int[] arr, int leftIndex, int rightIndex, int mid) {
        int aux[] = Arrays.copyOf(arr, rightIndex + 1);

        int l = leftIndex, m = mid + 1, i = leftIndex;

        while (l <= mid && m <= rightIndex) {
            if (aux[l] < aux[m]) {
                arr[i++] = aux[l++];
            } else {
                arr[i++] = aux[m++];
            }
        }

        while (l <= mid) {
            arr[i++] = aux[l++];
        }

        while (m <= rightIndex) {
            arr[i++] = aux[m++];
        }
    }

    public static void main(String[] args) {
        mergeSort ms = new mergeSort();
        ms.sort(new int[]{7, 1, 3, 2, 4}, 0, 4);
    }

}


