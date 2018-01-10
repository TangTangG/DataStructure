package model;

/**
 *
 * @author Caigao.Tang
 * @date 2018/1/10
 * description:
 */
public abstract class HeapDataModel<E> implements DataModel<E> {

    /**
     * Return the top of the heap,but do not remove it, and it depends on
     * the comparator or E(which implement comparable) .
     * @return E top of heap,if do not exist return null.
     */
    public abstract E top();

    /**
     *
     * Return the top of the heap,with remove it(take it out).It depends on
     * the comparator or E(which implement comparable) .
     * @return E top of heap,if do not exist return null.
     */
    public abstract E take();
}
