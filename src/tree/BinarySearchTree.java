package tree;

import model.TreeDataModel;

import java.util.Comparator;

/**
 * @author Caigao.Tang
 * @date 2018/1/8.
 * description:
 * An binary search tree.
 * <p>
 * Saved data should implement  interface Comparable<T>.java
 * or give Comparator<T>.java when initial this cls.
 */
public class BinarySearchTree<E> extends TreeDataModel<E> {

    Comparator<E> comparator = null;

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
        add(element);
        return element;
    }

    @SuppressWarnings("unchecked")
    Node<E> add(E element) {
        Node<E> t = root;
        if (t == null) {
            root = builder.build(element, null);
            size++;
            return root;
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
                    return t;
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
                    return t;
                } else {
                    t = t.left;
                }

            } while (t != null);
        }
        Node<E> node = builder.build(element, p);
        if (i > 0) {
            p.right = node;
        } else {
            p.left = node;
        }
        size++;
        return node;
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

    @SuppressWarnings("unchecked")
    Node<E> deleteNode(Node<E> n) {
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

        Node<E> replacement = n.left != null ? n.left : n.right;

        Node p = n.parent;
        if (replacement != null) {
            replacement.parent = p;
            if (p == null) {
                //left or right child must be null(only one branch).
                root = replacement;
            } else if (n.equals(p.left)) {
                p.left = replacement;
            } else {
                p.right = replacement;
            }
        } else if (p == null) {
            root = null;
        } else {
            //no child
            //n.parent must not null
            if (n.equals(p.left)) {
                p.left = null;
            } else {
                p.right = null;
            }
            n.parent = null;
        }
        release(n);
        return p;
    }

    @SuppressWarnings("unchecked")
    void rotateLeft(Node node) {
        if (node == null) {
            return;
        }
        Node p = node.parent;
        Node r = node.right;
        //1.link right-left to node right
        Node rL = r.left;
        node.right = rL;
        if (rL != null) {
            rL.parent = node;
        }
        //2.link node as left child of right.
        node.parent = r;
        r.left = node;
        //3.link to parent
        r.parent = p;
        if (p == null) {
            root = r;
            return;
        }
        if (node.equals(p.right)) {
            p.right = r;
        } else {
            p.left = r;
        }
    }

    @SuppressWarnings("unchecked")
    void rotateRight(Node node) {
        if (node == null) {
            return;
        }
        Node p = node.parent;
        Node l = node.left;
        //1.link left-right child to node.
        Node lR = l.right;
        node.left = lR;
        if (lR != null) {
            lR.parent = node;
        }
        //2.link node to left as right child.
        node.parent = l;
        l.right = node;
        //3.link to parent
        l.parent = p;
        if (p == null) {
            root = l;
            return;
        }
        if (node.equals(p.right)) {
            p.right = l;
        } else {
            p.left = l;
        }
    }

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    protected Node<E> findUseComparator(E e) {
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

    @SuppressWarnings("unchecked")
    protected Node<E> find(E e) {
        Node<E> target = null;
        Node<E> t = root;
        int i;
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
