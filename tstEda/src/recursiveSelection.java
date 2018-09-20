import java.util.Arrays;

public class recursiveSelection {
    public static void recursiveSelection(int arr[], int left, int right) {
        if (left < right) {
            int menor = left;

            for (int i = left + 1; i <= right; i++) {
                if (arr[i] < arr[menor]) {
                    menor = i;
                }
            }
            System.out.println(Arrays.toString(arr));
            swap(arr, menor, left);
            recursiveSelection(arr, left + 1, right);
        }
    }

    static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        recursiveSelection(new int[]{7, 1, 4, 2, 0}, 0, 4);
    }
}
