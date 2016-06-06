
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
public class EventSimulatorInterfaceTest2 {

    public EventSimulatorInterfaceTest2() {
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
        simulator.selectFloor(10);
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
        System.out.println("----- testFullCycle -----");
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
        System.out.println(simulator.isDownCallButtonLit(7));
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

    public void downButtonCheck(EventSimulatorInterface simulator, int numFloors, boolean[] trues) {
        for (int i = 0; i < numFloors; i++) {
            System.out.println("Checking Dwn Button " + i + ":" + simulator.isDownCallButtonLit(i));
            assertEquals("Floor " + i, trues[i], simulator.isDownCallButtonLit(i));
        }
    }

    public void upButtonCheck(EventSimulatorInterface simulator, int numFloors, boolean[] trues) {
        for (int i = 0; i < numFloors; i++) {
            System.out.println("Checking Up Button " + i + ":" + simulator.isUpCallButtonLit(i));
            assertEquals("Floor " + i, trues[i], simulator.isUpCallButtonLit(i));
        }
    }

    public void targetButtonCheck(EventSimulatorInterface simulator, int numFloors, boolean[] trues) {
        for (int i = 0; i < numFloors; i++) {
            System.out.println("Checking Tgt Button " + i + ":" + simulator.isTargetButtonLit(i));
            assertEquals("Floor " + i, trues[i], simulator.isTargetButtonLit(i));
        }
    }

    public void doorCheck(EventSimulatorInterface simulator, int numFloors, boolean[] trues, boolean open) {
        for (int i = 0; i < numFloors; i++) {
            System.out.println("Checking Door on floor " + i + ":" + simulator.isFloorDoorOpen(i));
            assertEquals("Floor " + i, trues[i], simulator.isFloorDoorOpen(i));
        }
        assertEquals(open, simulator.isElevatorDoorOpen());
    }

    @Test
    public void testFullCycle2() {
        System.out.println("----- testFullCycle 2 -----");

        System.out.println("----- INITIAL STATE -----");
        // Initial state
        int numFloors = 20;
        EventSimulatorInterface simulator = new EventSimulator(numFloors);
        boolean[] trues = new boolean[numFloors];
        downButtonCheck(simulator, numFloors, trues);
        upButtonCheck(simulator, numFloors, trues);
        targetButtonCheck(simulator, numFloors, trues);
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(0, simulator.getCurrentElevatorFloor());

        System.out.println("----- DOWN CALL FROM 7 -----");
        // Person on floor 7 wants to go down
        simulator.callElevatorDown(7);
        trues[7] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        upButtonCheck(simulator, numFloors, trues);
        targetButtonCheck(simulator, numFloors, trues);
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(0, simulator.getCurrentElevatorFloor());

        System.out.println("----- ELEVATOR TO FLOOR ONE -----");
        // The elevator starts to move...
        simulator.tick();
        trues[7] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        upButtonCheck(simulator, numFloors, trues);
        targetButtonCheck(simulator, numFloors, trues);
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(1, simulator.getCurrentElevatorFloor());

        System.out.println("----- UP CALL FROM ONE -----");
        // Person on floor 0 didn't quite make it; wants to go up
        simulator.callElevatorUp(0);
        trues[7] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        targetButtonCheck(simulator, numFloors, trues);
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(1, simulator.getCurrentElevatorFloor());

        System.out.println("----- DOWN CALL FROM SIX -----");
        // Person on floor 6 wants to go down
        simulator.callElevatorDown(6);
        trues[7] = true;
        trues[6] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        trues[6] = false;
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        targetButtonCheck(simulator, numFloors, trues);
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(1, simulator.getCurrentElevatorFloor());

        System.out.println("----- UP CALL FROM THREE -----");
        // Person on floor 3 wants to go up
        simulator.callElevatorUp(3);
        trues[7] = true;
        trues[6] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        trues[6] = false;
        trues[0] = true;
        trues[3] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        trues[3] = false;
        targetButtonCheck(simulator, numFloors, trues);
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(1, simulator.getCurrentElevatorFloor());

        System.out.println("----- ELEVATOR TO FLOOR TWO -----");
        // The elevator is now at floor 2
        simulator.tick();
        trues[7] = true;
        trues[6] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        trues[6] = false;
        trues[0] = true;
        trues[3] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        trues[3] = false;
        targetButtonCheck(simulator, numFloors, trues);
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(2, simulator.getCurrentElevatorFloor());

        System.out.println("----- SERVICE CALL AT FLOOR THREE -----");
        // Safe arrival at floor 3
        simulator.tick();
        trues[7] = true;
        trues[6] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        trues[6] = false;
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        targetButtonCheck(simulator, numFloors, trues);
        trues[3] = true;
        doorCheck(simulator, numFloors, trues, true);
        trues[3] = false;
        assertEquals(3, simulator.getCurrentElevatorFloor());

        System.out.println("----- FLOOR FIVE SELECTED -----");
        // Person wants to go to floor 5
        simulator.selectFloor(5);
        trues[7] = true;
        trues[6] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        trues[6] = false;
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        trues[5] = true;
        targetButtonCheck(simulator, numFloors, trues);
        trues[5] = false;
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(3, simulator.getCurrentElevatorFloor());

        System.out.println("----- ELEVATOR TO FLOOR FOUR -----");
        // The elevator is now at floor 4
        simulator.tick();
        trues[7] = true;
        trues[6] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        trues[6] = false;
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        trues[5] = true;
        targetButtonCheck(simulator, numFloors, trues);
        trues[5] = false;
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(4, simulator.getCurrentElevatorFloor());

        System.out.println("----- ELEVATOR TO FLOOR FIVE -----");
        // Safe arrival at floor 5
        simulator.tick();
        trues[7] = true;
        trues[6] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        trues[6] = false;
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        targetButtonCheck(simulator, numFloors, trues);
        trues[5] = true;
        doorCheck(simulator, numFloors, trues, true);
        trues[5] = false;
        assertEquals(5, simulator.getCurrentElevatorFloor());

        System.out.println("----- ELEVATOR TO FLOOR SIX -----");
        // On to floor 6
        simulator.tick();
        trues[7] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        targetButtonCheck(simulator, numFloors, trues);
        trues[6] = true;
        doorCheck(simulator, numFloors, trues, true);
        trues[6] = false;
        assertEquals(6, simulator.getCurrentElevatorFloor());

        System.out.println("----- FLOOR SIX SELECTED -----");
        // Person wants to go to floor 4
        simulator.selectFloor(4);
        trues[7] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[7] = false;
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        trues[4] = true;
        targetButtonCheck(simulator, numFloors, trues);
        trues[4] = false;
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(6, simulator.getCurrentElevatorFloor());

        System.out.println("----- ELEVATOR TO FLOOR SEVEN -----");
        // Safe arrival at floor 7
        simulator.tick();
        downButtonCheck(simulator, numFloors, trues);
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        trues[4] = true;
        targetButtonCheck(simulator, numFloors, trues);
        trues[4] = false;
        trues[7] = true;
        doorCheck(simulator, numFloors, trues, true);
        trues[7] = false;
        assertEquals(7, simulator.getCurrentElevatorFloor());

        System.out.println("----- FLOOR FOUR SELECTED AGAIN -----");
        // Person wants to go to floor 4 as well
        simulator.selectFloor(4);
        downButtonCheck(simulator, numFloors, trues);
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        trues[4] = true;
        targetButtonCheck(simulator, numFloors, trues);
        trues[4] = false;
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(7, simulator.getCurrentElevatorFloor());

        // Here we go again...
        for (int i = 6; i > 4; i--) {
            System.out.println("----- ELEVATOR TO FLOOR "+6+" -----");
            simulator.tick();
            downButtonCheck(simulator, numFloors, trues);
            trues[0] = true;
            upButtonCheck(simulator, numFloors, trues);
            trues[0] = false;
            trues[4] = true;
            targetButtonCheck(simulator, numFloors, trues);
            trues[4] = false;
            doorCheck(simulator, numFloors, trues, false);
            assertEquals(i, simulator.getCurrentElevatorFloor());
        }

        System.out.println("----- ELEVATOR TO FLOOR FOUR, FLOOR FIVE SELECTED -----");
        // Safe arrival at floor 4, followed by a request from floor 5
        simulator.tick();
        simulator.callElevatorDown(5);
        trues[5] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[5] = false;
        trues[0] = true;
        upButtonCheck(simulator, numFloors, trues);
        trues[0] = false;
        targetButtonCheck(simulator, numFloors, trues);
        trues[4] = true;
        doorCheck(simulator, numFloors, trues, true);
        trues[4] = false;
        assertEquals(4, simulator.getCurrentElevatorFloor());

        // Ok, person at floor 0 has waited patiently; now heading there
        for (int i = 3; i > 0; i--) {
            System.out.println("----- ELEVATOR TO FLOOR "+i+" -----");
            simulator.tick();
            trues[5] = true;
            downButtonCheck(simulator, numFloors, trues);
            trues[5] = false;
            trues[0] = true;
            upButtonCheck(simulator, numFloors, trues);
            trues[0] = false;
            targetButtonCheck(simulator, numFloors, trues);
            doorCheck(simulator, numFloors, trues, false);
            assertEquals(i, simulator.getCurrentElevatorFloor());
        }

        System.out.println("----- ARRIVE AT GROUND FLOOR -----");
        // Back where we started, safe and sound on the ground
        simulator.tick();
        trues[5] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[5] = false;
        upButtonCheck(simulator, numFloors, trues);
        targetButtonCheck(simulator, numFloors, trues);
        trues[0] = true;
        doorCheck(simulator, numFloors, trues, true);
        trues[0] = false;
        assertEquals(0, simulator.getCurrentElevatorFloor());

        System.out.println("----- FLOOR ONE SELECTED -----");
        // Easy request, wants to go to floor 1
        simulator.selectFloor(1);
        trues[5] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[5] = false;
        upButtonCheck(simulator, numFloors, trues);
        trues[1] = true;
        targetButtonCheck(simulator, numFloors, trues);
        trues[1] = false;
        doorCheck(simulator, numFloors, trues, false);
        assertEquals(0, simulator.getCurrentElevatorFloor());

        System.out.println("----- ARRIVE AT FLOOR ONE -----");
        // Arrival at 1
        simulator.tick();
        trues[5] = true;
        downButtonCheck(simulator, numFloors, trues);
        trues[5] = false;
        upButtonCheck(simulator, numFloors, trues);
        targetButtonCheck(simulator, numFloors, trues);
        trues[1] = true;
        doorCheck(simulator, numFloors, trues, true);
        trues[1] = false;
        assertEquals(1, simulator.getCurrentElevatorFloor());
        
        // Now heading to 5
        for (int i = 2; i < 5; i++) {
            System.out.println("----- ELEVATOR TO FLOOR "+i+" -----");
            simulator.tick();
            trues[5] = true;
            downButtonCheck(simulator, numFloors, trues);
            trues[5] = false;
            upButtonCheck(simulator, numFloors, trues);
            targetButtonCheck(simulator, numFloors, trues);
            doorCheck(simulator, numFloors, trues, false);
            assertEquals(i, simulator.getCurrentElevatorFloor());
        }

        // Leaving the story here; it's a real cliffhanger...
    }
}