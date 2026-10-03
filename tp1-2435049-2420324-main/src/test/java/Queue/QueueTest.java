package Queue;

import List.Vector;
import Queue.Queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    private Queue queue;

    @BeforeEach
    public void setup() {
        Vector v = new Vector();
        v.insert(1, 0);
        v.insert(2, 1);
        v.insert(3, 2);

        this.queue = new Queue(v);
    }

    @Test
    public void testCreation() {
        assertEquals(3, this.queue.size());
    }

    @Test
    public void testPeek() {
        assertEquals(1, this.queue.peek());
    }

    @Test
    public void testDequeue() {
        assertEquals(1, this.queue.dequeue());
        assertEquals(2, this.queue.dequeue());
        assertEquals(3, this.queue.dequeue());
        assertEquals(0, this.queue.size());
    }

    @Test
    public void testEnqueue() {
        this.queue.enqueue(4);
        this.queue.enqueue(5);

        assertEquals(5, this.queue.size());

        assertEquals(1, this.queue.dequeue());
        assertEquals(2, this.queue.dequeue());
        assertEquals(3, this.queue.dequeue());
        assertEquals(4, this.queue.dequeue());
        assertEquals(5, this.queue.dequeue());
    }
}
