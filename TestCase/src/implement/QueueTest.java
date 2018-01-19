package implement;

import queue.Queue;
import testCase.Test;

/**
 * @author Caigao.Tang
 * @date 2018/1/11
 * description:
 */
public class QueueTest extends Queue<Bean> {

    @Test
    public void test() {
        QueueTest test = new QueueTest();
        for (int i = 0; i < 9; i++) {
            test.offer(new Bean(String.valueOf(i), i));
        }
        System.out.println(test);
        for (int i = 0; i < 8; i++) {
            System.out.println(test.peek());
            test.poll();
        }
        System.out.println(test);
    }
}
