package testCase;

import implement.BinarySearchTreeTest;
import implement.BinaryTreeTest;
import implement.HeapTest;

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
//        testCase = new HeapTest();
        if (testCase != null) {
            testCase.test();
        }

    }

    private static void treeTest() {

    }

}
