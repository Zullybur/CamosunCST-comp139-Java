/*
 */
package lab06;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class DestinationList
{
    private PriorityQueue<Integer> upwardDestinations;
    private PriorityQueue<Integer> downwardDestinations;
    
    /**
     * 
     * @param numFloors
     */
    public DestinationList(int numFloors)
    {
        // Set the heap for upward progressing floors to a MIN heap
        upwardDestinations = new PriorityQueue<>(numFloors);
        // Set the heap for downward progressing floors to a MAX heap
        downwardDestinations = new PriorityQueue<>(numFloors, Collections.reverseOrder());
    }
    
    /**
     * 
     * @param floorID
     * @param location 
     */
    public void addDestination(int floorID, int location) throws IllegalArgumentException
    {
        if (floorID > location)
        {
            upwardDestinations.add(floorID);
        } else if (floorID < location) {
            downwardDestinations.add(floorID);
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * 
     * @param motion
     * @return 
     */
    public int getNextDestination(int motion)
    {
        if (motion == 1)
        {
            if (upwardDestinations.peek() != null)
            {
                return upwardDestinations.poll();
            } else if (downwardDestinations.peek() != null) {
                return downwardDestinations.poll();
            }
        } else if (motion == -1) {
            if (downwardDestinations.peek() != null)
            {
                return downwardDestinations.poll();
            } else if (upwardDestinations.peek() != null) {
                return upwardDestinations.poll();
            }
        }
        return 0;
    }
}