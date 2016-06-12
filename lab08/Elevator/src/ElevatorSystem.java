
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
            // We are headed up, can we go any higher?
            nextFloor = (Integer) floorList.higherKey(currentFloor);
            if (nextFloor != null) {
                return;
            }

            // Nope, let's go down
            dir = Direction.DIRECTION.DOWN;
        }

        // We are going down, can we go any lower?
        nextFloor = (Integer) floorList.lowerKey(currentFloor);
        if (nextFloor != null) {
            return;
        }

        // Nope. OK, time to rest
        dir = Direction.DIRECTION.NONE;
        nextFloor = -1;

    }

    /**
     * Gets the next Floor<br>
     *
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return the next target Floor
     */
    @Override
    public int getNextFloor() {
        return nextFloor;
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
     *
     * TODO: Support different directions
     */
    @Override
    public void removeFloor(int floor) throws IllegalArgumentException {
        if (floor < 0 || floor >= floors.length) {
            throw new IllegalArgumentException();
        }

        floorList.remove(floor);
        floors[floor].arrivedAtFloor(Direction.DIRECTION.UP);
        floors[floor].arrivedAtFloor(Direction.DIRECTION.DOWN);
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
