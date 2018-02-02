package sort;

/**
 * @author Caigao.Tang
 * @date 2018/1/26
 * description:
 * O(N^2)
 * In this solution, we assume that the data is ordered from the beginning of the array,
 * so every time we need to find a place in the ordered array before the current element.
 *
 * Find position behind of current element,and then insert.
 */
public final class InsertionSort {

    public static int[] sort(int[] unSort) {
        if (unSort == null || unSort.length <= 1) {
            return unSort;
        }
        int l = unSort.length,temp,j;
        for (int i = 0; i < l; i++) {
            temp = unSort[i];
            //find position
            for (j = i; j > 0 && temp < unSort[j - 1]; j--) {
                unSort[j] = unSort[j - 1];
            }
            unSort[j] = temp;
        }
        //The above approach significantly reduces the number of
        //useless data exchange,so the algorithm is more efficient.
        //The bad version as follows:
        /*
        for (int i = 0; i < l; i++) {
            for (int j = i; j > 0; j--) {
                if (unSort[j] < unSort[j - 1]){
                    temp = unSort[i];
                    unSort[j] = unSort[j - 1];
                    unSort[j - 1] = temp;
                }
            }
        }
        */
        return unSort;
    }


}
