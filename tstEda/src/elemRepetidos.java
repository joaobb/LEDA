import java.util.Scanner;

class elemRepetidos {

    static boolean elemRepetidos(String ent) {
        String arreio[] = ent.split(" ");
        boolean retorno = false;
        for (int i = 0; i < arreio.length; i++) {
            for (int j = i + 1; j < arreio.length; j++) {
                if (arreio[i].equals(arreio[j]))
                    retorno = true;
            }
        }
        return retorno;
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        String ent = inp.nextLine();
        System.out.println(elemRepetidos(ent));
    }
}
