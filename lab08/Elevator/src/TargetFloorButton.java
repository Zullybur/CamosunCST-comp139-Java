
public class TargetFloorButton {

    CallElevatorInterface elevator;
    int id;
    boolean isLit;
    UIControllerInterface controller;

    /**
     * Creates a TargetFloorButton<br>
     *
     * Precondition: N/A<br>
     * Postcondition: A TargetFloorButton has been created with a given id. It
     * is not lit.<br>
     * Cleanup: N/A<br>
     *
     * @param elevator the elevator to call in case the TargetFloorButton has
     * been pressed
     * @param id the id to give the TargetFloorButton
     * @param controller the controller to notify when a button status changed
     *
     * @see Elevator
     */
    public TargetFloorButton(CallElevatorInterface elevator, int id, UIControllerInterface controller) {
        isLit = false;
        this.elevator = elevator;
        this.id = id;
        this.controller = controller;
    }

    /**
     * Returns the status of the TargetFloorButton<br>
     *
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return true if the TargetFloorButton is lit, false otherwise
     */
    public boolean getLit() {
        return isLit;
    }

    /**
     * Informs the Elevator that this TargetFloorButton has been pressed<br>
     *
     * Precondition: N/A<br>
     * Postcondition: The Elevator, if a reference to it was passed in as part
     * of the constructor call, was informed that this TargetFloorButton was
     * pressed<br>
     * Cleanup: N/A<br>
     *
     * @see Elevator#selectFloor(int)
     */
    public void selectFloor() {
        if (elevator != null) {
            elevator.selectFloor(id);
        }
        setLit(true);
    }

    /**
     * Sets the status of the TargetFloorButton<br>
     *
     * Precondition: N/A<br>
     * Postcondition: This TargetFloorButton has been set to the given
     * status<br>
     * Cleanup: N/A<br>
     *
     * @param lit true if this TargetFloorButton is to be lit, false otherwise
     */
    public void setLit(boolean lit) {
        isLit = lit;
        if (controller != null) {
            controller.setTargetButtonLit(lit, id);
        }
    }
}
