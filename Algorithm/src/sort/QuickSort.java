package sort;

/**
 * Quick sort can be considered an improved version of bubble sort.
 *
 * In bubble sort,we compare array[i] to every element
 * in this array,if it bigger,exchange.This also leads to
 * excessive data exchange and comparison.In Shell sort
 * we use longer gap to fix this problem.
 *
 * So we can do this in bubble sort.We consider
 * the element which compare to array[i] as a pivot.
 * We fixed the pivot at the same time (try to)
 * expand the swap interval so we could sort faster than bubbling.
 *
 * O(N * log N)
 *
 * @author Caigao.Tang
 * @date 2018/1/26
 */
public final class QuickSort {

    public static int[] sort(int[] unSort) {
        if (unSort == null || unSort.length <= 1) {
            return unSort;
        }
        quickSort(unSort, 0, unSort.length - 1);
        return unSort;
    }

    private static void quickSort(int[] unSort, int low, int high) {
        int pivot = unSort[(low + high) >>> 1];
        int i = low;
        int j = high;
        int temp;
        while (i <= j) {
            //This code can make less exchange.
            //Because it make exchange gap become (j - i).
            //So if the array already ordered(but reverse),
            //quick sort just like bubble sort,exchange every data.
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
        //Check if the array head or tail is coming.
        if (j > low) {
            quickSort(unSort, low, j);
        }
        if (i < high) {
            quickSort(unSort, i, high);
        }
    }
}
