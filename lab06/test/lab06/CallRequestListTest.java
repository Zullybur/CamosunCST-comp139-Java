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
public class CallRequestListTest {
    
    public CallRequestListTest() {
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
     * Test of getDestinations method, of class CallRequestList.
     */
    @Test
    public void testGetDestinations() {
        System.out.println("getDestinations");
        Elevator e = new Elevator(10, true);
        CallRequestList instance = new CallRequestList("UP");
        instance.addDestination(11);
        instance.getDestinations(e);
    }

    /**
     * Test of addDestination method, of class CallRequestList.
     */
    @Test
    public void testAddDestination() {
        System.out.println("addDestination");
        int floorID = 0;
        CallRequestList instance = null;
        instance.addDestination(floorID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
