package tree;

import java.util.Comparator;

/**
 * @author Caigao.Tang
 * @date 2018/1/15
 * description:
 * An self balanced binary search tree.
 * <p>
 * This model inherited from BinarySearchTree,
 * only add balance method.
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    /**
     * Can defined in method balance();
     * defined at this place,just want to high-light in IDEA.
     */
    private final int LEFT_LEFT = 1;
    private final int LEFT_RIGHT = 2;
    private final int RIGHT_RIGHT = 3;
    private final int RIGHT_LEFT = 4;

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> c) {
        super(c);
        builder = new AvlBuilder<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E insert(E element) {
        if (element == null) {
            return null;
        }
        AvlNode<E> node = (AvlNode<E>) add(element);
        node = (AvlNode<E>) node.parent;
        while (node != null) {
            int old = node.h;
            node.updateHeight();
            //Check and balance this node if it is necessary.
            balance(node);
            int now = node.h;
            //after checked or balanced height is same as old,
            //do not need to check it parent,balance finished.
            if (old == now) {
                break;
            }
            node = (AvlNode<E>) node.parent;
        }
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(E element) {
        if (element == null || root == null) {
            return false;
        }
        Node<E> remove = comparator != null ? findUseComparator(element) : find(element);
        if (remove == null) {
            return false;
        }
        //real remove node`s parent.
        AvlNode<E> node = (AvlNode<E>) deleteNode(remove);
        //check all parent balance
        while (node != null) {
            node.updateHeight();
            balance(node);
            node = (AvlNode<E>) node.parent;
        }
        return true;
    }

    private void balance(AvlNode<E> node) {
        int factor = node.balanceFactor();
        if (factor > 1 || factor < -1) {
            int balanceType;
            AvlNode child;
            if (factor < 0) {
                child = (AvlNode) node.left;
                factor = child.balanceFactor();
                if (factor < 0) {
                    balanceType = LEFT_LEFT;
                } else {
                    balanceType = LEFT_RIGHT;
                }
            } else {
                child = (AvlNode) node.right;
                factor = child.balanceFactor();
                if (factor < 0) {
                    balanceType = RIGHT_LEFT;
                } else {
                    balanceType = RIGHT_RIGHT;
                }
            }
            switch (balanceType) {
                case LEFT_LEFT:
                    rotateRight(node);
                    break;
                case LEFT_RIGHT:
                    rotateLeft(child);
                    rotateRight(node);
                    break;
                case RIGHT_LEFT:
                    rotateRight(child);
                    rotateLeft(node);
                    break;
                case RIGHT_RIGHT:
                    rotateLeft(node);
                    break;
                default:
                    break;
            }
            child.updateHeight();
            node.updateHeight();
        }
    }

    private static class AvlNode<E> extends Node<E> {

        int h = 1;

        AvlNode(E e, Node p, Node l, Node r) {
            super(e, p, l, r);
        }

        @SuppressWarnings("unchecked")
        void updateHeight() {
            int rH = right != null ? ((AvlNode<E>) right).h : 0;
            int lH = left != null ? ((AvlNode<E>) left).h : 0;
            h = rH > lH ? (rH + 1) : (lH + 1);
        }

        @SuppressWarnings("unchecked")
        int balanceFactor() {
            int rH = right != null ? ((AvlNode<E>) right).h : 0;
            int lH = left != null ? ((AvlNode<E>) left).h : 0;
            return rH - lH;
        }

        @Override
        public String toString() {
            return "{h:" +
                    h +
                    "  [" +
                    super.toString() +
                    "]}";
        }
    }

    private static class AvlBuilder<E> extends DefaultNodeBuilder<E> {
        @Override
        public Node<E> build(E e, Node p, Node l, Node r) {
            if (e == null) {
                return null;
            }
            return new AvlNode<>(e, p, l, r);
        }
    }
}
