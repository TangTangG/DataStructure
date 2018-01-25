package t;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;


/**
 * @author Caigao.Tang
 * @date 2018/1/25
 * description:
 */
public class DoTest {

    private static Class<?>[] testCls = new Class[]{};

    public void initCls(Class<?>... cls) {
        int i = cls.length;
        testCls = new Class[i];
        System.arraycopy(cls, 0, testCls, 0, i);
    }

    public void begin() {
        class TimeInfo implements Comparable {
            private String clsName;
            private long timeNS;

            private TimeInfo(String clsName, long timeNS) {
                this.clsName = clsName;
                this.timeNS = timeNS;
            }

            @Override
            public int compareTo(Object o) {
                if (!(o instanceof TimeInfo)) {
                    return -1;
                }
                TimeInfo timeInfo = (TimeInfo) o;
                long l = timeNS - timeInfo.timeNS;
                return l > 1 ? 1 : (l < 0 ? -1 : 0);
            }

            @Override
            public String toString() {
                long timeMS = timeNS / 1000000;
                long timeS = timeNS / 1000000000;
                return "____________________" + clsName + "____________________ The test time-consuming:\\\n"
                        + timeNS + "ns | " + timeMS + "ms | " + timeS + "s";
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        }
        Set<TimeInfo> timeHolder = new TreeSet<>();
        for (Class<?> cls : testCls) {
            Method test = getMethod(cls);
            if (test != null) {
                AnalysisTime a = test.getAnnotation(AnalysisTime.class);
                long beginTime = 0L;
                long endTime = 0L;
                try {
                    beginTime = System.nanoTime();
                    test.invoke(cls.newInstance());
                    endTime = System.nanoTime();
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    endTime = System.nanoTime();
                    System.out.println("do test on error");
                    e.printStackTrace();
                } finally {
                    if (a != null) {
                        long timeNS = endTime - beginTime;
                        TimeInfo info = new TimeInfo(cls.getSimpleName(), timeNS);
                        if (a.compare()) {
                            timeHolder.add(info);
                        } else {
                            System.out.println(info.toString());
                        }
                    }
                }
            }
        }
        for (TimeInfo info : timeHolder) {
            System.out.println(info.toString());
        }
    }


    private Method getMethod(Class<?> cls) {
        Method[] methods = cls.getDeclaredMethods();
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
