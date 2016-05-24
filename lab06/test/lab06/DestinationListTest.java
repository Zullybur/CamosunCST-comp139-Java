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
public class DestinationListTest {
    
    public DestinationListTest() {
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
     * Test of getNextDestination method, of class DestinationList.
     */
    @Test
    public void testGetNextDestination() {
        System.out.println("getNextDestination");
        DestinationList instance = new DestinationList(10);
        instance.addDestination(4, 2);
        instance.addDestination(1, 2);
        int expResult1 = 4;
        int expResult2 = 1;
        int result = instance.getNextDestination(1);
        assertEquals(expResult1, result);
        result = instance.getNextDestination(1);
        assertEquals(expResult2, result);
    }
}