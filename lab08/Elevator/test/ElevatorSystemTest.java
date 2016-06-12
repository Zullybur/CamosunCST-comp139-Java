

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ElevatorSystemTest {
    
    public ElevatorSystemTest() {
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
    public void testElevatorSystem() {
        ElevatorSystem sys = new ElevatorSystem(4, null);
        
        assertEquals(-1, sys.getNextFloor());
        sys.addFloor(3);
        assertEquals(3, sys.getNextFloor());
        sys.removeFloor(3);
        assertEquals(-1, sys.getNextFloor());
        sys.callElevator(2, Direction.DIRECTION.UP);
        assertEquals(2, sys.getNextFloor());
    }
}
