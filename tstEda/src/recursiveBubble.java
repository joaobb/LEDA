import java.util.Arrays;

public class recursiveBubble {

    public static void bubble(int arr[], int left, int right) {
        System.out.println(Arrays.toString(arr));
        if (left < right) {
            for (int i = left; i < right; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
            bubble(arr, left, right - 1);
        }
    }

    static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        bubble(new int[]{7, 1, 4, 2, 0}, 0, 4);
    }

}
