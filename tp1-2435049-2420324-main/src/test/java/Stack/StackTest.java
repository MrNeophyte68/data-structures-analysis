package Stack;

import List.Vector;
import Stack.Stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    private Stack stack;

    @BeforeEach
    public void setup() {
        Vector v = new Vector();
        v.insert(1, 0);
        v.insert(2, 1);
        v.insert(3, 2);

        this.stack = new Stack(v);
    }

    @Test
    public void testCreation() {
        assertEquals(3, this.stack.size());
    }

    @Test
    public void testPeek() {
        assertEquals(3, this.stack.peek());
    }

    @Test
    public void testPop() {
        assertEquals(3, this.stack.pop());
        assertEquals(2, this.stack.pop());
        assertEquals(1, this.stack.pop());
        assertEquals(0, this.stack.size());
    }

    @Test
    public void testPush() {
        this.stack.push(4);
        this.stack.push(5);
        assertEquals(5, this.stack.size());
        assertEquals(5, this.stack.pop());
        assertEquals(4, this.stack.pop());
    }
}
