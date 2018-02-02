package implement.algorithm;

import sort.BubbleSort;
import sort.HeapSort;
import sort.InsertionSort;
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

    private static final int BIG_ARRAY = 50000;
    private static int[] forTest;

    static {
        if (TEST_TYPE == TEST_SMALL) {
            forTest = new int[]{20, 3, 44, 38, 5, 47, 80, 15, 36, 26,
                    8, 27, 2, 60, 46, 4, 19, 51, 48, 6, 12, 1, 100, 50, 13, 10};
        } else {
            forTest = new int[BIG_ARRAY];
            Random r = new Random();
            for (int i = 0; i < BIG_ARRAY; i++) {
                forTest[i] = r.nextInt();
            }
        }
    }


    @AnalysisTime
    @Test(enable = true)
    private void selectionSort() {
        printSort(SelectionSort.sort(getTestArray()));
    }

    @AnalysisTime
    @Test(enable = true)
    private void bubbleSort() {
        printSort(BubbleSort.sort(getTestArray()));

    }

    @AnalysisTime
    @Test(enable = true)
    private void insertionSort() {
        printSort(InsertionSort.sort(getTestArray()));
    }

    @AnalysisTime
    @Test(enable = true)
    private void heapSort() {
        printSort(HeapSort.sort(getTestArray()));
    }

    @AnalysisTime
    @Test(enable = true)
    private void shellSort() {
        printSort(ShellSort.sort(getTestArray()));
    }

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
