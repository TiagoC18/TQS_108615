import java.util.NoSuchElementException;

public class TqsStack<T> {
    public void push(T element) {
        // Implementation will be added later
    }

    public T pop() {
        throw new NoSuchElementException("Stack is empty");
    }

    public T peek() {
        throw new NoSuchElementException("Stack is empty");
    }

    public int size() {
        return 0; // Temporary return value
    }

    public boolean isEmpty() {
        return true; // Temporary return value
    }
}
