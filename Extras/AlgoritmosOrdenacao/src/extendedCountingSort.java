import java.util.Arrays;

public class extendedCountingSort<T extends Comparable<T>> {

    public void sort(Integer[] array, int leftIndex, int rightIndex) {

        int[] arreio;
        int[] arreioResultado;
        //Menor elemento presente no array, iniciado com o primeiro elemento para que se torne possivel a comparacao.
        int menor = array[leftIndex];
        //Maior elemento presente no array.
        int maior = 0;
        //Offset que optimiza o alg, agora aceitando negativos.
        int officeBoy;

        //Procura o maior e emnor elemento do array.
        for (int i = leftIndex; i <= rightIndex; i++) {
            if (array[i] > maior) maior = array[i];
            else if (array[i] < menor) menor = array[i];
        }

        officeBoy = menor * -1;


        arreio = new int[maior + officeBoy + 1];
        arreioResultado = new int[array.length];

        for (int i = leftIndex; i <= rightIndex; i++) {
            arreio[array[i] + officeBoy]++;
        }

        for (int i = 0; i < arreio.length - 1; i++) {
            arreio[i + 1] += arreio[i];
        }

        for (int i = rightIndex; i >= leftIndex; i--) {
            arreioResultado[--arreio[array[i] + officeBoy] + leftIndex] = array[i];
        }

        for (int i = leftIndex; i <= rightIndex; i++) {
            array[i] = arreioResultado[i];
        }

        System.out.println(Arrays.toString(array));

    }

    public static void main(String[] args) {
        extendedCountingSort ex = new extendedCountingSort();

        ex.sort(new Integer[]{7, 4, -38, 0}, 0, 3);
    }
}
