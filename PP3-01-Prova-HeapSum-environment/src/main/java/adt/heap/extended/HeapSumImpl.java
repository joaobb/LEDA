package adt.heap.extended;

import java.util.PriorityQueue;


/**
 * A classe HeapSumImpl herda de PriorityQueue, que funciona como uma min heap.
 *
 * @author adalbertocajueiro
 */
public class HeapSumImpl extends PriorityQueue<Integer> implements HeapSum {

    @Override
    public Integer sumRangeOrderStatistics(Integer k1, Integer k2) {
        Integer result = 0;
        Integer popped;

        //It will run until either the queue gets empty, either it gets to the right limit of the
        //given range (k2)
        while (!this.isEmpty() && k2-- > 0) {
            popped = this.poll();
            if (--k1 <= 0) {
                result += popped;
            }
        }

        return result;
    }

    @Override
    public Integer sumRangeBetween(Integer v1, Integer v2) {
        Integer result = 0;
        Integer popped;

        while (!this.isEmpty()) {
            popped = this.poll();
            if (popped >= v1 && popped <= v2) {
                result += popped;
            }
        }

        return result;
    }

    @Override
    public Integer sumRangeAtLevel(int level) {
        Integer result = 0;
        Object[] array = this.toArray();

        int start = (int) Math.pow(2, level) - 1;
        int end = (int) (Math.pow(2, level + 1) - 1);

        try {
            for (int i = start; i < end; i++) {
                result += (Integer) array[i];
            }
        } catch (RuntimeException ignored) {
        }

        return result;
    }

/*
    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new HeapSumImpl();

        heap.add(1);
        heap.add(3);
        heap.add(5);
        heap.add(7);
        heap.add(9);
        heap.add(11);
        heap.add(13);
        heap.add(15);
        heap.add(17);

        //->Teste sumRangeAtLevel
//        System.out.println(((HeapSumImpl) heap).sumRangeAtLevel(0));            //Espera 1
//        System.out.println(((HeapSumImpl) heap).sumRangeAtLevel(1));            //Espera 8
//        System.out.println(((HeapSumImpl) heap).sumRangeAtLevel(2));            //Espera 40
//        System.out.println(((HeapSumImpl) heap).sumRangeAtLevel(3));            //Espera 32
//        System.out.println(((HeapSumImpl) heap).sumRangeAtLevel(4));            //Espera 0, pois nivel nao existe
//        System.out.println(((HeapSumImpl) heap).sumRangeAtLevel(42));           //Espera 0, pois nivel nao existe

        //-> Teste sumRangeOrderStatistics
//        System.out.println(((HeapSumImpl) heap).sumRangeOrderStatistics(3,8));
//        System.out.println(((HeapSumImpl) heap).sumRangeOrderStatistics(1,9));

        //-> Teste sumRangeBetween
//        System.out.println(((HeapSumImpl) heap).sumRangeBetween(6, 15));

    }
*/
}
