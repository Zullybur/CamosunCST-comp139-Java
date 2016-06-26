
import java.util.concurrent.ConcurrentSkipListMap;

public class ElevatorSystem implements CallElevatorSystemInterface {

    Floor[] floors;
    Integer nextFloor;
    Direction.DIRECTION dir;
    Elevator elevator;
    ConcurrentSkipListMap floorList;
    UIController controller;

    /**
     * Creates an ElevatorSystem<br>
     *
     * Precondition: N/A<br>
     * Postcondition: An ElevatorSystem with the given number of Floors and an
     * Elevator have been created.<br>
     * Cleanup: N/A<br>
     *
     * @param numFloors the number of Floors to create
     * @param controller
     * @throws IllegalArgumentException if the number of Floors is less than 2
     *
     * @see Elevator
     * @see Floor
     */
    public ElevatorSystem(int numFloors, UIController controller) throws IllegalArgumentException {
        if (numFloors < 2) {
            throw new IllegalArgumentException();
        }

        floors = new Floor[numFloors];
        for (int i = 0; i < numFloors; i++) {
            floors[i] = new Floor(this, i, controller);
        }

        nextFloor = -1;
        dir = Direction.DIRECTION.NONE;

        elevator = new Elevator(this, numFloors, controller);

        floorList = new ConcurrentSkipListMap();

        this.controller = controller;
    }

    /**
     * Adds a Floor to the list of scheduled Floors. This method is called by an
     * Elevator.<br>
     *
     * Precondition: N/A<br>
     * Postcondition: The given floor has been added to the list of scheduled
     * Floors. The current Floor has been informed of the impending departure of
     * the Elevator<br>
     * Cleanup: N/A<br>
     *
     * @param floor the Floor to add to the list of scheduled Floors
     * @throws IllegalArgumentException if the floor is out of range or the
     * current Floor
     *
     * @see Floor#departFloor()
     */
    @Override
    public void addFloor(int floor) throws IllegalArgumentException {
        if (floor < 0 || floor >= floors.length || floor == elevator.getCurrentFloor()) {
            throw new IllegalArgumentException();
        }
        
        floorList.put(floor, floor);
        floors[elevator.getCurrentFloor()].departFloor();

        computeNextFloor();
    }

    /**
     * Calls an Elevator to a given Floor. This method is called by a Floor.<br>
     *
     * Precondition: N/A<br>
     * Postcondition: The given Floor has been added to the list of scheduled
     * Floors<br>
     * Cleanup: N/A<br>
     *
     * @param floor The floor to add to the schedule
     * @param dir The direction in which the caller wants to head
     * @throws IllegalArgumentException if the floor is out of range
     */
    @Override
    public void callElevator(int floor, Direction.DIRECTION dir) throws IllegalArgumentException {
        if (floor < 0 || floor >= floors.length) {
            throw new IllegalArgumentException();
        }

        if (floor == elevator.getCurrentFloor()) {
            floors[floor].arrivedAtFloor(dir);
            return;
        }

        floorList.put(floor, floor);
        computeNextFloor();
    }

