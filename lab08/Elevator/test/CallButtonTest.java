

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CallButtonTest {
    
    public CallButtonTest() {
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
    public void testCallButton() {
        CallButton button = new CallButton(null, null);
        assertEquals(false, button.isDownLit);
        assertEquals(false, button.isUpLit);
        button.setLit(Direction.DIRECTION.UP, true);
        assertEquals(false, button.isDownLit);
        assertEquals(true, button.isUpLit);
        button.setLit(Direction.DIRECTION.DOWN, true);
        assertEquals(true, button.isDownLit);
        assertEquals(true, button.isUpLit);
        button.setLit(Direction.DIRECTION.UP, false);
        assertEquals(true, button.isDownLit);
        assertEquals(false, button.isUpLit);
        button.setLit(Direction.DIRECTION.DOWN, false);
        assertEquals(false, button.isDownLit);
        assertEquals(false, button.isUpLit);
    }
}
