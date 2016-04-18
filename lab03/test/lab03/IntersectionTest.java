/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab03;

import junit.framework.Assert;
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
public class IntersectionTest {
    
    public IntersectionTest() {
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
     * Test of tick method, of class Intersection.
     */
    @Test
    public void testTick() {
        System.out.println("tick");
        Intersection instance = new Intersection();
        Assert.assertEquals("Initial state check", "N:GREEN S:GREEN W:RED E:RED", instance.getState());
        instance.tick();
        Assert.assertEquals("One tick check", "N:YELLOW S:YELLOW W:RED E:RED", instance.getState());
        instance.tick();
        Assert.assertEquals("Two tick check", "N:RED S:RED W:RED E:RED", instance.getState());
        instance.tick();
        Assert.assertEquals("Three tick check", "N:RED S:RED W:GREEN E:GREEN", instance.getState());
        instance.tick();
        Assert.assertEquals("Four tick check", "N:RED S:RED W:YELLOW E:YELLOW", instance.getState());
        instance.tick();
        Assert.assertEquals("Five tick check", "N:RED S:RED W:RED E:RED", instance.getState());
        instance.tick();
        Assert.assertEquals("Six tick check", "N:GREEN S:GREEN W:RED E:RED", instance.getState());
        instance.tick();
        Assert.assertEquals("Seven tick check", "N:YELLOW S:YELLOW W:RED E:RED", instance.getState());
        instance.tick();
        Assert.assertEquals("Eight tick check", "N:RED S:RED W:RED E:RED", instance.getState());
        instance.tick();
        Assert.assertEquals("Nine tick check", "N:RED S:RED W:GREEN E:GREEN", instance.getState());
        instance.tick();
        Assert.assertEquals("Ten tick check", "N:RED S:RED W:YELLOW E:YELLOW", instance.getState());
        instance.tick();
        Assert.assertEquals("Eleven tick check", "N:RED S:RED W:RED E:RED", instance.getState());
        instance.tick();
        Assert.assertEquals("Twelve tick check", "N:GREEN S:GREEN W:RED E:RED", instance.getState());
    }
}
