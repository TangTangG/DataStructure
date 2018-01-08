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
        public Node parent;
        public Node right;

        public Node(E e, Node p,Node l, Node r) {
            this.element = e;
            this.parent = p;
            this.left = l;
            this.right = r;
        }

        public Node(E e, Node p) {
            this.element = e;
            this.parent = p;
            this.left = null;
            this.right = null;
        }

        public Node<E> copy(){
            return new Node<E>(element,parent,left,right);
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
