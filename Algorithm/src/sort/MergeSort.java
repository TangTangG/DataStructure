package sort;

/**
 * Merge sort is a divide and conquer algorithm.
 * Conceptually, a merge sort works as follows:
 *  1.Divide the unsorted list into n sublists,
 *  each containing 1 element (a list of 1 element is considered sorted).
 *  2.Repeatedly merge sublists to produce new sorted sublists
 *  until there is only 1 sublist remaining. This will be the sorted list.
 *
 * The merge process, if represented from top to bottom,
 * is like an inverted complete binary tree.
 *
 * O(N * logN)
 *
 * @author Caigao.Tang
 * @date 2018/1/26
 */
public final class MergeSort {

    public static int[] sort(int[] unSort) {
        if (unSort == null || unSort.length <= 1) {
            return unSort;
        }
        int l = unSort.length;
        int gap = 1;
        //Get gap,used to decide how to divide array.
        while (gap < l) {
            //An temp array,store merged data.
            int[] t = new int[l];
            //Arrays the array according to the interval,
            //and merges the array after the partition.
            for (int lIdx = 0; lIdx < l; lIdx += (gap << 1)) {
                //Get left and right start idx.
                int rIdx = Math.min(lIdx + gap, l);
                //Do merge.
                int end = Math.min(rIdx + (rIdx - lIdx), l);
                int cursor = lIdx;
                int lCursor = lIdx;
                int rCursor = rIdx;
                while (lCursor < rIdx && rCursor < end) {
                    if (unSort[lCursor] > unSort[rCursor]) {
                        t[cursor++] = unSort[rCursor++];
                    } else {
                        t[cursor++] = unSort[lCursor++];
                    }
                }
                if (lCursor < rIdx) {
                    System.arraycopy(unSort, lCursor, t, cursor, rIdx - lCursor);
                }
                if (rCursor < end) {
                    System.arraycopy(unSort, rCursor, t, cursor, end - rCursor);
                }
            }
            unSort = t;
            gap = gap << 1;
        }
        return unSort;
    }
}
