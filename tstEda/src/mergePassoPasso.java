import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.ceil;


public class mergePassoPasso {

    static void sort(int arr[], int leftIndex, int rightIndex) {
        if (!(arr == null || arr.length == 0 || leftIndex < 0 || leftIndex >= rightIndex || rightIndex > arr.length - 1)) {

            int mid = (rightIndex + leftIndex) / 2;

            System.out.println(Arrays.toString(Arrays.copyOfRange(arr, leftIndex, rightIndex + 1)));
            sort(arr, leftIndex, mid);
            System.out.println(Arrays.toString(Arrays.copyOfRange(arr, leftIndex, rightIndex)));
            sort(arr, mid + 1, rightIndex);

            merge(arr, leftIndex, rightIndex, mid);
        }
    }

    static void merge(int arr[], int left, int right, int mid) {
        int aux[] = Arrays.copyOf(arr, right + 1);

        int l = left;
        int m = mid + 1;
        int n = left;

        while (l <= mid && m <= right) {
            if (aux[l] < aux[m]) {
                arr[n++] = aux[l++];
            } else {
                arr[n++] = aux[m++];
            }
        }

        while (l <= mid) {
            arr[n++] = aux[l++];
        }

        while (m <= right) {
            arr[n++] = aux[m++];
        }

    }

    public static void main(String[] args){
        Scanner inp = new Scanner(System.in);
        String ent = inp.nextLine();
        int arr[] = new int[ent.split(" ").length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(ent.split(" ")[i]);
        }

        sort(arr, 0, arr.length - 1);
    }
}
