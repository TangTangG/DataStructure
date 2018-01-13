package stack;

import model.StackDataModel;

/**
 * @author Caigao.Tang
 * @date 2018/1/9
 * description:LIFO
 * An array stack,which known as LIFO.
 *
 * This model does not support thread safety.If you need it,
 * use <Stack.java> which provided by JDK.
 * <Stack.java> inherit from Vector,which support thread safety.
 *
 * The default size of this stack is 16(also you can specify
 * the size when initialization),each expansion size is double.
 *
 * The adjustment of the array occurs every time the remove function
 * is called, so it is not recommended to use the delete frequently.
 */
public class Stack<E> extends StackDataModel<E> {

    private Object[] stack;
    private int tail = 0;

    public Stack() {
        this(16);
    }

    public Stack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        stack = new Object[capacity];
    }

    @Override
    protected E push(E e) {
        if (e == null) {
            return null;
        }
        stack[tail] = e;
        if (++tail == stack.length) {
            doubleCapacity();
        }
        return e;
    }

    private void doubleCapacity() {
        int old = stack.length;
        int newCapacity = old << 1;
        //overflow
        if (newCapacity < 0) {
            throw new IllegalStateException("stack is too big");
        }
        Object[] a = new Object[newCapacity];
        System.arraycopy(stack, 0, a, 0, old);
        stack = a;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected E peek() {
        return tail > 0 ? (E) stack[tail - 1] : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected E pop() {
        if (tail <= 0) {
            return null;
        }
        Object o = stack[--tail];
        stack[tail] = null;
        return (E) o;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < tail; i++) {
            if (stack[i].equals(element)) {
                return delete(i);
            }
        }
        return false;
    }

    private boolean delete(int i) {
        Object[] stack = this.stack;
        stack[i] = null;
        int size = this.tail;
        int move = size - i - 1;
        if (move > 0) {
            System.arraycopy(stack, i + 1, stack, i, move);
        }
        tail = size - 1;
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < tail; i++) {
            stack[i] = null;
        }
        tail = 0;
    }

    public int size() {
        return tail;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tail; i++) {
            builder.append(stack[i]).append(" ");
        }
        return builder.toString();
    }
}
