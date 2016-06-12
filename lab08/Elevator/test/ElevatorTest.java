

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ElevatorTest {
    
    public ElevatorTest() {
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
    public void testElevator() {
        ElevatorSystem sys = new ElevatorSystem(4, null);
        Elevator elev = sys.elevator;
        
        assertEquals(0, elev.getCurrentFloor());
        assertEquals(Door.DOOR_STATUS.CLOSED, elev.door.getStatus());
        elev.sys.callElevator(3, Direction.DIRECTION.DOWN);
        assertEquals(0, elev.getCurrentFloor());
        assertEquals(Door.DOOR_STATUS.CLOSED, elev.door.getStatus());
        elev.tick();
        assertEquals(1, elev.getCurrentFloor());
        assertEquals(Door.DOOR_STATUS.CLOSED, elev.door.getStatus());
        elev.tick();
        assertEquals(2, elev.getCurrentFloor());
        assertEquals(Door.DOOR_STATUS.CLOSED, elev.door.getStatus());
        elev.tick();
        assertEquals(3, elev.getCurrentFloor());
        assertEquals(Door.DOOR_STATUS.OPENED, elev.door.getStatus());
        elev.buttons[1].selectFloor();
        assertEquals(3, elev.getCurrentFloor());
        assertEquals(Door.DOOR_STATUS.CLOSED, elev.door.getStatus());
        elev.tick();
        assertEquals(2, elev.getCurrentFloor());
        assertEquals(Door.DOOR_STATUS.CLOSED, elev.door.getStatus());
        elev.tick();
        assertEquals(1, elev.getCurrentFloor());
        assertEquals(Door.DOOR_STATUS.OPENED, elev.door.getStatus());
    }
}
