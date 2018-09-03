package respostasPdf;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {

//        C<Integer> c = new C<>();
//        List<String> a = new ArrayList<>();
//        a.add("dsad");
//        a.add("dsadsa");
//
//        List e = new ArrayList<Integer>();
//        e.add(2);
//        e.add(3);
//
//        c.n(e);
//        c.n(a);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(9);
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello world");

        C<String> c = new C<>();
        c.m(stringList);
        c.m(integerList);

    }
}
