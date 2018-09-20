import java.util.Arrays;
import java.util.Scanner;

public class furaFila {

    static void furaFila(String ent, int ind) {
        String arr[] = ent.split(" ");
        int i = 1;

        while (ind < arr.length) {
            for (int j = ind; j >= i; j--) {

                swap(arr, j, j-1);
            }
            i++;
            ind++;

            System.out.println(Arrays.toString(arr));
        }
    }

    static void swap(String arr[], int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        Scanner inp = new Scanner(System.in);
        String enp = inp.nextLine();
        int index = inp.nextInt();

        furaFila(enp, index);

    }
}
