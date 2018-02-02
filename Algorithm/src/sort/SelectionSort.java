package sort;

/**
 * @author Caigao.Tang
 * @date 2018/1/26
 * description:
 * O(N^2)
 * In this solution, we look for an inheritor of the current element position each time in an unsorted element.
 * Compared to bubbling and insertion sorting, the overhead of exchanging data is reduced.
 */
public final class SelectionSort {

    public static int[] sort(int[] unSort) {
        if (unSort == null || unSort.length <= 1) {
            return unSort;
        }
        int l = unSort.length;
        int min;
        int temp;
        for (int k = 0; k < l; k++) {
            min = k;
            for (int j = k; j < l; j++) {
                if (unSort[j] < unSort[min]) {
                    min = j;
                }
            }
            temp = unSort[k];
            unSort[k] = unSort[min];
            unSort[min] = temp;
        }
        return unSort;
    }

}
