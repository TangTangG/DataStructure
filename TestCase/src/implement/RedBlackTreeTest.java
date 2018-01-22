package implement;

import testCase.Test;
import tree.RedBlackTree;

/**
 * @author Caigao.Tang
 * @date 2018/1/22
 * description:
 */
public class RedBlackTreeTest extends RedBlackTree<Bean> {

    @Test
    public void redBlack(){
        RedBlackTreeTest test = new RedBlackTreeTest();
        int[] data = new int[]{298,657,246,483,169,370};
        for (int i: data) {
            test.insert(new Bean(String.valueOf(i),i));
        }
        System.out.println(test.toString());
        System.out.println("---------------------------------");
        test.insert(new Bean(String.valueOf(820),820));
        System.out.println(test.toString());
        System.out.println("---------------------------------");
        test.remove(new Bean(String.valueOf(246),246));
        System.out.println(test.toString());
        System.out.println("---------------------------------");
    }

}
