package implement;

import t.Test;
import linkedList.OneWay;

/**
 * Created by CaiGao on 2017/9/29.
 */
public class OneWayTest extends OneWay<Bean> {

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        foreach((idx, element) -> sb.append("  [").append(idx).append("]")
                .append(" val:").append(element.toString()));
        return sb.toString();
    }

    @Test
    public void test() {
        OneWayTest newRoot = new OneWayTest();
        System.out.println(newRoot.toString());
        for (int i = 0; i < 6; i++) {
            newRoot.insert(new Bean(String.valueOf(i), i));
        }
        System.out.println(newRoot.toString());
        System.out.println(newRoot.contain(new Bean("2", 2)));
        System.out.println(newRoot.contain(new Bean("2", 10)));
        System.out.println(newRoot.update(new Bean("2", 2),
                new Bean("2wwww", 2)));
        System.out.println(newRoot.toString());
        System.out.println(newRoot.remove(new Bean("2wwww", 2)));
        System.out.println(newRoot.toString());
        System.out.println(newRoot.getFirst().toString() + " " + newRoot.getLast().toString());
        System.out.println(newRoot.reverse());
        System.out.println(newRoot.getFirst().toString() + " " + newRoot.getLast().toString());
    }
}
