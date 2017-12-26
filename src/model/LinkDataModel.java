package model;

/**
 * @author Caigao.Tang
 * @date  2017/11/6.
 * description：
 */
public abstract class LinkDataModel<E> implements DataModel<E>{

    public E childAt(int index){
        return null;
    }

    public int indexOf(E element){
        return -1;
    }

    public boolean contain(E element){
        return false;
    }

    public synchronized E set(int index,E element){
        return element;
    }

    public void foreach(DataModel.Consumer<? super E> consumer) {

    }

    protected static class Node<E>{

        public E element;

        public Node(E element) {
            this.element = element;
        }

        @Override
        public String toString(){
            return element.toString();
        }
    }
}
