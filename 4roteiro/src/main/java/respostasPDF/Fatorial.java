package respostasPDF;

import java.util.Scanner;

public class Fatorial {

    public int recursao(Object[] array, int indice) {
        int result = 0;

        if (array[indice] != null) result = 1;
        if (array.length - 1 != indice) result += this.recursao(array, ++indice);

        return result;
    }

    public int recursao(Object[] array) {
        int result;

        result = this.recursao(array, 0);

        return result;
    }

    public static void main(String args[]) {
        Fatorial fatorial = new Fatorial();
        Scanner inp = new Scanner(System.in);

        //int valor = inp.nextInt();

        System.out.println(fatorial.recursao(new Object[]{1, 2, null, null, 5, null, 7}));
    }
}
