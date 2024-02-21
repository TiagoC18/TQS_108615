import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lab1.TqsStack;
import java.util.NoSuchElementException;

class TqsStackTest {

    @DisplayName("a)")
    @Test
    void stackIsEmptyOnConstruction() {
        TqsStack<Integer> stack = new TqsStack<>();
        assertTrue(stack.isEmpty());
    }

    @DisplayName("b)")
    @Test
    void stackHasSizeZeroOnConstruction() {
        TqsStack<Integer> stack = new TqsStack<>();
        assertEquals(0, stack.size());
    }

    @DisplayName("c)")
    @Test
    void afterPushStackIsNotEmptyAndSizeIsN() {
        TqsStack<Integer> stack = new TqsStack<>();
        for (int i = 1; i <= 3; i++) {
            stack.push(i);
            assertFalse(stack.isEmpty());
            assertEquals(i, stack.size());
        }
    }

    @DisplayName("d)")
    @Test
    void pushThenPopReturnsSameValue() {
        TqsStack<String> stack = new TqsStack<>();
        String item = "test";
        stack.push(item);
        assertEquals(item, stack.pop());
    }

    @DisplayName("e)")
    @Test
    void pushThenPeekReturnsSameValueAndSizeUnchanged() {
        TqsStack<String> stack = new TqsStack<>();
        String item = "test";
        stack.push(item);
        assertEquals(item, stack.peek());
        assertEquals(1, stack.size());
    }

    @DisplayName("f)")
    @Test
    void afterNPopsStackIsEmptyAndSizeIsZero() {
        TqsStack<String> stack = new TqsStack<>();
        int n = 3;
        for (int i = 0; i < n; i++) {
            stack.push("element" + i);
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @DisplayName("g)")
    @Test
    void popFromEmptyStackThrowsException() {
        TqsStack<Integer> stack = new TqsStack<>();
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @DisplayName("h)")
    @Test
    void peekIntoEmptyStackThrowsException() {
        TqsStack<Integer> stack = new TqsStack<>();
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @DisplayName("i)")
    @Test
    void pushingOntoFullStackThrowsException() {
        TqsStack<Integer> stack = new TqsStack<>();
        assertThrows(IllegalStateException.class, () -> {
            for (int i = 0; i <= 5 ; i++  ){
                stack.push(i);
            }
        });
    }
}
