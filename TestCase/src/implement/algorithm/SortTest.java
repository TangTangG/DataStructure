package implement.algorithm;

import sort.BubbleSort;
import sort.BucketSort;
import sort.CountingSort;
import sort.HeapSort;
import sort.InsertionSort;
import sort.MergeSort;
import sort.QuickSort;
import sort.RadixSort;
import sort.RandomQuickSort;
import sort.SelectionSort;
import sort.ShellSort;
import t.AnalysisTime;
import t.Test;

import java.util.Random;

/**
 * @author Caigao.Tang
 * @date 2018/1/26
 * description:
 */
public class SortTest {

    private static final int TEST_SMALL = 0;
    private static final int TEST_BIG = 1;
    private static final int TEST_TYPE = TEST_BIG;

    private static final int BIG_ARRAY = 100000;
    private static int[] forTest;

    static {
        if (TEST_TYPE == TEST_SMALL) {
            forTest = new int[]{20, 3, 44, 38, 5,13, 47, 80, 15, 36, 26,
                    8, 27, 2, 60, 46, 4, 19,0, 51, 48, 6, 51,12, 1, 100, 50, 13, 10};
        } else {
            forTest = new int[BIG_ARRAY];
            Random r = new Random();
            for (int i = 0; i < BIG_ARRAY; i++) {
                forTest[i] = r.nextInt();
            }
        }
    }

    // *****************************Non-comparison sort ***************************** start

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void bucketSort() {
        //this may throw error when test big array.
        printSort(BucketSort.sort(getTestArray()));
    }

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void radixSort() {
        printSort(RadixSort.sort(getTestArray()));
    }

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void countingSort() {
        //this may throw error when test big array.
        printSort(CountingSort.sort(getTestArray()));
    }

    // *****************************Non-comparison sort ***************************** end
    
    //*******************************************************************************
    
    //***************************** Compare sort       *****************************start

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void randomQuickSort() {
        printSort(RandomQuickSort.sort(getTestArray()));
    }

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void quickSort() {
        printSort(QuickSort.sort(getTestArray()));
    }

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void mergeSort() {
        printSort(MergeSort.sort(getTestArray()));
    }

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void selectionSort() {
        printSort(SelectionSort.sort(getTestArray()));
    }

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void bubbleSort() {
        printSort(BubbleSort.sort(getTestArray()));
    }

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void insertionSort() {
        printSort(InsertionSort.sort(getTestArray()));
    }

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void heapSort() {
        printSort(HeapSort.sort(getTestArray()));
    }

    @AnalysisTime(compare = true)
    @Test(enable = true)
    private void shellSort() {
        printSort(ShellSort.sort(getTestArray()));
    }
    // *****************************Compare sort        ***************************** end

    private int[] getTestArray() {
        int[] array = new int[forTest.length];
        System.arraycopy(forTest, 0, array, 0, forTest.length);
        return array;
    }

    private void printSort(int[] sort) {
        if (TEST_TYPE == TEST_BIG) {
            return;
        }
        StringBuilder b = new StringBuilder();
        for (int s : sort) {
            b.append(s).append(",");
        }
        System.out.println(b.toString());
    }
}
