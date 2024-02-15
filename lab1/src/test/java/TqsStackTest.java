import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

class TqsStackTest {

    @Test
    void stackIsEmptyOnConstruction() {
        TqsStack<Integer> stack = new TqsStack<>();
        assertTrue(stack.isEmpty());
    }

    @Test
    void stackHasSizeZeroOnConstruction() {
        TqsStack<Integer> stack = new TqsStack<>();
        assertEquals(0, stack.size());
    }

    // Additional tests here for each contract condition...
}
