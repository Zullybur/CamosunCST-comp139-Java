/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab03;

import junit.framework.Assert;
import lab03.Signal.SignalColour;
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
public class SignalTest {
    
    public SignalTest() {
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
     * Test of tick method, of class Signal.
     */
    @Test
    public void testTick() {
        System.out.println("tick");
        Signal instance = new Signal();
        Assert.assertEquals("Initial value check", SignalColour.GREEN, instance.getColour());
        instance.tick();
        Assert.assertEquals("One tick value check", SignalColour.YELLOW, instance.getColour());
        instance.tick();
        Assert.assertEquals("Two tick value check", SignalColour.RED, instance.getColour());
        instance.tick();
        Assert.assertEquals("Three tick value check", SignalColour.GREEN, instance.getColour());
        instance.tick();
        Assert.assertEquals("Four tick value check", SignalColour.YELLOW, instance.getColour());
        instance.tick();
        Assert.assertEquals("Five tick value check", SignalColour.RED, instance.getColour());
        instance.tick();
        Assert.assertEquals("Six tick value check", SignalColour.GREEN, instance.getColour());
    }
}