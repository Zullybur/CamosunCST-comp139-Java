

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoorTest {
    
    public DoorTest() {
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
    public void testDoor() {
        Door instance = new Door(null, null);
        assertEquals(Door.DOOR_STATUS.CLOSED, instance.getStatus());
        instance.openDoor();
        assertEquals(Door.DOOR_STATUS.OPENED, instance.getStatus());
        instance.closeDoor();
        assertEquals(Door.DOOR_STATUS.CLOSED, instance.getStatus());
    }
}
