package tree;

import java.util.Comparator;

/**
 * @author Caigao.Tang
 * @date 2018/1/16
 * description:
 * <p>
 * 1.Each node is either red or black.
 * 2.The root is black
 * 3.all leaves(NIL) are Black.
 * 4.if node is red,then both its children are black.
 * 5.Every path from a given node to any of its descendant NIL nodes contains
 * the same number of black nodes.
 * Some definitions: the number of black nodes from the root to a node
 * is the node's black depth; the uniform number of black nodes in all paths
 * from root to the leaves is called the black-height of the red–black tree
 */
public class RedBlackTree<E> extends BinarySearchTree<E> {

    private static final int BLACK = 1;
    private static final int RED = 2;

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(Comparator<E> c) {
        super(c);
        builder = new RedBlackBuilder<>();
    }

    @Override
    public E insert(E element) {
        if (element == null) {
            return null;
        }
        ColorNode<E> add = (ColorNode<E>) add(element);
        balanceAfterInsert(add);
        return element;
    }

    /**
     * balance means the tree`s black height of left and right is same.
     * the color of two red child must be black,this ensure the black height is increase.
     * Insert: with a red node,and then judge the black height.
     * Delete: assuming the location of the removal is black(so the black height do not change)
     * 1.If the node is red,just change it color to black.(the successor is itself)
     * 2.If the node is black,that means it has double black,so we need
     * find a successor for one of the black(left ensure it own color)
     * or let other side dismiss one black(and then parent successor the color).
     */

    @SuppressWarnings("unchecked")
    private void balanceAfterInsert(ColorNode<E> n) {
        //Every time insert red node,in some situation,
        //we may do note need to re balance tree;
        n.color = RED;
        //begin check balance
        while (n != null && !n.equals(root) && colorOf(n.parent) == RED) {
            if (parentOf(n).equals(leftOf(parentOf(parentOf(n))))) {
                ColorNode uncle = rightOf(parentOf(parentOf(n)));
                if (colorOf(uncle) == BLACK) {
                    if (n.equals(rightOf(parentOf(n)))) {
                        //case left-right
                        //cast to left-left
                        n = parentOf(n);
                        rotateLeft(n);
                    }
                    //case left-left
                    setColor(parentOf(n), BLACK);
                    setColor(parentOf(parentOf(n)), RED);
                    rotateRight(parentOf(parentOf(n)));
                } else {
                    setColor(parentOf(n), BLACK);
                    setColor(uncle, BLACK);
                    n = parentOf(parentOf(n));
                    setColor(n, RED);
                }
            } else {
                ColorNode uncle = leftOf(parentOf(parentOf(n)));
                if (colorOf(uncle) == BLACK) {
                    if (n.equals(leftOf(parentOf(n)))) {
                        //case right-left
                        //cast to right-right
                        n = parentOf(n);
                        rotateRight(n);
                    }
                    //case right-right
                    setColor(parentOf(n), BLACK);
                    setColor(parentOf(parentOf(n)), RED);
                    rotateLeft(parentOf(parentOf(n)));
                } else {
                    setColor(parentOf(n), BLACK);
                    setColor(uncle, BLACK);
                    n = parentOf(parentOf(n));
                    setColor(n, RED);
                }
            }
        }
        //feature 1
        ((ColorNode) root).color = BLACK;
    }

