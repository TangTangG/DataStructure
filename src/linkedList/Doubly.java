package linkedList;

import model.LinkDataModel;

import java.util.ConcurrentModificationException;

/**
 * @author Caigao.Tang
 * @date 2017/9/30.
 * description：
 * thread safe doubly linked list.
 * do not support multi operation at same time.
 */
public class Doubly<E> extends LinkDataModel<E> {

    private int size = 0;
    private DoublyNode first;
    private DoublyNode last;
    private int modify = 0;

    @Override
    public synchronized E insert(E element) {
        int respectModify = modify;
        DoublyNode oldLast = last;
        DoublyNode<E> newNode = new DoublyNode<>(element, oldLast, null);
        last = newNode;
        if (oldLast == null) {
            first = newNode;
        } else {
            oldLast.next = newNode;
        }
        modifyCheck(respectModify);
        size++;
        modify++;
        return element;
    }

    public synchronized E insertBefore(E element) {
        int respectModify = modify;
        DoublyNode oldFirst = first;
        DoublyNode<E> newNode = new DoublyNode<>(element, null, oldFirst);
        first = newNode;
        if (oldFirst == null) {
            last = newNode;
        } else {
            oldFirst.prev = newNode;
        }
        modifyCheck(respectModify);
        size++;
        ++modify;
        return element;
    }

    public synchronized E insertAt(int index, E element, boolean isBefore) {
        if (index < 0) {
            return null;
        }
        if (first == null || last == null) {
            return insert(element);
        }
        if (index == 0) {
            return insertBefore(element);
        } else if (index == size - 1) {
            return insert(element);
        }
        DoublyNode axis = nodeAt(index);
        if (axis == null) {
            return null;
        }
        int respectModify = modify;
        if (isBefore) {
            DoublyNode prev = axis.prev;
            DoublyNode<E> newNode = new DoublyNode<>(element, prev, axis);
            prev.next = newNode;
            axis.prev = newNode;
        } else {
            DoublyNode next = axis.next;
            DoublyNode<E> newNode = new DoublyNode<>(element, axis, next);
            next.prev = newNode;
            axis.next = newNode;
        }
        modifyCheck(respectModify);
        size++;
        ++modify;
        return element;
    }

    private DoublyNode nodeAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index < (size >> 1)) {
            DoublyNode<E> node = first;
            for (int i = 0; i <= index; i++) {
                node = node.next;
            }
            return node;
        } else {
            DoublyNode<E> node = last;
            for (int i = size - 1; i > index; i++) {
                node = node.prev;
            }
            return node;
        }
    }

    private DoublyNode node(E e) {
        DoublyNode<E> node = first;
        if (e == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return node;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (e.equals(node.element)) {
                    return node;
                }
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public synchronized boolean remove(E element) {
        int respectModify = modify;
        DoublyNode<E> node = node(element);
        if (node == null) {
            return false;
        }
        DoublyNode prev = node.prev;
        DoublyNode next = node.next;
        if (prev != null) {
            prev.next = next;
        } else {
            first = next;
        }

        if (next != null) {
            next.prev = prev;
        } else {
            last = prev;
        }
        modifyCheck(respectModify);
        size--;
        ++modify;
        return true;
    }

    @Override
    public E childAt(int index) {
        DoublyNode nodeAt = nodeAt(index);
        return nodeAt == null ? null : (E) nodeAt.element;
    }

    @Override
    public int indexOf(E element) {
        DoublyNode<E> node = first;
        if (node == null) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(node.element)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public boolean contain(E element) {
        return node(element) != null;
    }

    @Override
    public synchronized E set(int index, E element) {
        DoublyNode node = nodeAt(index);
        if (node != null) {
            node.element = element;
            return element;
        }
        return null;
    }

    public int size(){
        return size;
    }

    private synchronized void modifyCheck(int respect) {
        if (respect != modify) {
            throw new ConcurrentModificationException("do not allow multi data operation at same time");
        }
    }

    static class DoublyNode<E> extends Node<E> {

        DoublyNode prev;
        DoublyNode next;

        public DoublyNode(E element, DoublyNode prev, DoublyNode next) {
            super(element);
            this.prev = prev;
            this.next = next;
        }
    }
}
