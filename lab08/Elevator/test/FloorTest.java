

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FloorTest {
    
    public FloorTest() {
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
    public void testFloor() {
        Floor f = new ElevatorSystem(10, null).floors[5];
        assertEquals(Door.DOOR_STATUS.CLOSED, f.door.getStatus());
        assertEquals(false, f.callButton.isDownLit);
        assertEquals(false, f.callButton.isUpLit);
        
        f.callButton.callElevator(Direction.DIRECTION.UP);
        assertEquals(Door.DOOR_STATUS.CLOSED, f.door.getStatus());
        assertEquals(false, f.callButton.isDownLit);
        assertEquals(true, f.callButton.isUpLit);
        f.arrivedAtFloor(Direction.DIRECTION.UP);
        assertEquals(Door.DOOR_STATUS.OPENED, f.door.getStatus());
        assertEquals(false, f.callButton.isDownLit);
        assertEquals(false, f.callButton.isUpLit);
        f.departFloor();
        assertEquals(Door.DOOR_STATUS.CLOSED, f.door.getStatus());
        assertEquals(false, f.callButton.isDownLit);
        assertEquals(false, f.callButton.isUpLit);

        f.callButton.callElevator(Direction.DIRECTION.DOWN);
        assertEquals(Door.DOOR_STATUS.CLOSED, f.door.getStatus());
        assertEquals(true, f.callButton.isDownLit);
        assertEquals(false, f.callButton.isUpLit);
        f.arrivedAtFloor(Direction.DIRECTION.DOWN);
        assertEquals(Door.DOOR_STATUS.OPENED, f.door.getStatus());
        assertEquals(false, f.callButton.isDownLit);
        assertEquals(false, f.callButton.isUpLit);
        f.departFloor();
        assertEquals(Door.DOOR_STATUS.CLOSED, f.door.getStatus());
        assertEquals(false, f.callButton.isDownLit);
        assertEquals(false, f.callButton.isUpLit);

        f.callButton.callElevator(Direction.DIRECTION.DOWN);
        f.callButton.callElevator(Direction.DIRECTION.UP);
        assertEquals(Door.DOOR_STATUS.CLOSED, f.door.getStatus());
        assertEquals(true, f.callButton.isDownLit);
        assertEquals(true, f.callButton.isUpLit);
        f.arrivedAtFloor(Direction.DIRECTION.DOWN);
        assertEquals(Door.DOOR_STATUS.OPENED, f.door.getStatus());
        assertEquals(false, f.callButton.isDownLit);
        assertEquals(true, f.callButton.isUpLit);
        f.departFloor();
        assertEquals(Door.DOOR_STATUS.CLOSED, f.door.getStatus());
        assertEquals(false, f.callButton.isDownLit);
        assertEquals(true, f.callButton.isUpLit);
    }
}
