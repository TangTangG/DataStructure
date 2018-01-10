package linkedList;

import model.LinkDataModel;

/**
 * @author Caigao.Tang
 * @date  2017/9/22.
 * description:
 */
public class OneWay<E> extends LinkDataModel<E> {

    private OneWayNode last;
    private OneWayNode first;

    @Override
    public synchronized E insert(E e) {
        OneWayNode<E> newNode = new OneWayNode<>(e, null);
        if (first == null){
            first = newNode;
        }
        if (last != null) {
            last.next = newNode;
        }
        last = newNode;
        return e;
    }

    @Override
    public synchronized boolean remove(E e) {
        OneWayNode node = first;
        if (node.element.equals(e)) {
            first = first.next;
            return true;
        }
        while (node.next != null) {
            if (node.next.element.equals(e)) {
                if (node.next.equals(last)) {
                    node.next = null;
                    last = node;
                } else {
                    OneWayNode newNext = node.next.next;
                    node.next.next = null;
                    node.next = newNext;
                }
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean contain(E e) {
        return node(e) == null;
    }

    public E query(int index) {
        int cursor = 0;
        OneWayNode node = first;
        while (node != null) {
            if (index == cursor) {
                return (E) node.element;
            }
            node = node.next;
            cursor++;
        }
        return null;
    }

    OneWayNode node(E e) {
        if (e == null) {
            return null;
        }
        if (first == null) {
            return null;
        }
        OneWayNode nextNode = first.next;
        while (nextNode != null) {
            if (nextNode.element.equals(e)) {
                return nextNode;
            }
            nextNode = nextNode.next;
        }
        return null;
    }

    public E update(int index, E newVal) {
        int cursor = 0;
        OneWayNode node = first;
        while (node != null) {
            if (index == cursor) {
                node.element = newVal;
                return newVal;
            }
            node = node.next;
            cursor++;
        }
        return null;
    }

    public E update(E oldVal, E newVal) {
        OneWayNode node = first;
        while (node != null) {
            if (node.element.equals(oldVal)) {
                node.element = newVal;
                return newVal;
            }
            node = node.next;
        }
        return null;
    }

    public synchronized OneWay reverse(){
        OneWayNode oldLast = last;
        last = first;
        reverseNode(first);
        first = oldLast;
        return this;
    }

    private void reverseNode(OneWayNode head){
        if (head == null || head.next == null){
            return;
        }
        reverseNode(head.next);
        head.next.next = head;
        head.next = null;
    }

    public E getFirst(){
        return first != null ? (E) first.element : null;
    }

    public E getLast(){
        return last != null ? (E) last.element : null;
    }

    @Override
    public void foreach(Consumer<? super E> consumer) {
        if (consumer == null){
            return;
        }
        int index = 0;
        OneWayNode node = first;
        while (node != null) {
            consumer.node(index, (E) node.element);
            node = node.next;
            index++;
        }
    }

    static class OneWayNode<E> extends Node<E> {

        OneWayNode next;

        OneWayNode(E element, OneWayNode next) {
            super(element);
            this.next = next;
        }
    }
}
