/*
 */
package lab06;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MattCasiro
 */
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

//    /**
//     * Test of getElevatorCount method, of class Elevator.
//     */
//    @Test
//    public void testGetElevatorCount() {
//        System.out.println("getElevatorCount");
//        Elevator instance = new Elevator(2, false);
//        int expResult = 4;
//        int result = instance.getElevatorCount();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getElevatorID method, of class Elevator.
//     */
//    @Test
//    public void testGetElevatorID() {
//        System.out.println("getElevatorID");
//        Elevator instance = new Elevator(2, true);
//        int expResult = 105;
//        int result = instance.getElevatorID();
//        assertEquals(expResult, result);
//    }

    /**
     * Test of getCurrentFloor method, of class Elevator.
     */
    @Test
    public void testGetCurrentFloor() {
        System.out.println("getCurrentFloor");
        Elevator instance = new Elevator(15, false);
        int expResult = 1;
        int result = instance.getCurrentFloor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTopFloor method, of class Elevator.
     */
    @Test
    public void testGetTopFloor() {
        System.out.println("getTopFloor");
        Elevator instance = new Elevator(15, false);
        int expResult = 16;
        int result = instance.getTopFloor();
        assertEquals(expResult, result);
        instance = new Elevator(15, true);
        expResult = 15;
        result = instance.getTopFloor();
        assertEquals(expResult, result);
    }

    /**
     * Test of selectFloor method, of class Elevator.
     */
    @Test
    public void testSelectFloor() {
        System.out.println("selectFloor");
        int floorID = 5;
        Elevator instance = new Elevator(10, false);
        instance.selectFloor(floorID);
        int result = instance.getDestination();
        assertEquals(floorID, result);
        instance.selectFloor(6);
        result = instance.getDestination();
        assertEquals(floorID, result);
    }

    /**
     * Test of tick method, of class Elevator.
     */
    @Test
    public void testTick() {
        System.out.println("tick");
        Elevator instance = null;
        instance.tick();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
