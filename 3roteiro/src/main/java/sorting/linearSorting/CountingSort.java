package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 */
public class CountingSort extends AbstractSorting<Integer> {

    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {
        if (!(array == null || array.length == 0 || leftIndex < 0 || leftIndex >= rightIndex || rightIndex > array.length - 1)) {
            int[] arreio;
            int[] arreioResultado;
            //Menor elemento presente no array, iniciado com o primeiro elemento para que se torne possivel a comparacao.
            int menor = array[leftIndex];
            //Maior elemento presente no array.
            int maior = 0;
            //Offset que optimiza o alg.
            int oficeBoy;

            //Procura o maior e menor elemento do array
            for (int i = leftIndex; i <= rightIndex; i++) {
                if (array[i] > maior) maior = array[i];
                else if (array[i] < menor) menor = array[i];
            }

            oficeBoy = menor;

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
        }
    }
}
