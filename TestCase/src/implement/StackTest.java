package implement;

import t.Test;
import stack.Stack;

/**
 * @author Caigao.Tang
 * @date 2018/1/12
 * description:
 */
public class StackTest extends Stack<Bean> {

    @Test
    public void test() {
        StackTest test = new StackTest();
        for (int i = 0; i < 10; i++) {
            test.push(new Bean(String.valueOf(i), i));
        }
        System.out.println(test);

        System.out.println(test.pop());
        System.out.println(test.pop());
        test.remove(new Bean(String.valueOf(5), 5));
        System.out.println(test);
        System.out.println(test.pop());
        test.remove(new Bean(String.valueOf(6), 6));
        System.out.println(test.pop());
        System.out.println(test);
        System.out.println(test.size());
    }

}
