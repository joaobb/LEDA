package stack;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

public class StackImp<T> implements Stack<T> {
    private T[] array;
    private int top;

    public StackImp(int size) {
        this.array = (T[]) new Object[size];
        this.top = -1;
    }

    @Override
    public T top() {
        T retorno;
        if (this.isEmpty())
            retorno = null;
        else retorno = this.array[top];

        return retorno;
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public boolean isFull() {
        return this.top == array.length - 1;
    }

    @Override
    public void push(T element) throws RuntimeException {

        if (this.isFull())
            throw new RuntimeException();
        this.array[++top] = element;
        System.out.println(this.array[top]);
    }

    @Override
    public T pop() throws RuntimeException {
        if (this.isEmpty())
            throw new RuntimeException();
        T temp = this.array[top];
        this.array[top--] = null;
        return temp;
    }
}
