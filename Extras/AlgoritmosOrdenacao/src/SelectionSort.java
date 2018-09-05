import java.util.Arrays;

public class SelectionSort<T extends Comparable<T>> {

    public void SelectionSort(T[] array, int leftIndex, int rightIndex) {
        int menor;

        for (int i = leftIndex; i < rightIndex; i++) {
            menor = i;
            for (int j = leftIndex++; j < rightIndex; j++) {
                if (array[j].compareTo(array[menor]) < 0) {
                    menor = j;
                }
            }
            Util.swap(array, i, menor);
            System.out.println(Arrays.toString(array));
        }
    }

    public static void main(String[] args) {
        SelectionSort<Integer> bubb = new SelectionSort<>();

        Integer arreio[] = new Integer[] {9, 7,8,1,2,0,4};
        bubb.SelectionSort(arreio, 0, 7);

    }
}
