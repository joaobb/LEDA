package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
        extends AbstractHashtableOpenAddress<T> {

    public HashtableOpenAddressQuadraticProbingImpl(int size,
                                                    HashFunctionClosedAddressMethod method, int c1, int c2) {
        super(size);
        hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            int i = 0;
            int elementHash;
            while (i < super.table.length) {
                elementHash = getHashFunction().hash(element, i);
                if (super.table[elementHash] == null || super.table[elementHash] instanceof DELETED) {
                    super.table[elementHash] = element;
                    super.elements++;
                    break;
                } else {
                    super.COLLISIONS++;
                    i++;
                }
            }
            if (i == super.table.length - 1) throw new HashtableOverflowException();
        }
    }

    @Override
    public void remove(T element) {
        if (element != null) {
            if (this.indexOf(element) != -1) {
                super.table[this.indexOf(element)] = new DELETED();
                super.elements--;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T search(T element) {
        T result = null;
        if (element != null)
            if (this.indexOf(element) != -1)
                result = (T) super.table[this.indexOf(element)];
        return result;
    }

    @Override
    public int indexOf(T element) {
        int result = -1;
        if (element != null) {
            int elementHash;
            int i = 0;
            while (i < super.table.length) {
                elementHash = getHashFunction().hash(element, i);
                if (super.table[elementHash] != null && element.equals(super.table[elementHash])) {
                    result = elementHash;
                    break;
                } else {
                    i++;
                }
            }
        }
        return result;
    }

    @Override
    public HashFunctionQuadraticProbing<T> getHashFunction() {
        return (HashFunctionQuadraticProbing<T>) super.getHashFunction();
    }
}
