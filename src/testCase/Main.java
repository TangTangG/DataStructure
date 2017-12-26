package testCase;

import testCase.implement.OneWayTest;
import model.Bean;

/**
 * @author Caigao.Tang
 * @date  2017/9/22.
 * description：
 */
public class Main {

    public static void main(String[] args) {
        linkedListTest();
    }

    private static void linkedListTest() {
        OneWayTest newRoot = new OneWayTest();
        System.out.println(newRoot.toString());
        for (int i = 0; i < 6; i++) {
            newRoot.insert(new Bean(String.valueOf(i),i));
        }
        System.out.println(newRoot.toString());
        System.out.println(newRoot.contain(new Bean("2",2)));
        System.out.println(newRoot.contain(new Bean("2",10)));
        System.out.println(newRoot.update(new Bean("2",2),
                new Bean("2wwww",2)));
        System.out.println(newRoot.toString());
        System.out.println(newRoot.remove(new Bean("2wwww",2)));
        System.out.println(newRoot.toString());
        System.out.println(newRoot.getFirst().toString() + " "+newRoot.getLast().toString());
        System.out.println(newRoot.reverse());
        System.out.println(newRoot.getFirst().toString() + " "+newRoot.getLast().toString());
        //单向链表
       /* TangLinkedList root = TangLinkedList.obtain("0",0);
        for (int i = 1; i < 10; i++) {
            root.insert(TangLinkedList.obtain(String.valueOf(i),i));
        }
        System.out.println(root.toString());
        OneWay query = root.query("2",1);
//        query.element = "110";
        System.out.println(query.toString());

        System.out.println(root.update("110",8));

        System.out.println(root.remove(TangLinkedList.obtain("4",1)).toString());
        root = (TangLinkedList) OneWay.reverse(root);
//        System.out.println(OneWay.reverse(root));
        System.out.println(root.getLast());*/
        //双向链表
        /*
        DTangLinkedList linkedList = DTangLinkedList.obtain("0",0);
        for (int i = 1; i < 10; i++) {
            linkedList.insert(String.valueOf(i),i);
        }
        System.out.println(linkedList.toString());

        linkedList.preInsert(DTangLinkedList.obtain("110",110));
        System.out.println(linkedList.transToString());
        linkedList.innerInsert(DTangLinkedList.obtain("2",2),DTangLinkedList.obtain("111111",111),true);
        System.out.println(linkedList.transToString());
        linkedList.innerInsert(DTangLinkedList.obtain("5",5),DTangLinkedList.obtain("112",112),false);
        System.out.println(linkedList.transToString());

        linkedList.update(3,"3223222");
        System.out.println(linkedList.transToString());

        linkedList.remove(4);
        System.out.println(linkedList.transToString());

        System.out.println(linkedList.query(5).element);
        */

        //循环链表

    }

}
