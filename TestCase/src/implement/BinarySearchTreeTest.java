package implement;

import t.Test;
import tree.BinarySearchTree;

/**
 * @author Caigao.Tang
 * @date 2018/1/9.
 * description:
 */
public class BinarySearchTreeTest extends BinarySearchTree<Bean> {

    @Test
    public void test() {
        BinarySearchTreeTest tree = new BinarySearchTreeTest();
        int axis = 3;
        tree.insert(new Bean(String.valueOf(axis), axis));
        for (int i = 0; i < 10; i++) {
            Bean element = new Bean(String.valueOf(i), i);
            if (i == axis) {
                element = new Bean(String.valueOf(100), i);
            }
            tree.insert(element);
        }
        System.out.println(tree.toString());
        System.out.println("---------------------------------------");
        tree.remove(new Bean(String.valueOf(6), 6));
        System.out.println(tree.toString());
        System.out.println("---------------------------------------");
        tree.remove(new Bean(String.valueOf(axis), axis));
        System.out.println(tree.toString());
        System.out.println("---------------------------------------");
        tree.remove(new Bean(String.valueOf(16), 16));
        System.out.println(tree.toString());
        System.out.println(tree.max());
        System.out.println(tree.min());
    }

}
