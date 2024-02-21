package lab1;

import java.util.NoSuchElementException;
import java.util.Stack;

public class TqsStack<T> {
    private Stack<T> stack = new Stack<>();  
    private final int maxSize = 3;

    public TqsStack() {
    }

    public void push(T element) {
        if (stack.size() >= maxSize) {
            throw new IllegalStateException();
        }

        stack.add(element); 
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return stack.remove(stack.size() - 1); 
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return stack.get(stack.size() - 1); 
    }

    public int size() {
        return stack.size(); 
    }

    public boolean isEmpty() {
        return stack.isEmpty(); 
    }
}
