/*
 */
package lab07;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class DestinationList
{
    /**
     * A destination object contains a floor id and the requested direction from
     * a call button OR elevator button service request, direction is the string null
     * for elevator buttons.
     */
    protected class Destination {
        int floorID;
        Direction direction;
        /**
         * Create a destination object to represent a floor requested and direction to travel in.<br>
         * 
         * PRE: N/A<br>
         * POST: A destination object is created<br>
         * Cleanup: N/A<br>
         * 
         * @param floorID is the ID of the floor to service
         * @param direction is "UP" or "DOWN" for a call button and "null" for an elevator button
         * @throws IllegalArgumentException if direction is invalid or floor ID is out of range for the system
         */
        private Destination(int floorID, Direction direction) throws IllegalArgumentException
        {
            if (direction == Direction.UP || direction == Direction.DOWN || direction == Direction.NULL)
            {
                this.direction = direction;
            } else {
                throw new IllegalArgumentException();
            }
            this.floorID = floorID;
        }
    }
    private PriorityQueue<Destination> upwardDestinations;
    private PriorityQueue<Destination> downwardDestinations;
    
    /**
     * Instantiate a destination list to track floors assigned to an elevator for servicing.<br>
     * 
     * PRE: N/A<br>
     * POST: A destination list was created<br>
     * Cleanup: N/A<br>
     * 
     * @param numFloors is the number of floors the elevator has access to
     */
    public DestinationList(int numFloors)
    {
        // Set the heap for upward progressing floors to a MIN heap
        upwardDestinations = new PriorityQueue<>(numFloors);
        // Set the heap for downward progressing floors to a MAX heap
        downwardDestinations = new PriorityQueue<>(numFloors, Collections.reverseOrder());
    }
    
    /**
     * Add a destination to the destination list.<br>
     * 
     * PRE: The elevator is not already at the destination floor ID<br>
     * POST: A destination was added to the list<br>
     * Cleanup: N/A<br>
     * 
     * @param floorID is the destination ID that requires servicing
     * @param location is the current location of the elevator
     * @param direction is the direction the destination wants to go, or null for an
     * internal elevator button
     * @throws IllegalArgumentException if floorID = location
     */
    public void addDestination(int floorID, int location, Direction direction) throws IllegalArgumentException
    {
        if (floorID > location)
        {
            upwardDestinations.add(new Destination(floorID, direction));
        } else if (floorID < location) {
            downwardDestinations.add(new Destination(floorID, direction));
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Get the next destination the elevator should service.<br>
     * 
     * PRE: N/A<br>
     * POST: A destination was removed from the destination list, if there is no<br>
     * new destination, the destination is set to one floor lower than the lowest floor
     * Cleanup: N/A<br>
     * 
     * @param bottomFloor is the lowest floor the elevator can reach
     * @param direction is the direction the elevator is moving
     * @return the next destination the elevator should service
     * @throws IllegalArgumentException if direction is not 1 or -1
     */
    public Destination getNextDestination(int bottomFloor, Direction direction)
    {
        
        if (direction == Direction.UP)
        {
            if (upwardDestinations.peek() != null)
            {
                return upwardDestinations.poll();
            } else if (downwardDestinations.peek() != null) {
                return downwardDestinations.poll();
            }
        } else if (direction == Direction.DOWN) {
            if (downwardDestinations.peek() != null)
            {
                return downwardDestinations.poll();
            } else if (upwardDestinations.peek() != null) {
                return upwardDestinations.poll();
            }
        }
        return new Destination(bottomFloor - 1, Direction.NULL);
    }
}