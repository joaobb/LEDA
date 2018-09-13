package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {
        if (!(array == null || array.length == 0 || leftIndex < 0 || leftIndex >= rightIndex || rightIndex > array.length - 1)) {
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
        }
    }
}
