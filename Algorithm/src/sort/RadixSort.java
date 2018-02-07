package sort;

/**
 * Radix sort,use the features of the data will be sort.
 * Each number of integer data affects its final position in the array.
 * So if we sort by the digit in the data,and we finally get an sorted array.
 *
 * @author Caigao.Tang
 * @date 2018/1/26
 * @see "https://en.wikipedia.org/wiki/Radix_sort"
 */
public final class RadixSort {

    public static int[] sort(int[] unSort) {
        if (unSort == null || unSort.length <= 1) {
            return unSort;
        }
        int d = 0;
        for (int i : unSort) {
            //i maybe an negative number.
            int temp = (int) (Math.log(i & 0x7FFFFFFF) + 1);
            if (temp > d) {
                d = temp;
            }
        }
        //Consider positive and negative numbers.
        //digit:-9 <= d <= 9
        int[][] buckets = new int[19][10];
        for (int i = 0; i <= d; i++) {
            int index;
            for (int e : unSort) {
                //Get the digit.
                int b = (e / ((int)Math.pow(10,i))) % 10 + 9;
                index = buckets[b][0];
                //if bucket array to small,double capacity.
                if ((buckets[b][0] = ++index) == buckets[b].length) {
                    int[] array = new int[index << 1];
                    System.arraycopy(buckets[b], 0, array, 0, index);
                    buckets[b] = array;
                }
                //put in bucket
                buckets[b][index] = e;
            }
            index = 0;
            for (int j = 0; j < 19; j++) {
                //Read data from bucket to unSort.
                int[] bucket = buckets[j];
                int size = bucket[0];
                for (int k = 1; k <= size; k++) {
                    unSort[index++] = bucket[k];
                }
                //reset bucket size.
                buckets[j][0] = 0;
            }
        }
        return unSort;
    }
}
