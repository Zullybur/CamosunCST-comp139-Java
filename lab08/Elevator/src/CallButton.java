
public class CallButton implements CheckCallButtonInterface {

    protected boolean isDownLit, isUpLit;
    protected CallFloorInterface floor;
    protected UIControllerInterface controller;

    /**
     * Creates a CallButton<br>
     *
     * Precondition: N/A<br>
     * Postcondition: A CallButton has been created, with its up and down lights
     * turned off<br>
     * Cleanup: N/A<br>
     *
     * @param floor the Floor on which the CallButton is located
     * @param controller the Controller to notify if a call button status
     * changes
     *
     * @see Floor
     */
    public CallButton(CallFloorInterface floor, UIControllerInterface controller) {
        isDownLit = isUpLit = false;
        this.floor = floor;
        this.controller = controller;
    }

    /**
     * Calls the elevator to the CallButton's Floor<br>
     *
     * Precondition: N/A<br>
     * Postcondition: The Elevator has been called to the Floor with the desired
     * direction<br>
     * Cleanup: N/A<br>
     *
     * @param dir The Direction which the caller wants to go, once in the
     * Elevator
     *
     * @see Floor#callElevator(Direction.DIRECTION)
     */
    public void callElevator(Direction.DIRECTION dir) {
        setLit(dir, true);
//        if (floor != null) {
//            floor.callElevator(dir);
//        }
    }

    /**
     * Gets the down button status<br>
     *
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return true if the down button is lit, false otherwise
     */
    @Override
    public boolean getDownLit() {
        return isDownLit;
    }

    /**
     * Gets the up button status<br>
     *
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return true if the up button is lit, false otherwise
     */
    @Override
    public boolean getUpLit() {
        return isUpLit;
    }

    /**
     * Sets the up or down button to a given status<br>
     *
     * Precondition: N/A<br>
     * Postcondition: If the Direction is UP, the up button status has been set
     * to the given status. If the Direction is DOWN, the down button status has
     * been set to the given status. Other Directions are ignored<br>
     * Cleanup: N/A<br>
     *
     * @param dir the button that is to be given a new status
     * @param status the status to give the selected button
     */
    protected void setLit(Direction.DIRECTION dir, boolean status) {
        switch (dir) {
            case UP:
                isUpLit = status;
                break;
            case DOWN:
                isDownLit = status;
                break;
            default:
                break;
        }

        if (controller != null) {
            controller.setCallButtonLit(dir, status, floor.getID());
        }
    }
}
