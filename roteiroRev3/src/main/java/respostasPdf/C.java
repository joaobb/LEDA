package respostasPdf;

import java.text.CollationElementIterator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class C <T> implements A <T> {

    public void zzz(T t){
        System.out.println(t.getClass());
    }

    public void n(Collection<? extends T> list) {
        System.out.println(list);
    }

    public void p(Collection<?> list) {
        for (Object elem: list)
            System.out.print(elem + " ");
        System.out.println();
    }

    public T m(T object) {
        return object;
    }
//
//    public void m(Collection<T> obj) {
//        System.out.println(obj.toString());
//    }

    public void m(Collection<?> obj) {
        System.out.println(obj);
    }

    public void m(? super T obj) {
        System.out.println("Hello world");
    }
}
