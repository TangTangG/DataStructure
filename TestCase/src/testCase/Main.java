package testCase;

import implement.AVLTreeTest;
import implement.BinarySearchTreeTest;
import implement.BinaryTreeTest;
import implement.HeapTest;
import implement.OneWayTest;
import implement.QueueTest;
import implement.RedBlackTreeTest;
import implement.StackTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Caigao.Tang
 * @date 2017/9/22.
 * description：
 */
public class Main {

    private final static class DoTest {

        private static Class<?>[] testCls = new Class[]{
                HeapTest.class,
                StackTest.class,
                QueueTest.class,
                OneWayTest.class,
                AVLTreeTest.class,
                BinaryTreeTest.class,
                RedBlackTreeTest.class,
                BinarySearchTreeTest.class
        };

        void begin() {
            for (Class<?> cls : testCls) {
                Method test = getMethod(cls);
                if (test != null) {
                    try {
                        test.invoke(cls.newInstance());
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        System.out.println("do test on error");
                        e.printStackTrace();
                    }
                }
            }
        }

        private Method getMethod(Class<?> cls) {
            Method[] methods = cls.getMethods();
            if (methods == null || methods.length <= 0) {
                return null;
            }
            for (Method method : methods) {
                Test a = method.getAnnotation(Test.class);
                if (a != null && a.enable()) {
                    method.setAccessible(true);
                    return method;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        new DoTest().begin();
    }
}