    /**
     * Check a call button state for a given direction.
     * 
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param floor is the floor to get the button for
     * @param dir is the direction to check, must be UP or DOWN
     * @return true if the button is lit, false if the button is not lit
     * @throws IllegalArgumentException if floor is out of range or direction is invalid
     */
    @Override
    public boolean checkButton(int floor, Direction.DIRECTION dir) throws IllegalArgumentException {
        if (floor < 0 || floor >= floors.length) {
            throw new IllegalArgumentException();
        }
        
        if (dir == Direction.DIRECTION.DOWN) {
            return floors[floor].getCallButtonInterface().getDownLit();
        } else if (dir == Direction.DIRECTION.UP) {
            return floors[floor].getCallButtonInterface().getUpLit();
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Computes the next Floor to visit<br>
     *
     * Precondition: N/A<br>
     * Postcondition: The direction of the Elevator and the next target Floor
     * have been set<br>
     * Cleanup: N/A<br>
     *
     */
    public void computeNextFloor() {
        int currentFloor = elevator.getCurrentFloor();

        if (dir == null) {
            dir = Direction.DIRECTION.NONE;
        }

        if (dir == Direction.DIRECTION.NONE) {
            dir = Direction.DIRECTION.UP;
        }

        if (dir == Direction.DIRECTION.UP) {
<<<<<<< HEAD

            // We are headed up, can we go any higher?
            nextFloor = (Integer) floorList.higherKey(currentFloor);
=======
            // Does current floor still need servicing in this direction?
            if (floors[currentFloor].getCallButtonInterface().getUpLit()) {
                nextFloor = currentFloor;
            } else {
                // We are headed up, can we go any higher?
                nextFloor = (Integer) floorList.higherKey(currentFloor);
            }
>>>>>>> bonusAttempt
            if (nextFloor != null) {
                return;
            }

            // Nope, let's go down
            dir = Direction.DIRECTION.DOWN;
        }

        // Does current floor still need servicing in this direction?
        if (floors[currentFloor].getCallButtonInterface().getDownLit()) {
                nextFloor = currentFloor;
        } else {
        // We are going down, can we go any lower?
            nextFloor = (Integer) floorList.lowerKey(currentFloor);
        }
        if (nextFloor != null) {
            return;
        }

        // Nope. OK, time to rest
        dir = Direction.DIRECTION.NONE;
        nextFloor = -1;

    }

    /**
     * Get the current direction<br>
     * 
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return the elevator's direction, UP or DOWN
     */
    @Override
    public Direction.DIRECTION getDir() {
        return dir;
    }
    
    /**
     * Gets the next Floor.<br>
     *
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return the next target Floor
     */
    @Override
    public int getNextFloor() {
        computeNextFloor();
        return nextFloor;
    }

    /**
     * Gets an interface to the selectFloor method of an elevator.
     * 
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return a CallElevatorInterface object
     */
    public CallElevatorInterface getCallElevatorInterface() {
        return (CallElevatorInterface)elevator;
    }
    
    /**
     * Get an array of floors with callElevator and getID methods.
     * 
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return 
     */
    public CallFloorInterface[] getCallFloorInterface() {
        return this.floors;
    }
    
    /**
     * Removes a Floor from the list of scheduled Floors<br>
     *
     * Precondition: N/A<br>
     * Postcondition: The given Floor has been removed from the list of
     * scheduled Floors and the given Floor has also been informed of the
     * Elevator arrival<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor to remove from the schedule
     * @throws IllegalArgumentException if the floor is out of range
     *
     * @see Floor#arrivedAtFloor(Direction.DIRECTION)
     */
    @Override
    public void removeFloor(int floor) throws IllegalArgumentException {
        if (floor < 0 || floor >= floors.length) {
            throw new IllegalArgumentException();
        }

<<<<<<< HEAD
        floors[floor].arrivedAtFloor(Direction.DIRECTION.UP);
        floors[floor].arrivedAtFloor(Direction.DIRECTION.DOWN);
=======
        // Remove up call if moving up
        if (this.dir == Direction.DIRECTION.UP && checkButton(floor, Direction.DIRECTION.UP)) {
            floors[floor].arrivedAtFloor(Direction.DIRECTION.UP);
        }
//        Remove down call if moving down
        if (this.dir == Direction.DIRECTION.DOWN && checkButton(floor, Direction.DIRECTION.DOWN)) {
            floors[floor].arrivedAtFloor(Direction.DIRECTION.DOWN);
        }
        
        // Remove floor if fully serviced
        if (!floors[floor].callButton.isDownLit && !floors[floor].callButton.isUpLit) {
            floorList.remove(floor);
        }
>>>>>>> bonusAttempt
        
        computeNextFloor();
    }

    /**
     * Processes a clock tick<br>
     *
     * Precondition: N/A<br>
     * Postcondition: Assuming there is a scheduled target Floor, the current
     * floor has been informed of the Elevator's departure and the Elevator has
     * been informed of the clock tick<br>
     * Cleanup: N/A<br>
     *
     * @see Elevator#tick()
     * @see Floor#departFloor()
     */
    public void tick() {
        if (nextFloor != -1) {
            floors[elevator.getCurrentFloor()].departFloor();
        }
        else if (!floorList.isEmpty()) {
            computeNextFloor();
        }
        elevator.tick();
    }
}
