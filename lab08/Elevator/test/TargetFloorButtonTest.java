

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TargetFloorButtonTest {
    
    public TargetFloorButtonTest() {
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

    /**
     * Test of isLit method, of class TargetFloorButton.
     */
    @Test
    public void testIsLit() {
        TargetFloorButton button = new ElevatorSystem(10, null).elevator.buttons[5];
        assertEquals(false, button.getLit());
        button.selectFloor();
        assertEquals(true, button.getLit());
        button.setLit(false);
        assertEquals(false, button.getLit());
    }
}
