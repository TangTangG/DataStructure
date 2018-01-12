package model;

/**
 * @author Caigao.Tang
 * @date 2018/1/11
 * description:
 */
public abstract class QueueDataModel<E> implements DataModel<E> {

    @Override
    public E insert(E element) {
        return offer(element);
    }

    /**
     * Inserts the specified element into this queue.
     *
     * @param e the element to add
     * @return E the element inserted
     */
    public abstract E offer(E e);

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public abstract E poll();

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public abstract E peek();
}
