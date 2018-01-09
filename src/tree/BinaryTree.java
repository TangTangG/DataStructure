package tree;

import model.TreeDataModel;

import java.util.ConcurrentModificationException;

/**
 * @author Caigao.Tang
 * @date 2017/12/12.
 * description:
 * Complete binary tree, not allowed null.
 * Algorithm time complexity is as follows:
 * insert:O(N)
 * remove:O(N)
 * update:O(N)
 * query :O(N)
 */
public class BinaryTree<E> extends TreeDataModel<E> {

    /**
     * An complete tree child size must between
     * 2^n - 1 and 2^(n + 1) - 1 (n represent deep of tree,started form zero).
     * Based on this condition,we can test tree deep.
     * So we can use 'deep' to traversal tree.
     */

    @Override
    public E insert(E element) {
        if (element == null) {
            return null;
        }
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return element;
        }
        Node<E> t = root;
        int head = 0;
        int tail = 1;
        int oldSize = size;
        @SuppressWarnings("unchecked")
        Node<E>[] q = new Node[oldSize];
        q[head] = root;
        while (head < tail) {
            t = q[head];
            if (oldSize != size) {
                throw new ConcurrentModificationException("do note allow modify tree when insert.");
            }
            if (t.left == null || t.right == null) {
                break;
            }
            if (element.equals(t.element)) {
                t.element = element;
                return element;
            }
            q[tail++] = t.left;
            q[tail++] = t.right;
            head++;
        }
        Node<E> p = t;
        Node<E> n = new Node<>(element, p);
        if (p.left == null) {
            p.left = n;
        } else {
            p.right = n;
        }
        size++;
        return element;
    }

    /**
     * deep start from zero.
     */
    private int deep() {
        int size = this.size;
        int level = 0;
        while (size > 0) {
            if (((1 << level) - 1) < size & ((1 << (level + 1)) - 1) >= size) {
                break;
            }
            level++;
        }
        return level;
    }

    @Override
    public boolean remove(E element) {
        Node<E> node = preOrder(element);
        if (node != null) {
            if (node.equals(root) && size == 1) {
                root = null;
                size--;
                return true;
            }
            Node<E> last = getLast();
            Node<E> c = last.copy();
            replace(last, null);
            replace(node, c);
            release(node);
            size--;
            return true;
        }
        return false;
    }

    public E update(E element) {
        Node<E> node = preOrder(element);
        if (node != null) {
            node.element = element;
        }
        return element;
    }

    @Override
    protected boolean contain(E e) {
        return preOrder(e) != null;
    }

    private Node<E> getFirst() {
        return root;
    }

    private Node<E> getLast() {
        Node<E> t = root;
        if (t == null) {
            return null;
        }
        int deep = deep();
        int lastLevelSize = size - (1 << deep) + 1;
        Node<E> last;
        while (t.left != null || t.right != null) {
            double half = lastLevelSize / ((1 << deep) * 1.0);
            if (half <= 0.5) {
                t = t.left;
            } else {
                t = t.right;
                lastLevelSize -= (1 << (deep - 1));
            }
            deep--;
        }
        last = t;
        return last;
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
}