    @SuppressWarnings("unchecked")
    @Override
    Node<E> deleteNode(Node<E> n) {
        //In fact, there are four situations here:
        //1.has two child --> find an successor.
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
        //Come here remove node n only one node at most.
        Node<E> replacement = n.left != null ? n.left : n.right;

        Node p = n.parent;
        if (replacement != null) {
            replacement.parent = p;
            if (p == null) {
                root = replacement;
            } else if (n.equals(p.left)) {
                p.left = replacement;
            } else {
                p.right = replacement;
            }
            //release n
            n.parent = n.left = n.right = null;
            if (((ColorNode) n).color == BLACK) {
                balanceAfterDelete(replacement);
            }
        } else if (p == null) {
            root = null;
        } else {
            if (((ColorNode) p).color == BLACK) {
                balanceAfterDelete(n);
            }
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

    private void balanceAfterDelete(Node n) {
        //No matter node n color,
        //we consider it as a black node
        //we should ensure the height of black not change.
        while (n != null && !n.equals(root) && colorOf(n) == BLACK) {
            if (n.equals(leftOf(parentOf(n)))) {
                Node sibling = rightOf(parentOf(n));
                if (colorOf(sibling) == RED) {
                    //cast to black condition
                    //it two child must black,change sibling and parent color
                    //and rotate parent left,then sibling child will be new sibling
                    setColor(sibling, BLACK);
                    setColor(parentOf(n), RED);
                    rotateLeft(parentOf(n));
                    sibling = rightOf(parentOf(n));
                }
                //sibling two children is black
                if (colorOf(rightOf(sibling)) == BLACK
                        && colorOf(leftOf(sibling)) == BLACK) {
                    //make other side dismiss one black
                    setColor(sibling, RED);
                    //if parent is red,while finish;
                    //if black,continue find the color pre-successor.
                    n = parentOf(n);
                } else {
                    if (colorOf(rightOf(sibling)) == BLACK) {
                        //left is red
                        //if we change sibling color to red,then two red is together.
                        //so consider parent already red,we need to rotate sibling left
                        //and make left child black,sibling red.
                        //so we cast this condition to right child is red
                        //rotate left can make child replace the position of it parent.
                        setColor(sibling, RED);
                        setColor(leftOf(sibling), BLACK);
                        rotateRight(sibling);
                        sibling = rightOf(parentOf(n));
                    }
                    //parent color get sibling,and rotate itself left,make sibling as new root
                    //this can make black height equals between left and right.
                    setColor(sibling, colorOf(parentOf(n)));
                    //parent successor the fake black,right of sibling successor the sibling-black
                    //after rotate,left will ensure it own black height,and the height between is same.
                    setColor(parentOf(n), BLACK);
                    setColor(rightOf(sibling), BLACK);
                    rotateLeft(parentOf(n));
                    n = root;
                }

            } else {
                Node sibling = leftOf(parentOf(n));
                if (colorOf(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(parentOf(n), RED);
                    rotateRight(parentOf(n));
                    sibling = leftOf(parentOf(n));
                }
                if (colorOf(rightOf(sibling)) == BLACK
                        && colorOf(leftOf(sibling)) == BLACK) {
                    setColor(sibling, RED);
                    n = parentOf(n);
                } else {
                    if (colorOf(leftOf(sibling)) == BLACK) {
                        setColor(sibling, RED);
                        setColor(rightOf(sibling), BLACK);
                        rotateLeft(sibling);
                        sibling = leftOf(parentOf(n));
                    }
                    setColor(sibling, colorOf(parentOf(n)));
                    setColor(parentOf(n), BLACK);
                    setColor(leftOf(sibling), BLACK);
                    rotateRight(parentOf(n));
                    n = root;
                }
            }
        }
        setColor(n, BLACK);
    }

    /**
     * These methods refer to Jdk1.8 TreeMap writing,
     * because through such a function expression points to multiple nodes,
     * we can quickly understand the relationship.
     * And through colorOf method,we do not need to judge the leaf node.
     */

    private int colorOf(Node node) {
        return node == null ? BLACK : ((ColorNode) node).color;
    }

    private void setColor(Node node, int color) {
        if (node != null) {
            ((ColorNode) node).color = color;
        }
    }

    private ColorNode parentOf(Node node) {
        return node == null ? null : (ColorNode) node.parent;
    }

    private ColorNode leftOf(Node node) {
        return node == null ? null : (ColorNode) node.left;
    }

    private ColorNode rightOf(Node node) {
        return node == null ? null : (ColorNode) node.right;
    }

    private static class ColorNode<E> extends Node<E> {

        int color = BLACK;

        ColorNode(E e, Node p, Node l, Node r) {
            super(e, p, l, r);
        }

        @Override
        public String toString() {
            return "{c:" +
                    (color == BLACK ? "B" : "R") +
                    "  [" +
                    super.toString() +
                    "]}";
        }
    }

    private static class RedBlackBuilder<E> extends DefaultNodeBuilder<E> {
        @Override
        public Node<E> build(E e, Node p, Node l, Node r) {
            return new ColorNode<>(e, p, l, r);
        }
    }

}
