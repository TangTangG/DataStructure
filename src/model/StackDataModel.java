package model;

/**
 * @author Caigao.Tang
 * @date 2018/1/12
 * description:
 */
public abstract class StackDataModel<E> implements DataModel<E> {

    @Override
    public E insert(E element) {
        return push(element);
    }

    /**
     * Push an specified data in this Stack.
     *
     * @param e the element to be add.
     * @return E the added element
     */
    protected abstract E push(E e);

    /**
     * Retrieves, but does not remove, the last of this stack,
     * or returns {@code null} if this stack is empty.
     *
     * @return E the last element
     */
    protected abstract E peek();

    /**
     * Retrieves, and remove, the last of this stack,
     * or returns {@code null} if this stack is empty.
     *
     * @return E the last element
     */
    protected abstract E pop();
}
