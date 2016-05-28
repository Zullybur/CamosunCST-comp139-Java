package lab06;

/**
 *
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class EventSimulator implements EventSimulatorInterface {
    ElevatorSystem es;
    public EventSimulator(int numFloors) throws IllegalArgumentException
    {
        if (numFloors < 2)
        {
            throw new IllegalArgumentException();
        }
        es = new ElevatorSystem(numFloors, 0, true, 1);
    }
    /**
     * LIGHTS
     */
    
    /**
     * Checks whether or not the down button on the given floor is lit<br>
     *
     * Preconditions: numFloors is valid<br>
     * Postconditions: N/A<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor to check
     * @return true if the button is lit, false otherwise
     * @throws IllegalArgumentException if numFloors is invalid
     */
    @Override
    public boolean isDownCallButtonLit(int floor) throws IllegalArgumentException
    {
        return es.isCallButtonLightLit(floor, "DOWN");
    }

    /**
     * Checks whether or not the up button on the given floor is lit<br>
     *
     * Preconditions: numFloors is valid<br>
     * Postconditions: N/A<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor to check
     * @return true if the button is lit, false otherwise
     * @throws IllegalArgumentException if numFloors is invalid
     */
    @Override
    public boolean isUpCallButtonLit(int floor) throws IllegalArgumentException
    {
        return es.isCallButtonLightLit(floor, "UP");
    }

    /**
     * Checks whether or not the target button corresponding to the given floor
     * is lit in the elevator<br>
     *
     * Preconditions: numFloors is valid<br>
     * Postconditions: N/A<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor to which the button inside the elevator
     * corresponds
     * @return true if the button is lit, false otherwise
     * @throws IllegalArgumentException if numFloors is invalid
     */
    @Override
    public boolean isTargetButtonLit(int floor) throws IllegalArgumentException
    {
        return es.isElevatorButtonLightLit(floor, 1);
    }

    /**
     * DOORS
     */
    
     /**
     * Check if the elevator door is open<br>
     *
     * Preconditions: N/A<br>
     * Postconditions: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return true if the door is open, false otherwise
     */
    @Override
    public boolean isElevatorDoorOpen()
    {
        return es.isElevatorDoorOpen(1);
    }
    
    /**
     * Checks whether or not the door corresponding to the given floor
     * is open<br>
     *
     * Preconditions: numFloors is valid<br>
     * Postconditions: N/A<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor to check
     * @return true if the door is open, false otherwise
     * @throws IllegalArgumentException if numFloors is invalid
     */
    @Override
    public boolean isFloorDoorOpen(int floor) throws IllegalArgumentException
    {
        return es.isFloorDoorOpen(floor, 0);
    }

    /**
     * ELEVATOR
     */

    /**
     * Check where the elevator is<br>
     *
     * Preconditions: N/A<br>
     * Postconditions: N/A<br>
     * Cleanup: N/A<br>
     *
     * @return the floor at which the elevator is located
     */
    @Override
    public int getCurrentElevatorFloor()
    {
        return es.getElevatorLocation(1);
    }
    
    /**
     * EVENTS
     */
    
    /**
     * Simulates an event where a person on the given floor presses the down
     * button<br>
     *
     * Preconditions: floor is valid<br>
     * Postconditions: The down button has been pressed<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor where the call is to be made
     * @throws IllegalArgumentException if numFloors is invalid
     */
    @Override
    public void callElevatorDown(int floor) throws IllegalArgumentException
    {
        es.callElevator(floor, "DOWN");
    }

    /**
     * Simulates an event where a person on the given floor presses the up
     * button<br>
     *
     * Preconditions: floor is valid<br>
     * Postconditions: The down button has been pressed<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor where the call is to be made
     * @throws IllegalArgumentException if numFloors is invalid
     */
    @Override
    public void callElevatorUp(int floor) throws IllegalArgumentException
    {
        es.callElevator(floor, "UP");
    }
    
     /**
     * Simulates an event where a person selects a floor from the floor panel
     * inside the elevator<br>
     * 
     * Preconditions: floor is valid<br>
     * Postconditions: The down button has been pressed<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor that is selected
     * @throws IllegalArgumentException if numFloors is invalid
     */
    @Override
    public void selectFloor(int floor) throws IllegalArgumentException
    {
        es.selectFloor(floor, 0);
    }
    
    /**
     * Issue a time event. If the elevator is in motion, a tick event
     * causes the elevator to go to the next floor. If the elevator
     * arrives, the doors open and buttons state are changed immediately.
     * If the elevator is stationary, it checks for a floor and moves
     * immediately if there is an outstanding elevator request. Otherwise,
     * this method does nothing. 
     */
    @Override
    public void tick()
    {
        es.tick();
    }
}
