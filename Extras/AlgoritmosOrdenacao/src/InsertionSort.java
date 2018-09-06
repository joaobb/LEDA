import java.util.Arrays;

public class InsertionSort <T extends Comparable<T>> {

    public void InsertionSort(T[] array, int leftIndex, int rightIndex) {

        for (int i = leftIndex + 1; i < rightIndex; i++) {
            while (array[i].compareTo(array[i - 1]) < 0 && i - 1 > 0) {
                Util.swap(array, i, i - 1);
                i--;
            }
            System.out.println(Arrays.toString(array));
        }
    }


    public static void main(String[] args) {
        InsertionSort<Integer> bubb = new InsertionSort<>();

        Integer arreio[] = new Integer[] {9, 7,8,1,2,0,4};
        bubb.InsertionSort(arreio, 0, 7);

    }
}
