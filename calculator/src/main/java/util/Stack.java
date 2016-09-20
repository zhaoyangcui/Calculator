package util;

public interface Stack<T> {
    T pop();

    void push(T obj);

    int getLength();

    void expansionArray();

    Boolean ifStackNone();

    T get();
}
 