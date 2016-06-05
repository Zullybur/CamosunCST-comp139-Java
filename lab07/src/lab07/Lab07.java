/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab07;

/**
 *
 * @author MattCasiro
 */
public class Lab07 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initial state
        EventSimulatorInterface simulator = new EventSimulator(10);
        simulator.isDownCallButtonLit(7);
        simulator.isUpCallButtonLit(7);
        simulator.isTargetButtonLit(7);
        simulator.isFloorDoorOpen(7);
        simulator.isElevatorDoorOpen();
        simulator.getCurrentElevatorFloor();

        // Person on floor 7 wants to go down
        simulator.callElevatorDown(7);
        simulator.isDownCallButtonLit(7);
        simulator.isUpCallButtonLit(7);
        simulator.isTargetButtonLit(7);
        simulator.isFloorDoorOpen(7);
        simulator.isElevatorDoorOpen();
        simulator.getCurrentElevatorFloor();

        // Here we go...
        for (int i = 1; i < 7; i++) {
            simulator.tick();
            simulator.isDownCallButtonLit(7);
            simulator.isUpCallButtonLit(7);
            simulator.isTargetButtonLit(7);
            simulator.isFloorDoorOpen(7);
            simulator.isElevatorDoorOpen();
            simulator.getCurrentElevatorFloor();
        }

        // Safe arrival at floor 7
        simulator.tick();
        simulator.isDownCallButtonLit(7);
        simulator.isUpCallButtonLit(7);
        simulator.isTargetButtonLit(7);
        simulator.isFloorDoorOpen(7);
        simulator.isElevatorDoorOpen();
        simulator.getCurrentElevatorFloor();

        // Person wants to go to floor 4
        simulator.selectFloor(4);
        simulator.isDownCallButtonLit(7);
        simulator.isUpCallButtonLit(7);
        simulator.isTargetButtonLit(4);
        simulator.isFloorDoorOpen(7);
        simulator.isElevatorDoorOpen();
        simulator.getCurrentElevatorFloor();

        // Here we go again...
        for (int i = 6; i > 4; i--) {
            simulator.tick();
            simulator.isDownCallButtonLit(7);
            simulator.isDownCallButtonLit(4);
            simulator.isUpCallButtonLit(7);
            simulator.isUpCallButtonLit(4);
            simulator.isTargetButtonLit(7);
            simulator.isTargetButtonLit(4);
            simulator.isFloorDoorOpen(7);
            simulator.isFloorDoorOpen(4);
            simulator.isElevatorDoorOpen();
            simulator.getCurrentElevatorFloor();
        }

        // Safe arrival at floor 4
        simulator.tick();
        simulator.isDownCallButtonLit(7);
        simulator.isDownCallButtonLit(4);
        simulator.isUpCallButtonLit(7);
        simulator.isUpCallButtonLit(4);
        simulator.isTargetButtonLit(7);
        simulator.isTargetButtonLit(4);
        simulator.isFloorDoorOpen(7);
        simulator.isFloorDoorOpen(4);
        simulator.isElevatorDoorOpen();
        simulator.getCurrentElevatorFloor();
    }
}
