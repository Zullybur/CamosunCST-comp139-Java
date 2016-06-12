
public class Door {

    public enum DOOR_STATUS {
        OPENED, CLOSED;
    }

    protected DOOR_STATUS status;
    protected GetIDInterface idSource;
    protected UIControllerInterface controller;

    /**
     * Creates a Door that is initially closed<br>
     *
     * Precondition: N/A<br>
     * Postcondition: A closed Door has been created<br>
     * Cleanup: N/A<br>
     *
     * @param controller the controller to notify when the door status changes
     * @param idSource
     */
    public Door(GetIDInterface idSource, UIControllerInterface controller) {
        status = DOOR_STATUS.CLOSED;
        this.idSource = idSource;
        this.controller = controller;
    }

    /**
     * Closes this Door<br>
     *
     * Precondition: N/A<br>
     * Postcondition: This Door's status has been set to closed<br>
     * Cleanup: N/A<br>
     *
     */
    public void closeDoor() {
        status = DOOR_STATUS.CLOSED;
        if (controller != null) {
            controller.setDoorOpen(false, idSource.getID());
        }
    }

    /**
     * Gets the status of the Door<br>
     *
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return the Door status
     */
    public DOOR_STATUS getStatus() {
        return status;
    }

    /**
     * Opens this Door<br>
     *
     * Precondition: N/A<br>
     * Postcondition: This Door's status has been set to OPEN<br>
     * Cleanup: N/A<br>
     *
     */
    public void openDoor() {
        status = DOOR_STATUS.OPENED;
        if (controller != null) {
            controller.setDoorOpen(true, idSource.getID());
        }
    }
}
