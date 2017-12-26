package model;

/**
 * @author Caigao.Tang
 * @date 2017/12/12.
 * description：
 */
public abstract class TreeDataModel<E> implements DataModel<E>{

    protected boolean contain(E e){
        return false;
    }

    protected static class Node<E>{

        public E element;
        public Node left;
        public Node right;

        public Node(E e, Node l, Node r) {
            this.element = e;
            this.left = l;
            this.right = r;
        }

        @Override
        public String toString(){
            return element.toString();
        }
    }
}
