public class Elevator implements CallElevatorInterface, GetIDInterface {

    protected CallElevatorSystemInterface sys;
    protected int nextFloor, currentFloor;
    protected Door door;
    protected TargetFloorButton[] buttons;
    protected UIController controller;

    /**
     * Creates an Elevator<br>
     *
     * Precondition: N/A<br>
     * Postcondition: An Elevator has been created with a Door and a given
     * number of TargetFloorButtons. The initial Floor is 0<br>
     * Cleanup: N/A<br>
     *
     * @param sys The ElevatorSystem to inform of Floor departure and
     * TargetFloorButton selections
     * @param numFloors The number of Floors for which to create
     * TargetFloorButtons
     * @throws IllegalArgumentException if numFloors is less than 2
     *
     * @see Door
     * @see ElevatorSystem
     * @see TargetFloorButton
     */
    public Elevator(CallElevatorSystemInterface sys, int numFloors, UIController controller) throws IllegalArgumentException {
        if (numFloors < 2) {
            throw new IllegalArgumentException();
        }

        this.sys = sys;
        currentFloor = 0;
        nextFloor = -1;
        door = new Door(this, controller);

        buttons = new TargetFloorButton[numFloors];
        for (int i = 0; i < numFloors; i++) {
            buttons[i] = new TargetFloorButton(this, i, controller);
        }

        this.controller = controller;
    }

    /**
     * Processes a clock tick<br>
     *
     * Precondition: N/A<br>
     * Postcondition: Moves the Elevator to the next scheduled Floor, if there
     * is one. In case of a departure, the Elevator Door is closed. In case of
     * an arrival, the Elevator Door is opened, the TargetFloorButton associated
     * with the current Floor is turned off, and the ElevatorSystem is informed
     * of the arrival<br>
     * Cleanup: N/A<br>
     *
     * @see Door#closeDoor()
     * @see Door#openDoor()
     * @see ElevatorSystem#removeFloor(int)
     * @see TargetFloorButton#setLit(boolean)
     */
    public void tick() {
        nextFloor = sys.getNextFloor();
        if (nextFloor == -1) {
            door.openDoor();
            return;
        }

        if (door.getStatus() == Door.DOOR_STATUS.OPENED) {
            door.closeDoor();
        }

        if (currentFloor < nextFloor) {
            currentFloor++;
        } else if (currentFloor > nextFloor) {
            currentFloor--;
        }

        if (currentFloor == nextFloor && 
                (buttons[currentFloor].isLit || (
                    sys.checkButton(currentFloor, Direction.DIRECTION.UP) && sys.getDir() == Direction.DIRECTION.UP ||
                    sys.checkButton(currentFloor, Direction.DIRECTION.DOWN) && sys.getDir() == Direction.DIRECTION.DOWN)
                )){
            door.openDoor();
            buttons[currentFloor].setLit(false);
            if (sys != null) {
                sys.removeFloor(currentFloor);
            }

            nextFloor = -1;
        }
    }

    /**
     * Gets the current Floor of the Elevator<br>
     *
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return the current Floor
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Selects a target Floor<br>
     *
     * Precondition: N/A<br>
     * Postcondition: The ElevatorSystem has been informed of the selected Floor
     * and the associated TargetFloorButton in the Elevator has been lit. Does
     * nothing if the floor is the current floor<br>
     * Cleanup: N/A<br>
     *
     * @param floor the selected Floor
     * @throws IllegalArgumentException if the floor is out of range
     *
     * @see Door#closeDoor()
     * @see ElevatorSystem#addFloor(int)
     * @see TargetFloorButton#setLit(boolean)
     */
    @Override
    public void selectFloor(int floor) throws IllegalArgumentException {
        if (floor < 0 || floor >= buttons.length) {
            throw new IllegalArgumentException();
        }

        if (floor == currentFloor) {
            return;
        }

        door.closeDoor();
        buttons[floor].setLit(true);
        if (sys != null) {
            sys.addFloor(floor);
        }
    }

    /**
     * Gets the current Floor of the Elevator<br>
     *
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return the current Floor
     */
    @Override
    public int getID() {
        return getCurrentFloor();
    }
}
