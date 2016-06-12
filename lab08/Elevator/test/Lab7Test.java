import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Lab7Test {

    final static int NUM_FLOORS = 101;

    EventSimulator simulator;

    boolean[] upButtons;
    boolean[] downButtons;
    boolean[] targetButtons;
    boolean[] doors;

    public Lab7Test() {
        simulator = new EventSimulator(NUM_FLOORS);
        upButtons = new boolean[NUM_FLOORS];
        downButtons = new boolean[NUM_FLOORS];
        targetButtons = new boolean[NUM_FLOORS];
        doors = new boolean[NUM_FLOORS];
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

    public void upButtonCheck() {
        for (int i = 0; i < NUM_FLOORS; i++) {
            Assert.assertEquals("Floor " + i, upButtons[i], simulator.isUpCallButtonLit(i));
        }
    }

    public void downButtonCheck() {
        for (int i = 0; i < NUM_FLOORS; i++) {
            Assert.assertEquals("Floor " + i, downButtons[i], simulator.isDownCallButtonLit(i));
        }
    }

    public void targetButtonCheck() {
        for (int i = 0; i < NUM_FLOORS; i++) {
            Assert.assertEquals("Floor " + i, targetButtons[i], simulator.isTargetButtonLit(i));
        }
    }

    public void doorCheck(boolean open) {
        for (int i = 0; i < NUM_FLOORS; i++) {
            Assert.assertEquals("Floor " + i, doors[i], simulator.isFloorDoorOpen(i));
        }
        Assert.assertEquals(open, simulator.isElevatorDoorOpen());
    }

    @Test
    public void testFullCycle3() {
        System.out.println("----- testFullCycle 3 -----");

        upButtonCheck();
        downButtonCheck();
        targetButtonCheck();
        doorCheck(false);
        Assert.assertEquals(0, simulator.getCurrentElevatorFloor());

        for (int i = 99; i >= 55; i--) {
            System.out.println(simulator.getCurrentElevatorFloor());
            if (i % 11 == 0) {
                simulator.callElevatorDown(i);
                downButtons[i] = true;
            }
            upButtonCheck();
            downButtonCheck();
            targetButtonCheck();
            doorCheck(false);
            Assert.assertEquals((NUM_FLOORS - i - 2), simulator.getCurrentElevatorFloor());
            simulator.tick();
        }

        System.out.println(simulator.getCurrentElevatorFloor());
        for (int i = 22; i <= 44; i = i + 11) {
            simulator.callElevatorUp(i);
            upButtons[i] = true;
        }
        simulator.tick();
        System.out.println(simulator.getCurrentElevatorFloor());
        upButtonCheck();
        downButtonCheck();
        targetButtonCheck();
        doorCheck(false);
        Assert.assertEquals(46, simulator.getCurrentElevatorFloor());

        simulator.callElevatorDown(100);
        downButtons[100] = true;
        upButtonCheck();
        downButtonCheck();
        targetButtonCheck();
        doorCheck(false);

        for (int i = 46; i <= 100; i++) {
            System.out.println(simulator.getCurrentElevatorFloor());
            if (i % 11 == 0 || i == 100) {
                downButtons[i] = false;
                doors[i] = true;
                doorCheck(true);
                doors[i] = false;
                simulator.selectFloor(80);
                simulator.selectFloor(10);
                targetButtons[80] = true;
                targetButtons[10] = true;
            } else if (i == 80) {
                targetButtons[i] = false;
                doors[i] = true;
                doorCheck(true);
                doors[i] = false;
            } else {
                doorCheck(false);
            }
            upButtonCheck();
            downButtonCheck();
            targetButtonCheck();
            Assert.assertEquals(i, simulator.getCurrentElevatorFloor());
            simulator.tick();
        }

        for (int i = 99; i >= 10; i--) {
            System.out.println(simulator.getCurrentElevatorFloor());

            if (i == 10 || i == 22 || i == 33 || i == 44 || i == 80) {
                doors[i] = true;
                doorCheck(true);
                doors[i] = false;
                targetButtons[i] = false;
                upButtons[i] = false;
                simulator.selectFloor(100);
                targetButtons[100] = true;
            } else {
                doorCheck(false);
            }
            upButtonCheck();
            downButtonCheck();
            targetButtonCheck();
            simulator.tick();
        }

        for (int i = 10; i <= 100; i++) {
            System.out.println(simulator.getCurrentElevatorFloor());
            simulator.tick();
        }

        Assert.assertEquals(100, simulator.getCurrentElevatorFloor());
    }
}
