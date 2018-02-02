package sort;

/**
 * @author Caigao.Tang
 * @date 2018/1/29
 * O(N * logN)
 */
public final class HeapSort {

    public static int[] sort(int[] unSort) {
        if (unSort == null || unSort.length <= 1) {
            return unSort;
        }
        int l = unSort.length;
        int[] heap = new int[l];
        //build a max heap,direction down -> up,O(N)
        //this code loop time decide by height of heap.
        //The following is a proof of its time complexity:
        //if has N element,h(height) = logN
        //count = 1 * 2 ^ (logN - 1) + 2 * 2 ^ (logN - 2)+...+ (logN - 1) * 2 ^ (1) + (logN) * 2^0 -----(1)
        //2count = 1 * 2 ^ (logN) + 2 * 2 ^ (logN - 1)+...+ (logN - 1) * 2 ^ (2) + (logN) * 2^1    -----(2)
        //(2) - (1):
        //count = 1 * 2 ^ (logN) + 1 * 2^(logN - 1) +...+ 1 * 2 ^ 2 + 1 * 2 ^ (1) - (logN)
        //count = 2N-1-logN ~ O(N)
        for (int i = 0; i < l; i++) {
            int j = i;
            while (j > 0) {
                int parent = (j - 1) >>> 1;
                if (heap[parent] > unSort[i]) {
                    break;
                }
                heap[j] = heap[parent];
                j = parent;
            }
            heap[j] = unSort[i];
        }
        //O(N * logN)
        for (int i = 0; i < l; i++) {
            int curSize = l - i - 1;
            int last = heap[curSize];
            heap[curSize] = heap[0];

            int half = (curSize) >> 1;
            int idx = 0;
            while (idx < half) {
                int child = ( idx << 1) + 1;
                int right = child + 1;
                if (right < curSize && heap[right] > heap[child]){
                    child = right;
                }
                if (heap[child] < last){
                    break;
                }
                heap[idx] = heap[child];
                idx = child;
            }
            heap[idx] = last;
        }
        return heap;
    }
}
