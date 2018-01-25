package implement;

import t.Test;
import heap.Heap;

/**
 * @author Caigao.Tang
 * @date 2018/1/10
 * description:
 */
public class HeapTest extends Heap<Bean> {

    @Test
    public void test() {
        HeapTest heapTest = new HeapTest();
        for (int i = 0; i < 12; i++) {
            heapTest.insert(new Bean(String.valueOf(i), i));
        }
        System.out.println(heapTest.toString());
        heapTest.remove(new Bean(String.valueOf(2), 2));
        System.out.println(heapTest.top());
        System.out.println(heapTest.take());
        System.out.println(heapTest.take());
        System.out.println(heapTest.take());
        System.out.println(heapTest.take());
    }
}
