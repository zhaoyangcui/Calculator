package util;

public class SStack<T> implements Stack<T> {

    private Object[] elements;
    private int top;

    public SStack(int size) {
        elements = new Object[100];
        top = -1;
    }

    public SStack() {
        this(64);
    }

    public void push(T obj) {

        if (obj == null) {
            return;
        }
        if (top == this.elements.length) {
            expansionArray();
        }
        top++;
        elements[top] = obj;
    }

    public T pop() {
        if (top == -1) {
            return null;
        } else {
            return (T) this.elements[top--];
        }
    }

    public int getLength() {
        return top + 1;
    }

    public void expansionArray() {
        Object[] tempArray = elements;
        elements = new Object[tempArray.length * 2];
        for (int i = 0; i < tempArray.length; i++) {
            elements[i] = tempArray[i];
        }
    }

    public Boolean ifStackNone() {
        return top == -1 ? true : false;
    }

    @Override
    public T get() {

        return top == -1 ? null : (T) elements[top];
    }


}
