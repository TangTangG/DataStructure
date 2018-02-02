package sort;

/**
 * @author Caigao.Tang
 * @date 2018/1/26
 * description:
 * O(N^2)
 * In this solution, we assume that the data is ordered from the end of the array,
 * so we need to bubble up the elements each time to find their place in the ordered array.
 */
public final class BubbleSort {

    public static int[] sort(int[] unSort) {
        if (unSort == null || unSort.length <= 1) {
            return unSort;
        }
        int l = unSort.length;
        int temp;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l - i - 1; j++) {
                if (unSort[j] > unSort[j + 1]) {
                    temp = unSort[j];
                    unSort[j] = unSort[j + 1];
                    unSort[j + 1] = temp;
                }
            }
        }
        return unSort;
    }
}
