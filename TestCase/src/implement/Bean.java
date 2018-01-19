package implement;

/**
 * Created by CaiGao on 2017/9/29.
 */
public final class Bean implements Comparable{
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

    public Bean copy(){
        return new Bean(s,anInt);
    }

    @Override
    public String toString() {
        return "s:"+s+" i:"+anInt;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof Bean)){
            throw new IllegalArgumentException("unexpected args(such as null or not an instance).");
        }
        return anInt - ((Bean)o).anInt;
    }
}
