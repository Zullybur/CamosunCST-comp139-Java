
public class Floor implements CallFloorInterface, GetIDInterface {

    CallButton callButton;
    Door door;
    CallElevatorSystemInterface system;
    int id;
    UIControllerInterface controller;

    /**
     * Creates a Floor<br>
     *
     * Precondition: N/A<br>
     * Postcondition: A Floor has been created, with its CallButton and Door<br>
     * Cleanup: N/A<br>
     *
     * @param system the ElevatorSystem to call if the Floor's CallButton has
     * been pressed
     * @param id the id of this Floor
     * @param controller the controller to notify that
     *
     * @see CallButton
     * @see Door
     * @see ElevatorSystem
     */
    public Floor(CallElevatorSystemInterface system, int id, UIControllerInterface controller) {
        callButton = new CallButton(this, controller);
        door = new Door(this, controller);
        this.system = system;
        this.id = id;
        this.controller = controller;
    }

    /**
     * Notifies this Floor that an Elevator has arrived<br>
     *
     * Precondition: N/A<br>
     * Postcondition: The chime has been sounded, the callButton associated with
     * the given Direction has been turned off, and this Floor's Door has been
     * opened.<br>
     * Cleanup: N/A<br>
     *
     * @param dir The Direction in which the Elevator will head
     *
     * @see Chime#soundChime()
     * @see CallButton#setLit(Direction.DIRECTION, boolean)
     * @see Door#openDoor()
     */
    public void arrivedAtFloor(Direction.DIRECTION dir) {
        Chime.soundChime();
        callButton.setLit(dir, false);
        door.openDoor();
    }

    /**
     * Calls the Elevator to this Floor<br>
     *
     * Precondition: N/A<br>
     * Postcondition: The ElevatorSystem, assuming it was passed in as part of
     * the constructor call, has been informed that an Elevator is to be sent to
     * this Floor<br>
     * Cleanup: N/A<br>
     *
     * @param dir the Direction in which the caller wants to head
     *
     * @see ElevatorSystem#callElevator(int, Direction.DIRECTION)
     */
    @Override
    public void callElevator(Direction.DIRECTION dir) {
        if (system != null) {
            callButton.callElevator(dir);
            system.callElevator(id, dir);
        }
    }
    
    /**
     * Get a callButton that can be used to check the state of the lights.
     * 
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return a callButton with getUpLit() and getDownLit() methods
     */
    public CheckCallButtonInterface getCallButtonInterface() {
        return callButton;
    }

    /**
     * Closes this Floor's Door<br>
     *
     * Precondition: N/A<br>
     * Postcondition: The Door has been closed<br>
     * Cleanup: N/A<br>
     *
     * @see Door#closeDoor()
     */
    public void departFloor() {
        door.closeDoor();
        if (controller != null) {
            controller.setDoorOpen(false, id);
        }
    }

    /**
     * Returns the Floor's ID<br>
     *
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return the floor number
     */
    @Override
    public int getID() {
        return id;
    }
}
