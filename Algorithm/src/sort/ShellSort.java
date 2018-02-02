package sort;

/**
 * Shell sort,improvements to insert (bubbling) sort.
 * By spacing the elements at different distances,
 * the spacing between the elements is increased,
 * thereby solving the problem of effervescence,
 * which can be exacerbated by bubbling and inserting sorts
 * at a time by exchanging only one element at a time.
 *
 * The efficiency of the algorithm depends on the step size of each comparison,
 * it determines the number of comparisons.
 * (Here only conclusion, if you need to know, please click the link following see)
 * General term(k>=1)   | Concrete gaps       |  Worst-case time complexity   |  Author and year of publication
 *   2^k - 1            | 1,3,7,15,31,63...   |         O(N^(3/2))            |        Hibbard, 1963
 *   2^i * 3^j          | 1,2,3,4,6,8,9,12... |         O(N * (logN)^2)       |        Pratt, 1971
 *  (3^k - 1)/2         | 1,4,13,40,121...    |         O(N^(3/2))            |        Pratt, 1971
 * 4^k + 3* 2^(k-1) + 1 | 1,8,123,77,281...   |         O(N^(4/3))            |        Sedgewick, 1986
 *
 *
 * @author Caigao.Tang
 * @date 2018/1/30
 * @see "https://en.wikipedia.org/wiki/Shellsort"
 */
public final class ShellSort {

    public static int[] sort(int[] unSort) {
        if (unSort == null || unSort.length <= 1) {
            return unSort;
        }
        int l = unSort.length;
        int temp;
        int gap = 1;
        // Find start gap about this array.
        // <O(n^(3/2)) by Knuth,1973>:1, 4, 13, 40, 121, ...
        while (gap < l / 3) {
            gap = 3 * gap + 1;
        }
        int j;
        for (; gap > 0; gap = gap / 3) {
            for (int i = gap; i < l; i++) {
                temp = unSort[i];
                for (j = i; j >= gap && temp < unSort[j - gap]; j -= gap) {
                    unSort[j] = unSort[j - gap];
                }
                unSort[j] = temp;
            }
        }
        return unSort;
    }

}
