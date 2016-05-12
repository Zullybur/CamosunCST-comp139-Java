/*
 */
package lab05;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Michael
 */
public class QueueTest {

    public QueueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Queue q = new Queue();
        assertEquals("test empty queue", true, q.isEmpty());
        q.enqueue("A");
        assertEquals("test non-empty queue", false, q.isEmpty());
        q.dequeue();
        assertEquals("test emptied queue", true, q.isEmpty());
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testEnqueue() {
        System.out.println("enqueue and first");
        Queue q = new Queue();
        q.enqueue("A");
        assertEquals("test enqueue to empty list", "A", q.first());
        q.enqueue("B");
        assertEquals("test enqueue multiple elements", "A", q.first());
        assertEquals("test queue order", "A,B", q.toString());
    }

    @Test
    public void testDequeue() {
        System.out.println("dequeue and first");
        Queue q = new Queue();
        q.enqueue("A");
        q.enqueue("B");
        q.dequeue();
        assertEquals("test dequeue order", "B", q.first());
        q.dequeue();
        exception.expect(BufferUnderflowException.class);
        q.dequeue();
    }

    @Test
    public void testFirst() {
        System.out.println("first");
        Queue q = new Queue();
        exception.expect(BufferUnderflowException.class);
        System.out.println(q.first());
    }
}
