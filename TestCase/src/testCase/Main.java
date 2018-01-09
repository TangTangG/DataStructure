package testCase;

import implement.BinarySearchTreeTest;
import implement.BinaryTreeTest;

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
        if (testCase != null) {
            testCase.test();
        }

    }

    private static void treeTest() {

    }

}
