package testCase.implement;

import linkedList.OneWay;
import model.Bean;

/**
 * Created by CaiGao on 2017/9/29.
 */
public class OneWayTest extends OneWay<Bean>{

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        foreach((idx, element) -> sb.append("  [").append(idx).append("]")
                .append(" val:").append(element.toString()));
        return sb.toString();
    }
}
