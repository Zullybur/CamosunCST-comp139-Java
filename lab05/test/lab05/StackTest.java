package lab05;

import java.nio.BufferOverflowException;
import java.util.EmptyStackException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class StackTest {

    public StackTest() {
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
        Stack s = new Stack(4);
        assertEquals(true, s.isEmpty());
        s.push(1);
        assertEquals(false, s.isEmpty());
        s.pop();
        assertEquals(true, s.isEmpty());
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testPush() {
        System.out.println("push and top");
        Stack s = new Stack(2);
        s.push(5);
        assertEquals("push to empty stack", "5", s.toString());
        assertEquals("first element is on top", 5, s.top());
        s.push(6);
        assertEquals("push to non-empty stack", "6,5", s.toString());
        assertEquals("second element is on top", 6, s.top());
        System.out.println("push to full stack");
        exception.expect(BufferOverflowException.class);
        s.push(7);
    }

    @Test
    public void testPop() {
        System.out.println("pop and top");
        Stack s = new Stack(2);
        s.push("A");
        s.push("B");
        assertEquals("top element before pop", "B", s.top());
        s.pop();
        assertEquals("top element after pop", "A", s.top());
        s.pop();
        assertEquals("poping last element", true, s.isEmpty());
        exception.expect(EmptyStackException.class);
        s.pop();
    }

    @Test
    public void testTop() {
        System.out.println("top");
        Stack s = new Stack(4);
        exception.expect(EmptyStackException.class);
        System.out.println(s.top());
    }
}