package testCase;

import implement.BinarySearchTreeTest;
import implement.BinaryTreeTest;
import implement.HeapTest;
import implement.QueueTest;
import implement.StackTest;

/**
 * @author Caigao.Tang
 * @date 2017/9/22.
 * description：
 */
public class Main {

    public static void main(String[] args) {
        testCase.TestIface testCase = null;
//        testCase = new OneWayTest();
//        testCase = new BinaryTreeTest();
//        testCase = new BinarySearchTreeTest();
//        testCase = new QueueTest();
//        testCase = new HeapTest();
//        testCase = new StackTest();
        if (testCase != null) {
            testCase.test();
        }

    }

    private static void treeTest() {

    }

}
