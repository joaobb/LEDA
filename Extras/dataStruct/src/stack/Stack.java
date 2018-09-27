package stack;

import javax.management.relation.RoleUnresolved;

public interface Stack <T> {
    public T top();
    public boolean isEmpty();
    public boolean isFull();
    public void push(T element) throws RuntimeException;
    public T pop() throws RuntimeException;
}
