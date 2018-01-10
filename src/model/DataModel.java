package model;

/**
 * @author Caigao.Tang
 * @date  2017/9/22.
 * description:
 * Basic data model of data structure,
 * all implement should contains insert and remove operation.
 */
interface DataModel<E> {

    /**
     * Insert an element to DataModel
     * @param element basic element in this model.
     * @return E which inserted.
     */
    E insert(E element);

    /**
     * Remove an stored data which contains element.
     * @param element Used to compare with stored data.
     * @return true means successful,otherwise return false
     */
    boolean remove(E element);

    interface Consumer<E> {
        /**
         * Node traverse interface
         * @param element stored basic data.
         * @param idx index of element in this model.
         */
        void node(int idx, E element);
    }
}
