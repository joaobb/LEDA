package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
        AbstractHashtableOpenAddress<T> {

    public HashtableOpenAddressLinearProbingImpl(int size,
                                                 HashFunctionClosedAddressMethod method) {
        super(size);
        hashFunction = new HashFunctionLinearProbing<T>(size, method);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            boolean endLoop = false;
            int i = 0;
            int elementHash;
            while (i < super.table.length && !endLoop) {
                elementHash = getHashFunction().hash(element, i);
                if (super.table[elementHash] == null || super.table[elementHash] instanceof DELETED) {
                    super.table[elementHash] = element;
                    super.elements++;
                    endLoop = true;
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
            int i = 0;
            int elementHash;
            while (i < super.table.length) {
                elementHash = getHashFunction().hash(element, i);

                if (super.table[elementHash] != null && element.equals(super.table[elementHash])) {
                    super.table[elementHash] = new DELETED();
                    super.elements--;
                    break;
                } else i++;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T search(T element) {
        T result = null;
        if (element != null && elements > 0) {
            int i = 0;
            int elementHash = - 1;
            while (i < super.table.length) {
                elementHash = getHashFunction().hash(element, i);

                if (this.table[elementHash] != null && element.equals(super.table[elementHash])) {
                    result = (T) super.table[elementHash];
                    break;
                } else i++;
            }
        }
        return result;
    }

    @Override
    public int indexOf(T element) {
        int retorno = -1;

        if (element != null) {
            int i = 0;
            int elementHash;
            while (i < super.table.length) {
                elementHash = getHashFunction().hash(element, i);

                if (element.equals(super.table[elementHash])) {
                    retorno = elementHash;
                    break;
                } else i++;
            }
        }
        return retorno;
    }

    @Override
    public HashFunctionLinearProbing<T> getHashFunction() {
        return (HashFunctionLinearProbing<T>) super.getHashFunction();

    }
}
