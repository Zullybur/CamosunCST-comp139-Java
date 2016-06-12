/*
 */
package lab07;

import java.awt.Toolkit;

/**
 * An elevator object that moves between floors and maintains a destination list
 * of what floors it needs to service.
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class Elevator
{
    private static final int MAX_FLRS = 999;
    private static final int MIN_FLRS = 2;
    private static final int MIN_SUB_FLRS = 0;    
    private boolean hasThirteen, hasZero, hasArrived;
    private int elevatorID, shaftID, currentFloor, destination, topFloor, bottomFloor;
    private int offset;             // Offset is used to account for sub-floors when returning values
    private static int count = 0;   // ID's to track multiple elevators in larger system
    private Door innerDoor;
    private FloorButton[] floorButtons;
    private DestinationList destinationList;
    private Direction direction;
    
    /**
     * Instantiate an elevator object with only above-ground floors.<br>
     * 
     * Pre: A system exists to control the elevator<br>
     * Post: An elevator object was created and set to be on the first floor<br>
     * Cleanup: N/A<br>
     * 
     * @param numFloors is a positive number greater than one that is the number of above-ground floors the elevator can access
     * @param shaftID is the shaft in which the elevator operates, or 0 in a single elevator system
     * @param hasZero is true if the first floor is floor 0, or false if the first floor is floor 1
     * @param hasThirteen is True if the system has a 13th floor, otherwise False
     */
    public Elevator(int numFloors, int shaftID, boolean hasZero, boolean hasThirteen) throws IllegalArgumentException
    {
        if (MIN_FLRS > numFloors || numFloors > MAX_FLRS)
        {
            throw new IllegalArgumentException();
        }
        direction = Direction.NULL;
        this.hasZero = hasZero;
        this.hasThirteen = hasThirteen;
        elevatorID = getNextID();
        currentFloor = (hasZero ? 0 : 1) + offset;
        this.shaftID = shaftID;
        this.topFloor = numFloors + (hasZero ? -1 : 0) + (hasThirteen ? 0 : 1);
        this.offset = 0;
        bottomFloor = (hasZero ? 0 : 1);
        innerDoor = new Door();
        destinationList = new DestinationList(numFloors);
        destination = bottomFloor - 1;
        try
        {
            setFloorButtons();
        } catch (IllegalStateException ex) {
            System.out.println("Illegal state reached when created elevator! Floors not instantiated.");
            System.out.println(ex);
        }
    }
    
    /**
     * Instantiate an elevator object with above and below ground floors.<br>
     * 
     * Pre: A system exists to control the elevator<br>
     * Post: An elevator object was created and set to be on the first floor<br>
     * Cleanup: N/A<br>
     * 
     * @param numFloors is a positive number greater than one that is the number of above-ground floors the elevator can access
     * @param shaftID is the shaft in which the elevator operates, or 0 in a single elevator system
     * @param numSubFloors is a number greater than or equal to zero that is the number of below-ground floors the elevator can access
     * @param hasZero hasZero is true if the first floor is floor 0, or false if the first floor is floor 1
     * @param hasThirteen is True if the system has a 13th floor, otherwise False
     */
    public Elevator(int numFloors, int numSubFloors, int shaftID, boolean hasZero, boolean hasThirteen) throws IllegalArgumentException
    {
        if (MIN_FLRS > numFloors || MIN_SUB_FLRS > numSubFloors ||
            numFloors > MAX_FLRS || numSubFloors > MAX_FLRS)
        {
            throw new IllegalArgumentException();
        }
        direction = Direction.NULL;
        this.hasZero = hasZero;
        this.hasThirteen = hasThirteen;
        elevatorID = getNextID();
        currentFloor = 1 + offset;
        this.shaftID = shaftID;
        this.topFloor = numFloors + (hasZero ? -1 : 0) + (hasThirteen ? 0 : 1);
        this.offset = numSubFloors;
        bottomFloor = -offset;
        innerDoor = new Door();
        destinationList = new DestinationList(numFloors + numSubFloors);
        try
        {
            setFloorButtons();
        } catch (IllegalStateException ex) {
            System.out.println("Illegal state reached when creating elevator! Floors not instantiated.");
            System.out.println(ex);
        }
    }
    
    /**
     * Create an array of FloorButton objects representing the
     * floors the elevator has access to.
     */
    private void setFloorButtons()
    {
        int tmp = topFloor + offset;
        // Add one to account for ignoring floor zero
        floorButtons = new FloorButton[tmp + 1];
        for (int i = 0, j = offset; i <= tmp; i++)
        {
            // Skip null floors
            if ((!hasZero && i - offset == 0) || (!hasThirteen && i - offset == 13)) continue;
            // Generate labels for each floor for use in GUI applications, where
            // the pre-fix letter indicates an above-ground (F)loor or (B)asement floor
            String name;
            if (j > 0)
            {
                if (j < 10)         name = "B:00";
                else if (j < 100)   name = "B:0";
                else                name = "B:";
                floorButtons[i] = new FloorButton(-j, name + j);
                j--;
            } else {
                if (i < 10)         name = "F:00";
                else if (i < 100)   name = "F:0";
                else                name = "F:";
                floorButtons[i] = new FloorButton(i-offset, name + (i-offset));
            }
        }
    }
    
    /**
     * Increment elevator count and return an ID for an elevator.
     */
    private int getNextID()
    {
        return (++count) + 100;
    }
    
    /**
     * Get the state of the button for a specific floor.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param floor is the reference for which button to check
     * @return true if the button is active, false if the button is inactive
     */
    public boolean getButtonState(int floor)
    {
        return floorButtons[floor].getButtonState();
    }
    
    /**
     * Get the state of the light for a button for a specific floor.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param floor is the reference for which button to check
     * @return true if the light is active, false if the light is inactive
     */
    public boolean getButtonLightState(int floor)
    {
        return floorButtons[floor].getLightState();
    }
    
    /**
     * Get the state of the elevator's door.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return true if the door is open, false if the door is closed
     */
    public boolean getDoorState()
    {
        return innerDoor.isOpen();
    }
    
    /**
     * Get the current number of elevators created.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return the current number of elevators
     */
    public int getElevatorCount()
    {
        return count;
    }
    
    /**
     * Get the ID of the elevator object.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return the elevator ID as an integer
     */
    public int getElevatorID()
    {
        return elevatorID;
    }
    
    /**
     * Get the shaft ID in which the elevator operates.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return the ID number for the shaft the elevator was assigned to
     */
    public int getShaftID()
    {
        return shaftID;
    }
    
    /**
     * Get the current floor the elevator is on.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return the current floor as an integer
     */
    public int getCurrentFloor()
    {
        return currentFloor;
    }
    
    /**
     * Get the top floor the elevator has access to.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return the top floor as an integer
     */
    public int getTopFloor()
    {
        return topFloor;
    }
    
    /**
     * Get the lowest floor the elevator has access to.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return the lowest floor as an integer (first floor is 1, basements are
     * reverse indexed starting at -1 and descending, floor zero never exists)
     */
    public int getBottomFloor()
    {
        return bottomFloor;
    }
    
    /**
     * Get the current destination for the elevator.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
      * @return the next location the elevator will stop at given it's current state, as an integer
      */
    public int getDestination()
    {
        return destination;
    }
    
    /**
     * Get the current direction the elevator is traveling in.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return 1 if the elevator is moving up, 0 if it is stationary, and -1 if it is moving down
     */
    public Direction getDirection()
    {
        return direction;
    }
    
    /**
     * Check if the elevator has arrived at the set destination.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return true if the elevator has just arrived, false if not
     */
    public boolean isArrived()
    {
        return hasArrived;
    }
    
    /**
     * Adds a floor number to the destination list for this elevator.<br>
     * 
     * PRE: The request was generated by a floor button<br>
     * POST: The floor is added to the elevator's list of destinations, or the 
     * elevator signals it's arrival if the floor is the current floor, the
     * floor button is activated and the button lit<br>
     * Cleanup: N/A<br>
     * 
     * @param floorID is a floor that is accessible by the elevator
     * @throws IllegalArgumentException if the floorID is invalid
     */
    public void selectFloor(int floorID) throws IllegalArgumentException
    {
        innerDoor.close();
        if (floorID > topFloor || bottomFloor > floorID)
        {
            throw new IllegalArgumentException();
        }
        floorButtons[floorID].activate();
        // Signal arrived if the current floor is selected
        if (floorID == currentFloor)
        {
            arrived();
        // If the elevator has no destination, set the floorID as the destination
        } else if (destination == bottomFloor - 1) {
            destination = floorID;
        // If the selected floor should be the next destination, put the current
        // destination back in the destination list and use the selected floor
        // as the new next destination
        } else if (direction == Direction.UP && floorID > currentFloor && floorID < destination) {
            destinationList.addDestination(destination, currentFloor, Direction.NULL);
            destination = floorID;
        } else if (direction == Direction.DOWN && floorID < currentFloor && floorID > destination) {
            destinationList.addDestination(destination, currentFloor, Direction.NULL);
            destination = floorID;
        } else {
            destinationList.addDestination(floorID, currentFloor, Direction.NULL);
        }
        // If the elevator is not moving, start it in the direction of the latest request
        if (direction == Direction.NULL)
        {
            direction = (destination > currentFloor ? Direction.UP : Direction.DOWN);
        }
    }
    
    /**
     * Adds a floor number to the destination list for this elevator.<br>
     * 
     * PRE: The request was generated by a call button<br>
     * POST: The floor is added to the elevator's list of destinations, or the 
     * elevator signals it's arrival if the floor is the current floor<br
     * Cleanup: N/A<br>
     * 
     * @param callBtn is the call button making the elevator service request
     */
    public void addServiceRequest(CallButton callBtn) throws IllegalArgumentException
    {
        int floorID = callBtn.getFloorID();
        Direction btnDirection = callBtn.getDirection();
        
        if (floorID > topFloor || floorID < bottomFloor)
        {
            throw new IllegalArgumentException();
        }
        
        // Signal arrived if the current floor is selected
        if (floorID == currentFloor)
        {
            arrived();
        // If the elevator has no destination, set the floorID as the destination
        } else if (destination < bottomFloor) {
            destination = floorID;
            direction = (destination > currentFloor ? Direction.UP : Direction.DOWN);
        // If the selected floor should be the next destination, put the current
        // destination back in the destination list and use the selected floor
        // as the new next destination
        } else if (this.direction == Direction.UP && floorID > currentFloor && floorID < destination) {
            destinationList.addDestination(destination, currentFloor, Direction.NULL);
            destination = floorID;
        } else if (this.direction == Direction.DOWN && floorID < currentFloor && floorID > destination) {
            destinationList.addDestination(destination, currentFloor, Direction.NULL);
            destination = floorID;
        } else {
            destinationList.addDestination(floorID, currentFloor, btnDirection);
        }
    }
    
    /**
     * Conduct a single time-step event for an elevator.<br>
     * 
     * PRE: N/A<br>
     * POST: The elevator moves as required and the state is updated to represent
     * any required changes<br>
     * Cleanup: N/A<br>
     */
    public void tick() throws IllegalStateException
    {
        hasArrived = false;
        // Close door before moving, if open
        if (innerDoor.isOpen()) innerDoor.close();
        
        // Move in the current direction for the elevator
        switch (direction) {
            case NULL:
                break;
            case UP:
                incrementFloor();
                System.out.println("Moved to: "+currentFloor);
                break;
            case DOWN:
                decrementFloor();
                System.out.println("Moved to: "+currentFloor);
                break;
            default:
                throw new IllegalStateException();
        }
        // Check if elevator is at next destination
        if (currentFloor == destination)
        {
            System.out.println("arriving at " + destination);
            arrived();
        }
        
        // Update direction for next destination
        if (destination < bottomFloor)
        {
            direction = Direction.NULL;
        } else if (destination > currentFloor) {
            System.out.println("Setting direction: Up");
            direction = Direction.UP;
        } else if (destination < currentFloor) {
            System.out.println("Setting direction: Down");
            direction = Direction.DOWN;
        } else {
            throw new IllegalStateException();
        }
    }
    
    /**
     * Move the elevator up one floor.
     * 
     * PRE: The elevator is not on the top floor
     * POST: The elevator ascends one floor
     * 
     * @throws IllegalStateException if the elevator tries to ascend above the highest floor
     */
    private void incrementFloor() throws IllegalStateException
    {
        int tmpFloor = currentFloor;
        currentFloor++;
        // Move past null floors
        if ((!hasZero && currentFloor == 0) || (!hasThirteen && currentFloor == 13))
        {
            currentFloor++;
        }
        // Prevent crashing in to the top of the shaft
        if (currentFloor > topFloor)
        {
            currentFloor = tmpFloor;
            throw new IllegalStateException();
        }
    }
    
    /**
     * Move the elevator down one floor.
     * 
     * PRE: The elevator is not on the bottom floor
     * POST: The elevator descends one floor
     * 
     * @throws IllegalStateException if the elevator tries to descend below the lowest floor
     */
    private void decrementFloor() throws IllegalStateException
    {
        int tmpFloor = currentFloor;
        currentFloor--;
        // Move past null floors
        if ((!hasZero && currentFloor == 0) || (!hasThirteen && currentFloor == 13))
        {
            currentFloor--;
        }
        // Prevent crashing in to the bottom of the shaft
        if (currentFloor < -offset)
        {
            currentFloor = tmpFloor;
            throw new IllegalStateException();
        }
    }
    
    /**
     * Play a chime noise and output a chime message to console.
     */
    private void playChime()
    {
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Elevator " + elevatorID + " chimed!");
    }

    /**
     * Adjust the elevator state to account for arriving at the given destination
     */
    private void arrived()
    {
        if(!hasArrived) {
            floorButtons[currentFloor].deactivate();
            innerDoor.open();
            playChime();
            hasArrived = true;
            do {
                destination = destinationList.getNextDestination(bottomFloor, direction).floorID;
            } while (destination == currentFloor);
        }
    }
    
    /**
     * Get the string representation of the elevator's state.
     * 
     * PRE: N/A
     * POST: N/A
     * Cleanup: N/A
     * 
     * @return the elevator's state as a string
     */
    @Override
    public String toString()
    {
        return "Elvator State:"+
                "\n\tID:\t"+elevatorID+
                "\n\tLOC:\t"+currentFloor+
                "\n\tDEST:\t"+destination;
    }
}