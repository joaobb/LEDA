package respostasPdf;

import java.util.Collection;

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

    @Override
    public T m(T string) {
        return null;
    }
}
