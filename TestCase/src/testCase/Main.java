package testCase;

import implement.AVLTreeTest;
import implement.BinarySearchTreeTest;
import implement.BinaryTreeTest;
import implement.HeapTest;
import implement.OneWayTest;
import implement.QueueTest;
import implement.RedBlackTreeTest;
import implement.StackTest;
import implement.algorithm.SortTest;
import t.DoTest;

/**
 * @author Caigao.Tang
 * @date 2017/9/22.
 * description：
 */
public class Main {

    public static void main(String[] args) {
        DoTest test = new DoTest();
        test.initCls(
                SortTest.class,
                HeapTest.class,
                StackTest.class,
                QueueTest.class,
                OneWayTest.class,
                AVLTreeTest.class,
                BinaryTreeTest.class,
                RedBlackTreeTest.class,
                BinarySearchTreeTest.class);
        test.begin();
    }
}
