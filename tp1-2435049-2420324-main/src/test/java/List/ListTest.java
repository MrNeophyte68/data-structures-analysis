package List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ListTest {

    protected List list = makeInstance();

    abstract List makeInstance();

    @BeforeEach
    public void setup() {
        this.list = makeInstance();
    }

    @Test
    public void testCreation() {
        assertEquals(0, this.list.size());
    }

    @Test
    public void testInsertBeginning() {
        this.list.insert(3, 0);
        this.list.insert(2, 0);
        this.list.insert(1, 0);

        assertEquals(3, this.list.size());

        assertEquals(1, this.list.get(0));
        assertEquals(2, this.list.get(1));
        assertEquals(3, this.list.get(2));
    }

    @Test
    public void testInsertMiddle() {
        this.list.insert(1, this.list.size());
        this.list.insert(3, this.list.size());

        this.list.insert(2, 1);

        assertEquals(3, this.list.size());

        assertEquals(1, this.list.get(0));
        assertEquals(2, this.list.get(1));
        assertEquals(3, this.list.get(2));
    }

    @Test
    public void testInsertEnd() {
        this.list.insert(3, this.list.size());
        this.list.insert(2, this.list.size());
        this.list.insert(1, this.list.size());

        assertEquals(3, this.list.size());

        assertEquals(3, this.list.get(0));
        assertEquals(2, this.list.get(1));
        assertEquals(1, this.list.get(2));
    }

    @Test
    public void testRemoveBeginning() {
        this.list.insert(1, this.list.size());
        this.list.insert(2, this.list.size());
        this.list.insert(3, this.list.size());

        assertEquals(1, this.list.remove(0));
        assertEquals(2, this.list.size());

        assertEquals(2, this.list.get(0));
        assertEquals(3, this.list.get(1));

    }

    @Test
    public void testRemoveMiddle() {
        this.list.insert(1, this.list.size());
        this.list.insert(2, this.list.size());
        this.list.insert(3, this.list.size());

        assertEquals(2, this.list.remove(1));

        assertEquals(1, this.list.get(0));
        assertEquals(3, this.list.get(1));
    }

    @Test
    public void testRemoveEnd() {
        this.list.insert(1, this.list.size());
        this.list.insert(2, this.list.size());
        this.list.insert(3, this.list.size());

        assertEquals(3, this.list.remove(2));

        assertEquals(1, this.list.get(0));
        assertEquals(2, this.list.get(1));
    }

    @Test
    public void testClear() {
        this.list.insert(1, this.list.size());
        this.list.insert(1, this.list.size());
        this.list.insert(1, this.list.size());
        assertEquals(3, this.list.size());
        this.list.clear();
        assertEquals(0, this.list.size());
    }

    @Test
    public void testSet() {
        this.list.insert(1, this.list.size());
        this.list.insert(1, this.list.size());
        this.list.insert(1, this.list.size());

        assertEquals(3, this.list.size());

        for (int i = 0; i < this.list.size(); i++) {
            this.list.set(2, i);
            assertEquals(2, this.list.get(i));
            this.list.set(1, i);
        }
    }

    @Test
    public void testExceptions() {
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> this.list.get(1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> this.list.get(-1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> this.list.set(0, 1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> this.list.set(0, -1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> this.list.insert(0, 1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> this.list.insert(0, -1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> this.list.remove(1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> this.list.remove(-1))
        );
    }
}



