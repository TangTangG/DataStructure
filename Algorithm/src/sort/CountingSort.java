package sort;

/**
 * @author Caigao.Tang
 * @date 2018/1/26
 * description:
 */
public final class CountingSort {

    public static int[] sort(int[] unSort) {
        if (unSort == null || unSort.length <= 1) {
            return unSort;
        }
        int max = unSort[0], min = unSort[0];
        for (int i : unSort) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int l = unSort.length;
        int countL = ((max - min) & 0x7FFFFFFF) + 1;
        if (countL < 0) {
            throw new IndexOutOfBoundsException();
        }
        //There may throw java.lang.OutOfMemoryError: Java heap space
        //Decide by your IDE JVM setting.
        int[] c = new int[countL];
        //Get the number of occurrences for each of the arrays.
        for (int i : unSort) {
            c[i - min]++;
        }
        //Get the element index in sorted array.
        for (int i = 1; i < countL; i++) {
            c[i] += c[i - 1];
        }
        int[] result = new int[l];
        for (int i : unSort) {
            result[--c[i - min]] = i;
        }
        return result;
    }
}
