package List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest extends ListTest {

    @Override
    List makeInstance() {
        return new LinkedList();
    }

    @Test
    public void testNonRecursiveImplementation() {
        for (int i = 0; i < 10000; ++i) {
            this.list.insert(i, 0);
        }

        assertEquals(10000, list.size());

        assertAll(
                () -> assertDoesNotThrow(() -> this.list.get(this.list.size() - 1)),
                () -> assertDoesNotThrow(() -> this.list.set(-1, this.list.size() - 1)),
                () -> assertDoesNotThrow(() -> this.list.insert(-1, this.list.size())),
                () -> assertDoesNotThrow(() -> this.list.remove(this.list.size() - 1))
        );
    }
}
