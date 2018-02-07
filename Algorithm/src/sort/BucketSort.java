package sort;

/**
 * @author Caigao.Tang
 * @date 2018/2/7
 */
public final class BucketSort {

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
        int countL = ((max - min) & 0x7FFFFFFF) + 1;
        //over flow
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
        int index = 0;
        //Get the element index in sorted array.
        for (int i = 0; i < countL; i++) {
            int size = c[i];
            for (int j = 0; j < size; j++) {
                unSort[index++] = i + min;
            }
        }
        return unSort;
    }

}
