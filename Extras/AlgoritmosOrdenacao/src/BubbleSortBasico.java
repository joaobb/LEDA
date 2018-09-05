import java.util.Arrays;

public class BubbleSortBasico<T extends Comparable<T>> {

    public void BubbleSortBasico(T[] array, int leftIndex, int rightIndex) {
        for (int i = leftIndex; i < rightIndex; i++) {
            for (int j = leftIndex; j < rightIndex; j++) {
               if (array[i].compareTo(array[j]) < 0) {
                   Util.swap(array, i, j);
               }
            }
            System.out.println(Arrays.toString(array));
        }
    }

    public static void main(String[] args) {
        BubbleSortBasico<Integer> bubb = new BubbleSortBasico<>();

        Integer arreio[] = new Integer[] {9, 7,8,1,2,0,4};
        bubb.BubbleSortBasico(arreio, 0, 7);

    }
}
