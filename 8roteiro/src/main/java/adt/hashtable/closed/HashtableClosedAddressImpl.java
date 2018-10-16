package adt.hashtable.closed;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

    /**
     * A hash table with closed address works with a hash function with closed
     * address. Such a function can follow one of these methods: DIVISION or
     * MULTIPLICATION. In the DIVISION method, it is useful to change the size
     * of the table to an integer that is prime. This can be achieved by
     * producing such a prime number that is bigger and close to the desired
     * size.
     * <p>
     * For doing that, you have auxiliary methods: Util.isPrime and
     * getPrimeAbove as documented bellow.
     * <p>
     * The length of the internal table must be the immediate prime number
     * greater than the given size (or the given size, if it is already prime).
     * For example, if size=10 then the length must
     * be 11. If size=20, the length must be 23. You must implement this idea in
     * the auxiliary method getPrimeAbove(int size) and use it.
     *
     * @param desiredSize
     * @param method
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
        int realSize = desiredSize;

        if (method == HashFunctionClosedAddressMethod.DIVISION) {
            realSize = this.getPrimeAbove(desiredSize); // real size must the
            // the immediate prime
            // above
        }
        initiateInternalTable(realSize);
        HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
        this.hashFunction = function;
    }

    // AUXILIARY

    /**
     * It returns the prime number that is closest (and greater) to the given
     * number.
     * If the given number is prime, it is returned.
     * You can use the method Util.isPrime to check if a number is
     * prime.
     */
    int getPrimeAbove(int number) {
        while (!isPrime(++number));
        return number;
    }

    int getPrimeAboveImproved(int number) {
        int i;
        for (i = 2; i * 2 <= number; i *= 2) ;
        i *= 1.5;
        while (!isPrime(i)) i++;
        return i;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        else if (n <= 3) return true;
        else if (n % 2 == 0 || n % 3 == 0) return false;
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
            i += 6;
        }
        return true;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            int hash = getHashFunction().hash(element);

            if (!this.getCelula(hash).isEmpty()) {
                if (this.search(element) == null) {
                    this.getCelula(hash).push(element);
                    super.COLLISIONS++;
                    super.elements++;
                }
            } else {
                this.getCelula(hash).addFirst(element);
                super.elements++;
            }
        }
    }

    @Override
    public void remove(T element) {
        if (element != null && super.elements > 0) {
            int hash = getHashFunction().hash(element);

            this.getCelula(hash).remove(element);
            super.elements--;
        }
    }

    @Override
    public T search(T element) {
        T retorno = null;
        if (element != null && super.elements > 0) {
            int hash = this.getHashFunction().hash(element);

            for (T item : this.getCelula(hash)) if (item.equals(element)) retorno = item;
        }

        return retorno;
    }

    @Override
    public int indexOf(T element) {
        int index = -1;

        if (element != null && this.search(element) != null)
            if (hashFunction instanceof HashFunctionClosedAddress)
                index = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);

        return index;
    }

    @Override
    public HashFunctionClosedAddress<T> getHashFunction() {
        return (HashFunctionClosedAddress<T>) super.getHashFunction();
    }

    @SuppressWarnings("unchecked")
    private LinkedList<T> getCelula(int index) {
        if (super.table[index] == null) super.table[index] = new LinkedList<>();

        return (LinkedList<T>) super.table[index];
    }
}
