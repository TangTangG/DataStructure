package tree;

import model.TreeDataModel;

import java.util.Comparator;

/**
 * @author Caigao.Tang
 * @date 2018/1/8.
 * description:
 */
public class BinarySearchTree<E> extends TreeDataModel<E> {

    private Comparator<E> comparator = null;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> c) {
        this.comparator = c;
    }

    @Override
    public E insert(E element) {
        if (element == null) {
            return null;
        }
        Node<E> t = root;
        if (t == null) {
            root = new Node<>(element, null);
            size++;
            return element;
        }
        Node<E> p;
        int i;
        if (comparator != null) {
            do {
                p = t;
                i = comparator.compare(element, t.element);
                if (i > 0) {
                    t = t.right;
                } else if (i == 0) {
                    t.element = element;
                    return element;
                } else {
                    t = t.left;
                }

            } while (t != null);
        } else {
            @SuppressWarnings("unchecked")
            Comparable<E> c = (Comparable<E>) element;
            do {
                p = t;
                i = c.compareTo(t.element);
                if (i > 0) {
                    t = t.right;
                } else if (i == 0) {
                    t.element = element;
                    return element;
                } else {
                    t = t.left;
                }

            } while (t != null);
        }
        Node<E> node = new Node<>(element, p);
        if (i > 0) {
            p.right = node;
        } else {
            p.left = node;
        }
        size++;
        return element;
    }

    @Override
    public boolean remove(E element) {
        if (element == null || root == null) {
            return false;
        }
        Node<E> remove = comparator != null ? findUseComparator(element) : find(element);
        if (remove == null) {
            return false;
        }
        deleteNode(remove);
        return true;
    }

    private void deleteNode(Node<E> n) {
        //In fact, there are four situations here:
        //1.has two child
        //2.only has left child
        //3.only has right child
        //4.no child
        //This code is condensed after analyzing all four cases.
        size--;
        //two child.Do it first,so we can update
        //delete node to successor(instead of recursively delete it).
        if (n.left != null && n.right != null) {
            //use left-max or right-min as successor.
            //there we choose right-min.
            //this may cause left tree much deeper than right
            //(because always use right as successor).
            Node<E> s = n.right;
            while (s.left != null) {
                s = s.left;
            }
            n.element = s.element;
            n = s;
        }

        @SuppressWarnings("unchecked")
        Node<E> replacement = n.left != null ? n.left : n.right;

        if (replacement != null) {
            replacement.parent = n.parent;
            if (n.parent == null) {
                //left or right child must be null(only one branch).
                root = replacement;
            } else if (replacement.equals(n.parent.left)) {
                n.parent.left = replacement;
            } else {
                n.parent.right = replacement;
            }
        } else if (n.parent == null) {
            root = null;
        } else {
            //no child
            //n.parent must not null
            if (n.equals(n.parent.left)) {
                n.parent.left = null;
            } else {
                n.parent.right = null;
            }
            n.parent = null;
        }
        release(n);
    }

    public E max() {
        if (root == null) {
            return null;
        }
        Node<E> t = root;
        while (t.right != null) {
            t = t.right;
        }
        return t.element;
    }

    public E min() {
        if (root == null) {
            return null;
        }
        Node<E> t = root;
        while (t.left != null) {
            t = t.left;
        }
        return t.element;
    }

    @Override
    protected boolean contain(E e) {
        return (comparator != null ? findUseComparator(e) : find(e)) != null;
    }

    private Node<E> findUseComparator(E e) {
        if (comparator == null) {
            throw new NullPointerException("findUseComparator at comparator is null.");
        }
        Node<E> target = null;
        Node<E> t = root;
        int i;
        do {
            i = comparator.compare(e, t.element);
            if (i > 0) {
                t = t.right;
            } else if (i == 0) {
                target = t;
                break;
            } else {
                t = t.left;
            }

        } while (t != null);
        return target;
    }

    private Node<E> find(E e) {
        Node<E> target = null;
        Node<E> t = root;
        int i;
        @SuppressWarnings("unchecked")
        Comparable<E> c = (Comparable<E>) e;
        do {
            i = c.compareTo(t.element);
            if (i > 0) {
                t = t.right;
            } else if (i == 0) {
                target = t;
                break;
            } else {
                t = t.left;
            }

        } while (t != null);
        return target;
    }
}
