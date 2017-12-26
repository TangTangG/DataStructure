package model;

/**
 * @author Caigao.Tang
 * @date  2017/9/22.
 * description：
 */
interface DataModel<E> {

    /**
     * 添加元素的方法
     * @param element 基本的元素
     * @return e
     */
    E insert(E element);

    /**
     * 移除元素
     * @param element
     * @return true or false
     */
    boolean remove(E element);

    interface Consumer<E> {
        /**
         * node traverse interface
         * @param element 存储的元素
         * @param idx 元素对应的index
         */
        void node(int idx, E element);
    }
}
