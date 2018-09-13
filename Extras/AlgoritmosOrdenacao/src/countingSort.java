import java.util.Arrays;

public class countingSort <T extends Comparable<T>> {

    public void sort(Integer[] array, int leftIndex, int rightIndex) {
        int[] arreio;
        int[] arreioResultado;
        //Menor elemento presente no array, iniciado com o primeiro elemento para que se torne possivel a comparacao.
        int menor = array[leftIndex];
        //Maior elemento presente no array.
        int maior = 0;
        //Offset que optimiza o alg.
        int oficeBoy = menor;

        //Procura o maior e menor elemento do array
        for (int i = leftIndex; i <= rightIndex; i++) {
            if (array[i] > maior) maior = array[i];
            if (array[i] < menor) menor = array[i];
        }

        //Inicia o array com as quantidades de cada elemento no tamanho necessario
        arreio = new int[maior - menor + 1];
        //Inicia o array com o resultado da particao a ser ordenada.
        arreioResultado = new int[array.length];

        //Incremento nas posicoes que possuem numeros
        for (int i = leftIndex; i <= rightIndex; i++) {
            arreio[array[i] - oficeBoy]++;
        }

        //Somando os prefixos
        for (int i = 0; i < arreio.length - 1; i++) {
            arreio[i + 1] += arreio[i];
        }

        //Adicao de elementos da particao ordenada a um array.
        for (int i = rightIndex; i >= leftIndex; i--) {
            arreioResultado[--arreio[array[i] - oficeBoy] + leftIndex] = array[i];
        }

        //Tranferencia de elementos ordenados para o array original.
        for (int i = leftIndex; i <= rightIndex; i++) {
            array[i] = arreioResultado[i];
        }

        //Teste maroto
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args){
        countingSort cs = new countingSort();
        cs.sort(new Integer[]{1300, 1000, 1200, 1001, 1500, 1231},1, 4);
    }
}
