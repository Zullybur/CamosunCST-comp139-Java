/*
 */
package lab06;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class CallRequestList
{
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    private LinkedList<Integer> destinationList;
    private String direction;
    
    /**
     * Instantiate a Call Request List to hold a list of waiting elevator calls
     * that want to move in a specified direction.
     * @param direction is "UP" if the list services up call buttons, otherwise "DOWN"
     */
    public CallRequestList(String direction) throws IllegalArgumentException
    {
        if (direction.equals(DOWN) || direction.equals(UP))
        {
            this.direction = direction;
        } else {
            throw new IllegalArgumentException();
        }
        destinationList = new LinkedList<>();
    }
    
    /**
     * Assign valid destinations to a given elevator.
     * PRE: The elevator is moving in the same direction as the Call Request List
     * is serving, or the elevator is stationary
     * POST: The elevator is assigned all valid destinations given its current state
     * @param e is the elevator being assigned new destinations
     */
    public void getDestinations(Elevator e)
    {
        ListIterator i = destinationList.listIterator();
        while (i.hasNext()) {
            int floorID = (int)i.next();
            if (isValidFloor(floorID, e.getCurrentFloor()))
            {
                try {
                    e.addFloor(floorID);
                    i.remove();
                } catch (IllegalArgumentException ex) {
                    // crash prevention - i.remove() fails if exception is thrown
                    // above so list state is maintained.
                }
            }
        }
    }
    
    /**
     * Check if floorID should be added to an elevator's destination list.
     * @param floorID is the integer representing the floor being checked
     * @param currentFloor is the elevator's current location
     * @return true if the elevator is going up and the floor is above it, or
     * if the elevator is going down and the floor is below it
     */
    private boolean isValidFloor(int floorID, int currentFloor)
    {
        if (direction.equals(UP))
        {
            return floorID > currentFloor;
        } else {
            return floorID < currentFloor;
        }
    }
    
    /**
     * Add a floor to the destination list.
     * PRE: The floorID does not already have an active call button for this direction
     * POST: The floorID is added to the list of floors requesting service
     * @param floorID is the ID of the floor to add.
     */
    public void addDestination(int floorID)
    {
        destinationList.add(floorID);
    }
}