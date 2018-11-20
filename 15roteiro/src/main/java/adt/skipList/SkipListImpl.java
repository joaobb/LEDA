package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

    protected SkipListNode<T> root;
    protected SkipListNode<T> NIL;

    protected int maxHeight;

    protected double PROBABILITY = 0.5;

    public SkipListImpl(int maxHeight) {
        this.maxHeight = maxHeight;
        root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
        NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
        connectRootToNil();
    }

    /**
     * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
     * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
     * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
     * metodo deve conectar apenas o forward[0].
     */
    private void connectRootToNil() {
        for (int i = 0; i < maxHeight; i++) {
            root.forward[i] = NIL;
        }
    }


    @Override
    public void insert(int key, T newValue, int height) {
        SkipListNode[] update = new SkipListNode[this.maxHeight];

        SkipListNode x = this.root;
        for (int i = this.maxHeight - 1; i >= 0; i--) {
            while (x.getForward(i).getKey() < key) {
                x = x.getForward(i);
            }
            update[i] = x;
        }
        x = x.getForward(0);

        if (x.getKey() == key) {
            x.setValue(newValue);
        } else {
            if (height <= this.maxHeight) {
                x = new SkipListNode(key, height, newValue);
                for (int i = 0; i < height; i++) {
                    x.getForward()[i] = update[i].getForward(i);
                    update[i].getForward()[i] = x;
                }
            } else throw new RuntimeException();
        }
    }

    @Override
    public void remove(int key) {
        SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
        SkipListNode<T> x = this.root;

        for (int i = this.maxHeight - 1; i >= 0; i--) {
            while (x.getForward(i).getKey() < key) {
                x = x.getForward(i);
            }
            update[i] = x;
        }
        x = x.getForward(0);

        if (x.getKey() == key) {
            for (int i = 0; i < this.nodeHeight(x.getForward()); i++) {
                if (!update[i].getForward(i).equals(x)) {
                    break;
                }
                update[i].getForward()[i] = x.getForward(i);
            }
        }
    }

    private int nodeHeight(SkipListNode<T>[] forward) {
        int resp = 0;
        for (int i = 0; i < forward.length; i++) {
            if (forward[i] != null) {
                resp++;
            }
        }
        return resp;
    }

    @Override
    public int height() {
        return this.nodeHeight(this.root.getForward());
    }

//    public int height() {
//        int biggest = this.maxHeight - 1;
//        while (this.root.getForward(biggest).equals(NIL)) {
//            biggest--;
//        }
//
//        return biggest;
//    }

    @Override
    public SkipListNode<T> search(int key) {
        SkipListNode<T> retorno = null;
        SkipListNode x = this.root;

        for (int i = this.maxHeight - 1; i >= 0; i--) {
            while (x.getForward(i).getKey() < key) {
                x = x.getForward(i);
            }
        }
        x = x.getForward(0);
        if (x.getKey() == key) {
            retorno = x;
        }

        return retorno;
    }

    @Override
    public int size() {
        SkipListNode x = this.root.getForward(0);
        int size = 0;
        while (!x.equals(NIL)) {
            size++;
            x = x.getForward(0);
        }

        return size;
    }

    @Override
    public SkipListNode<T>[] toArray() {
        SkipListNode<T>[] result = new SkipListNode[this.size() + 2];
        SkipListNode x = this.root;
        int ind = 0;

        while (x != null) {
            result[ind++] = x;
            x = x.getForward(0);
        }
        return result;
    }

}
