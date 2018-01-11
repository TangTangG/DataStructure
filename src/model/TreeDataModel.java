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
        @SuppressWarnings("unchecked")
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

            if (t.left != null) {
                q[tail++] = t.left;
            }
            if (t.right != null) {
                q[tail++] = t.right;
            }
            head++;
        }
        return s.toString();
    }

    protected void release(Node<E> node) {
        if (node != null) {
            node.element = null;
            node.left = null;
            node.right = null;
            node.parent = null;
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

        public Node(E e, Node p) {
            this.element = e;
            this.parent = p;
            this.left = null;
            this.right = null;
        }

        public Node<E> copy() {
            return new Node<E>(element, parent, left, right);
        }

        @Override
        public String toString() {
            return element != null ? element.toString(): "null";
        }
    }
}
