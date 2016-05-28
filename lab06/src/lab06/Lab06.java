/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

/**
 *
 * @author MattCasiro
 */
public class Lab06 {

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
        // Bottom floor is 1, not zero :)
        simulator.getCurrentElevatorFloor();

        // Person on floor 7 wants to go down
        simulator.callElevatorDown(7);
        simulator.isDownCallButtonLit(7);
        simulator.isUpCallButtonLit(7);
        simulator.isTargetButtonLit(7);
        simulator.isFloorDoorOpen(7);
        simulator.isElevatorDoorOpen();
        // Bottom floor is 1, not zero :)
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
        // Adjusted max floor to account for no floor zero
//        simulator.selectFloor(11);
        // Safe arrival at floor 7
        simulator.tick();
        simulator.isDownCallButtonLit(7);
        simulator.isUpCallButtonLit(7);
        simulator.isTargetButtonLit(7);
        simulator.isFloorDoorOpen(7);
        simulator.isElevatorDoorOpen();
        simulator.getCurrentElevatorFloor();
    }
    
}
