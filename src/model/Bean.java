package model;

/**
 * Created by CaiGao on 2017/9/29.
 */
public class Bean {
    String s;
    int anInt;

    public Bean(String s, int anInt) {
        this.s = s;
        this.anInt = anInt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Bean)){
            return false;
        }
        return anInt == ((Bean)obj).anInt;
    }

    @Override
    public String toString() {
        return "s:"+s+" i:"+anInt;
    }
}
