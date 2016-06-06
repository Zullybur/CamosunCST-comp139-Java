/*
 */
package lab07;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class CallRequestList
{
    private LinkedList<CallButton> requestList;
    private Direction direction;
    
    /**
     * Instantiate a Call Request List to hold a list of waiting elevator calls
     * that want to move in a specified direction.
     * @param direction is "UP" if the list services up call buttons, otherwise "DOWN"
     */
    public CallRequestList(Direction direction) throws IllegalArgumentException
    {
        if (direction == Direction.DOWN || direction == Direction.UP)
        {
            this.direction = direction;
        } else {
            throw new IllegalArgumentException();
        }
        requestList = new LinkedList<>();
    }
    
    /**
     * Assign valid destinations to a given elevator.<br>
     * 
     * PRE: The elevator is moving in the same direction as the Call Request List
     * is serving, or the elevator is stationary<br>
     * POST: The elevator is assigned all valid destinations given its current state<br>
     * Cleanup: N/A<br>
     * 
     * @param e is the elevator being assigned new destinations
     */
    public void getDestinations(Elevator e)
    {
        // Iterate over the destination list and add call buttons to the elevator
        // if the elevator is in a position to service them
        ListIterator i = requestList.listIterator();
        while (i.hasNext()) {
            CallButton callBtn;
            callBtn = (CallButton) i.next();
            // Add the floor to the elevator and remove from the request list
            // if possible, otherwise catch the error to prevent a crash
            try {
                e.addServiceRequest(callBtn);
                i.remove();
            } catch (IllegalArgumentException ex) {
                // crash prevention - i.remove() fails if exception is thrown
                // above so list state is maintained.
            }
        }
    }

    /**
     * Check if floorID should be added to an elevator's destination list.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param floorID is the integer representing the floor being checked
     * @param currentFloor is the elevator's current location
     * @return true if the elevator is going up and the floor is above it, or
     * if the elevator is going down and the floor is below it
     */
    private boolean isValidFloor(CallButton callBtn, Elevator e)
    {
        // Compare call button direction to elevator direction and add if matching
        if (direction == Direction.UP && e.getDirection() == Direction.UP)
        {
            return callBtn.getFloorID() > e.getCurrentFloor();
        } else if (direction == Direction.DOWN && e.getDirection() == Direction.DOWN) {
            return callBtn.getFloorID() < e.getCurrentFloor();
        } else if (e.getDirection() == Direction.NULL) {
            return true;
        }
        return false;
    }
    
    /**
     * Add a floor to the destination list.<br>
     * 
     * PRE: The floorID does not already have an active call button for this direction<br>
     * POST: The floorID is added to the list of floors requesting service<br>
     * Cleanup: N/A<br>
     * 
     * @param callBtn is the call button requesting service
     */
    public void addDestination(CallButton callBtn)
    {
        System.out.println("Adding Call Button: "+callBtn.getFloorID());
        requestList.add(callBtn);
        callBtn.activate();
    }
}