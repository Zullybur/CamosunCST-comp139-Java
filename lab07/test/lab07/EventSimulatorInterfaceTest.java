package lab07;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Michael
 */
public class EventSimulatorInterfaceTest {

    public EventSimulatorInterfaceTest() {
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

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testInvalidCreateElevatorSystem() {
        exception.expect(IllegalArgumentException.class);
        EventSimulatorInterface simulator = new EventSimulator(1);
    }

    @Test
    public void testInvalidDownCallButton() {
        exception.expect(IllegalArgumentException.class);
        EventSimulatorInterface simulator = new EventSimulator(10);
        simulator.callElevatorDown(11);
    }

    @Test
    public void testInvalidUpCallButton() {
        exception.expect(IllegalArgumentException.class);
        EventSimulatorInterface simulator = new EventSimulator(10);
        simulator.callElevatorUp(-1);
    }

    @Test
    public void testInvalidTargetButton() {
        exception.expect(IllegalArgumentException.class);
        EventSimulatorInterface simulator = new EventSimulator(10);
        // Adjusted max floor to account for no floor zero
        simulator.selectFloor(11);
    }

    @Test
    public void testInvalidFloorDoor() {
        exception.expect(IllegalArgumentException.class);
        EventSimulatorInterface simulator = new EventSimulator(10);
        simulator.isFloorDoorOpen(100);
    }

    @Test
    public void testInvalidSelectFloor() {
        exception.expect(IllegalArgumentException.class);
        EventSimulatorInterface simulator = new EventSimulator(10);
        simulator.selectFloor(-100);
    }

    @Test
    public void testFullCycle() {
        // Initial state
        EventSimulatorInterface simulator = new EventSimulator(10);
        assertEquals(false, simulator.isDownCallButtonLit(7));
        assertEquals(false, simulator.isUpCallButtonLit(7));
        assertEquals(false, simulator.isTargetButtonLit(7));
        assertEquals(false, simulator.isFloorDoorOpen(7));
        assertEquals(false, simulator.isElevatorDoorOpen());
        assertEquals(0, simulator.getCurrentElevatorFloor());

        // Person on floor 7 wants to go down
        simulator.callElevatorDown(7);
        assertEquals(true, simulator.isDownCallButtonLit(7));
        assertEquals(false, simulator.isUpCallButtonLit(7));
        assertEquals(false, simulator.isTargetButtonLit(7));
        assertEquals(false, simulator.isFloorDoorOpen(7));
        assertEquals(false, simulator.isElevatorDoorOpen());
        assertEquals(0, simulator.getCurrentElevatorFloor());

        // Here we go...
        for (int i = 1; i < 7; i++) {
            simulator.tick();
            assertEquals(true, simulator.isDownCallButtonLit(7));
            assertEquals(false, simulator.isUpCallButtonLit(7));
            assertEquals(false, simulator.isTargetButtonLit(7));
            assertEquals(false, simulator.isFloorDoorOpen(7));
            assertEquals(false, simulator.isElevatorDoorOpen());
            assertEquals(i, simulator.getCurrentElevatorFloor());
        }

        // Safe arrival at floor 7
        simulator.tick();
        assertEquals(false, simulator.isDownCallButtonLit(7));
        assertEquals(false, simulator.isUpCallButtonLit(7));
        assertEquals(false, simulator.isTargetButtonLit(7));
        assertEquals(true, simulator.isFloorDoorOpen(7));
        assertEquals(true, simulator.isElevatorDoorOpen());
        assertEquals(7, simulator.getCurrentElevatorFloor());

        // Person wants to go to floor 4
        simulator.selectFloor(4);
        assertEquals(false, simulator.isDownCallButtonLit(7));
        assertEquals(false, simulator.isUpCallButtonLit(7));
        assertEquals(true, simulator.isTargetButtonLit(4));
        assertEquals(false, simulator.isFloorDoorOpen(7));
        assertEquals(false, simulator.isElevatorDoorOpen());
        assertEquals(7, simulator.getCurrentElevatorFloor());

        // Here we go again...
        for (int i = 6; i > 4; i--) {
            simulator.tick();
            assertEquals(false, simulator.isDownCallButtonLit(7));
            assertEquals(false, simulator.isDownCallButtonLit(4));
            assertEquals(false, simulator.isUpCallButtonLit(7));
            assertEquals(false, simulator.isUpCallButtonLit(4));
            assertEquals(false, simulator.isTargetButtonLit(7));
            assertEquals(true, simulator.isTargetButtonLit(4));
            assertEquals(false, simulator.isFloorDoorOpen(7));
            assertEquals(false, simulator.isFloorDoorOpen(4));
            assertEquals(false, simulator.isElevatorDoorOpen());
            assertEquals(i, simulator.getCurrentElevatorFloor());
        }

        // Safe arrival at floor 4
        simulator.tick();
        assertEquals(false, simulator.isDownCallButtonLit(7));
        assertEquals(false, simulator.isDownCallButtonLit(4));
        assertEquals(false, simulator.isUpCallButtonLit(7));
        assertEquals(false, simulator.isUpCallButtonLit(4));
        assertEquals(false, simulator.isTargetButtonLit(7));
        assertEquals(false, simulator.isTargetButtonLit(4));
        assertEquals(false, simulator.isFloorDoorOpen(7));
        assertEquals(true, simulator.isFloorDoorOpen(4));
        assertEquals(true, simulator.isElevatorDoorOpen());
        assertEquals(4, simulator.getCurrentElevatorFloor());
    }
}
