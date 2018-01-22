package model;

import java.util.ConcurrentModificationException;
import java.util.Stack;

/**
 * @author Caigao.Tang
 * @date 2017/12/12.
 * description:
 */
public abstract class TreeDataModel<E> implements DataModel<E> {

    protected Node<E> root;
    protected int size = 0;
    protected NodeBuilder<E> builder = new DefaultNodeBuilder<>();

    protected void replace(Node<E> oldN, Node<E> newN) {
        if (oldN == null) {
            return;
        }
        Node p = oldN.parent;
        Node l = oldN.left;
        Node r = oldN.right;
        if (newN != null) {
            newN.parent = p;
            newN.left = l;
            newN.right = r;
        }
        if (l != null) {
            l.parent = newN;
        }
        if (r != null) {
            r.parent = newN;
        }
        if (p == null) {
            root = newN;
        } else {
            if (oldN.equals(p.left)) {
                p.left = newN;
            } else {
                p.right = newN;
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected Node<E> preOrder(E e) {
        Stack<Node<E>> l = new Stack<>();
        Node<E> t = root;
        Node<E> result = null;
        while (t != null || !l.empty()) {
            while (t != null) {
                if (e.equals(t.element)) {
                    result = t;
                    break;
                }
                l.push(t);
                t = t.left;
            }
            if (result != null) {
                break;
            }
            if (!l.empty()) {
                t = l.pop().right;
            }
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    protected Node<E> inorder(E e) {
        Stack<Node<E>> l = new Stack<>();
        Node<E> t = root;
        while (t != null || !l.empty()) {
            while (t != null) {
                l.push(t);
                t = t.left;
            }
            if (!l.empty()) {
                t = l.pop();
                if (e.equals(t.element)) {
                    break;
                }
                t = t.right;
            }
        }
        return t;
    }

    protected Node<E> postOrder(E e) {
        Node<E> t = root;
        if (t == null) {
            return null;
        }
        Stack<Node<E>> lr = new Stack<>();
        Node<E> last = null;
        lr.push(t);
        while (!lr.empty()) {
            t = lr.peek();
            @SuppressWarnings("unchecked")
            Node<E> l = t.left;
            @SuppressWarnings("unchecked")
            Node<E> r = t.right;
            boolean access = (r == null && l == null) ||
                    (last != null && (last.equals(r) || last.equals(l)));
            if (access) {
                if (e.equals(t.element)) {
                    break;
                }
                lr.pop();
                last = t;
            } else {
                if (l != null) {
                    lr.push(l);
                }
                if (r != null) {
                    lr.push(r);
                }
            }
        }
        return t;
    }

    protected Node<E> layerOrder(E e) {
        if (root == null) {
            return null;
        }
        int head = 0;
        int tail = 1;
        Node<E> t = root;
        int oldSize = size;
        //use as a queue.
        @SuppressWarnings("unchecked")
        Node<E>[] q = new Node[oldSize];
        q[0] = root;
        while (head < tail) {
            t = q[head];
            if (t.element.equals(e)) {
                break;
            }
            if (t.left != null) {
                q[tail++] = t.left;
            }
            if (t.right != null) {
                q[tail++] = t.right;
            }
            head++;
        }

        return t;
    }

    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    protected boolean contain(E e) {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        if (root == null) {
            return "empty";
        }
        int curD = 0;
        StringBuilder s = new StringBuilder();
        int head = 0;
        int tail = 1;
        Node<E> t;
        int oldSize = size;
        String test = root.toString();
        final int length = test.length();
        int cursor = 0;
        class Nil extends Node<E> {

            private Nil() {
                super(null, null, null, null);
            }

            @Override
            public String toString() {
                StringBuilder b = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    b.append(" ");
                }
                return "";
            }
        }
        Node<E>[] q = new Node[oldSize];
        q[head] = root;
        while (head < tail) {
            if (oldSize != size) {
                throw new ConcurrentModificationException("do not allow modify data when toString()");
            }
            t = q[head];
            int deepSize = (1 << (curD + 1)) - 1;
            if (head == deepSize) {
                curD++;
                s.append("\n");
            }
            s.append(" [").append(t.toString()).append("] ");
            if (t.element != null) {
                cursor++;
            }
            if (cursor == oldSize) {
                break;
            }
            //if cur tail will move to length
            if (tail + 2 > q.length) {
                int intent = (1 << (curD + 2)) - 1;
                if (intent < 0) {
                    throw new IndexOutOfBoundsException("target tree is too deep,can not print it");
                }
                Node<E>[] n = new Node[intent];
                System.arraycopy(q, 0, n, 0, tail);
                q = n;
            }
            if (t.left == null) {
                q[tail++] = new Nil();
            } else {
                q[tail++] = t.left;
            }
            if (t.right == null) {
                q[tail++] = new Nil();
            } else {
                q[tail++] = t.right;
            }
            head++;
        }
        return s.toString();
    }

    protected void release(Node<E> node) {
        if (node != null) {
            node.element = null;
            node.left = node.right = node.parent = null;
        }
    }

    protected static class Node<E> {

        public E element;
        public Node left;
        public Node parent;
        public Node right;

        public Node(E e, Node p, Node l, Node r) {
            this.element = e;
            this.parent = p;
            this.left = l;
            this.right = r;
        }

        public Node<E> copy() {
            return new Node<E>(element, parent, left, right);
        }

        @Override
        public String toString() {
            return element != null ? element.toString() : "null";
        }
    }

    protected static class DefaultNodeBuilder<E> implements NodeBuilder<E> {

        @Override
        public Node<E> build(E e, Node p, Node l, Node r) {
            if (e == null) {
                return null;
            }
            return new Node<>(e, p, l, r);
        }

        @Override
        public Node<E> build(E e, Node p) {
            return build(e, p, null, null);
        }
    }

    /**
     * Node builder
     *
     * @author Caigao.Tang
     * @date 2018/1/16
     */
    protected interface NodeBuilder<E> {

        /**
         * Build with child.
         *
         * @param e Node element val
         * @param l Node`s left child
         * @param p Node`s parent
         * @param r Node`s right child
         * @return the node build,return null when param invalid
         */
        Node<E> build(E e, Node p, Node l, Node r);

        /**
         * Build with no child.
         *
         * @param e Node element val
         * @param p Node`s parent
         * @return the node build,return null when param invalid
         */
        Node<E> build(E e, Node p);
    }
}
