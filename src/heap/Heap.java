package heap;

import model.HeapDataModel;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Caigao.Tang
 * @date 2018/1/9
 * description:
 * A balanced binary heap.
 * Use array store element.
 * Some code consult <PriorityQueue.java> in JDK 1.8;
 */
public class Heap<E> extends HeapDataModel<E> {

    /**
     * The default size is too large will cause the waste of memory resources;
     * Too small will frequently adjust the size of the array,
     * resulting in a waste of CPU resources.
     * 11 we can change the number of levels in the heap
     * after run insert/remove 4 times.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    /**
     * The two children of heap[n] are heap[2*n+1] and heap[2*(n+1)].
     * The heap is ordered by comparator, or by the elements' natural ordering,
     * if comparator is null: For each node n in the
     * heap and each descendant d of n, n <= d.
     */
    private Object[] heap;
    private int size = 0;
    private Comparator<? super E> comparator;

    public Heap() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public Heap(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public Heap(int capacity, Comparator<? super E> comparator) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "build heap initial capacity must greater than zero.");
        }
        heap = new Object[capacity];
        this.comparator = comparator;
    }

    @Override
    public E insert(E element) {
        if (element == null) {
            throw new IllegalArgumentException("insert element must not be null.");
        }
        int oldSize = size;
        if (oldSize == heap.length) {
            grow(oldSize + 1);
        }
        size = oldSize + 1;
        if (oldSize == 0) {
            heap[0] = element;
        } else {
            siftUp(oldSize, element);
        }
        return element;
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increases the capacity of the array.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = heap.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        heap = Arrays.copyOf(heap, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        // overflow
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    @SuppressWarnings("unchecked")
    private void siftUp(int i, E e) {
        if (comparator != null) {
            siftUpUseComparator(i, e);
        } else {
            Comparable<E> c = (Comparable<E>) e;
            while (i > 0) {
                int parent = (i - 1) >>> 1;
                Object element = heap[parent];
                if (c.compareTo((E) element) >= 0) {
                    break;
                }
                heap[i] = element;
                i = parent;
            }
            heap[i] = e;
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUpUseComparator(int i, E e) {
        while (i > 0) {
            int parent = (i - 1) >>> 1;
            Object element = heap[parent];
            if (comparator.compare(e, (E) element) >= 0) {
                break;
            }
            heap[i] = element;
            i = parent;
        }
        heap[i] = e;
    }

    @Override
    public boolean remove(E element) {
        int idx = indexOf(element);
        if (idx < 0) {
            return false;
        }
        removeAt(idx);
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            heap[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void removeAt(int idx) {
        int i = --size;
        if (idx == i) {
            heap[i] = null;
        } else {
            E o = (E) heap[i];
            heap[i] = null;
            siftDown(idx, o);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDown(int i, Object o) {
        if (comparator != null) {
            siftDownUseComparator(i, o);
        } else {
            int half = size >>> 1;
            Comparable<E> c = (Comparable<E>) o;
            while (i < half) {
                int child = (i << 1) + 1;
                E e = (E) heap[child];
                int right = child + 1;
                //every time find greater child.
                if (right < size && ((Comparable<E>) e).compareTo((E) heap[right]) > 0) {
                    e = (E) heap[child = right];
                }
                if (c.compareTo(e) <= 0) {
                    break;
                }
                heap[i] = e;
                i = child;
            }
            heap[i] = o;
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDownUseComparator(int i, Object o) {
        int half = size >>> 1;
        while (i < half) {
            int child = (i << 1) + 1;
            E e = (E) heap[child];
            int right = child + 1;
            //every time find greater child.
            if (right < size && comparator.compare(e, (E) heap[right]) > 0) {
                e = (E) heap[child = right];
            }
            if (comparator.compare((E) o, e) <= 0) {
                break;
            }
            heap[i] = e;
            i = child;
        }
        heap[i] = o;

    }

    private int indexOf(E e) {
        for (int j = 0; j < size; j++) {
            if (heap[j].equals(e)) {
                return j;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E top() {
        return size > 0 ? (E) heap[0] : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E take() {
        if (size == 0) {
            return null;
        }
        int i = --size;
        E top = (E) heap[0];
        E last = (E) heap[i];
        heap[i] = 0;
        if (i != 0) {
            siftDown(0, last);
        }
        return top;
    }
}
