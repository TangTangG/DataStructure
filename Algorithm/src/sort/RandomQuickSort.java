package sort;

import java.util.Random;

/**
 * An quick sort version that pivot index is random int.
 *
 * @author Caigao.Tang
 * @date 2018/1/26
 * description:
 */
public final class RandomQuickSort {

    private static final Random RANDOM = new Random();

    public static int[] sort(int[] unSort) {
        if (unSort == null || unSort.length <= 1) {
            return unSort;
        }
        quickSort(unSort, 0, unSort.length - 1);
        return unSort;
    }

    private static void quickSort(int[] unSort, int low, int high) {
        int pivot = unSort[low + RANDOM.nextInt(high - low + 1)];
        int i = low;
        int j = high;
        int temp;
        while (i <= j) {
            while (unSort[i] < pivot) {
                i++;
            }
            while (unSort[j] > pivot) {
                j--;
            }
            if (i <= j) {
                temp = unSort[i];
                unSort[i] = unSort[j];
                unSort[j] = temp;
                i++;
                j--;
            }
        }
        if (j > low) {
            quickSort(unSort, low, j);
        }
        if (i < high) {
            quickSort(unSort, i, high);
        }
    }

}
