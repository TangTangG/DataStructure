package implement;

import testCase.TestIface;
import tree.BinaryTree;

/**
 * @author Caigao.Tang
 * @date 2018/1/9.
 * description：
 */
public class BinaryTreeTest extends BinaryTree<Bean> implements TestIface {

    @Override
    public void test() {
        BinaryTree<Bean> tree = new BinaryTree<>();
        for (int i = 0; i < 12; i++) {
            tree.insert(new Bean(String.valueOf(i),i));
        }
//        tree.remove(new Bean(String.valueOf(0),0));
        tree.remove(new Bean(String.valueOf(5),5));
//        tree.remove(new Bean(String.valueOf(4),4));
        System.out.println(tree.toString());
    }
}
