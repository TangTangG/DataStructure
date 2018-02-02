package implement;

import t.AnalysisTime;
import t.Test;
import tree.AVLTree;

/**
 * @author Caigao.Tang
 * @date 2018/1/16
 * description:
 */
public class AVLTreeTest extends AVLTree<Bean> {

    @AnalysisTime
    @Test
    private void test() {
        AVLTreeTest test = new AVLTreeTest();
        int[] vals = new int[]{62,16,71,3,50,70,77,94};
        for (int i: vals) {
            test.insert(new Bean(String.valueOf(i),i));
        }
        System.out.println(test);
        System.out.println("---------------");
        test.remove(new Bean(String.valueOf(70),70));
        System.out.println(test);
        System.out.println("---------------");
        vals = new int[]{5,11,70};
        for (int i: vals) {
            test.insert(new Bean(String.valueOf(i),i));
        }
        System.out.println(test);
        System.out.println("---------------");
        test.insert(new Bean(String.valueOf(2),2));
        System.out.println(test);
        System.out.println("---------------");
        test.remove(new Bean(String.valueOf(94),94));
        System.out.println(test);
        System.out.println("---------------");
        test.insert(new Bean(String.valueOf(49),49));
        System.out.println(test);
        System.out.println("---------------");
        test.insert(new Bean(String.valueOf(65),65));
        test.remove(new Bean(String.valueOf(49),49));
        System.out.println(test);
        System.out.println("---------------");
        test.remove(new Bean(String.valueOf(70),70));
        System.out.println(test);
        System.out.println("---------------");
        vals = new int[]{10,4,8,6,60,12,72,68,52,42};
        for (int i: vals) {
            test.insert(new Bean(String.valueOf(i),i));
        }
        System.out.println(test);
        System.out.println("---------------");
        test.insert(new Bean(String.valueOf(9),9));
        System.out.println(test);
        System.out.println("---------------");
        vals = new int[]{2,4,3};
        for (int i: vals) {
            test.remove(new Bean(String.valueOf(i),i));
        }
        System.out.println(test);
        System.out.println("---------------");
        test.remove(new Bean(String.valueOf(12),12));
        System.out.println(test);
        System.out.println("---------------");
    }
}
