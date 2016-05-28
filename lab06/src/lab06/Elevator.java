/*
 */
package lab06;

/**
 * An elevator object that moves between floors and maintains a destination list
 * of what floors it needs to service.
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class Elevator
{
    private final String UP = "UP";
    private final String DOWN = "DOWN";
    private static final int MAX_FLRS = 999;
    private static final int MIN_FLRS = 2;
    private static final int MIN_SUB_FLRS = 0;    
    private boolean hasThirteen, hasArrived;
    private int elevatorID, shaftID, currentFloor, destination, topFloor, bottomFloor;
    private int offset;             // Offset is used to account for sub-floors when returning values
    private static int count = 0;   // ID's to track multiple elevators in larger system
    private Door innerDoor;
    private FloorButton[] floorButtons;
    private DestinationList destinationList;
    /***********************
     * Track motion state: *
     * -1  : moving down   *
     *  0  : stationary    *
     *  1  : moving up     *
     ***********************/
    private int motion;
    
    /**
     * Instantiate an elevator object with only above-ground floors.
     * Pre: A system exists to control the elevator
     * Post: An elevator object was created and set to be on the first floor
     * @param numFloors is a positive number greater than one that is the number of above-ground floors the elevator can access
     * @param shaftID is the shaft in which the elevator operates, or 0 in a single elevator system
     * @param hasThirteen is True if the system has a 13th floor, otherwise False
     */
    public Elevator(int numFloors, int shaftID, boolean hasThirteen) throws IllegalArgumentException
    {
        if (MIN_FLRS > numFloors || numFloors > MAX_FLRS)
        {
            throw new IllegalArgumentException();
        }
        motion = 0;
        this.hasThirteen = hasThirteen;
        elevatorID = getNextID();
        currentFloor = 1 + offset;
        this.shaftID = shaftID;
        this.topFloor = hasThirteen ? numFloors : (numFloors + 1);
        this.offset = 0;
        bottomFloor = 1;
        innerDoor = new Door();
        destinationList = new DestinationList(numFloors);
        try
        {
            setFloorButtons();
        } catch (IllegalStateException ex) {
            System.out.println("Illegal state reached when created elevator! Floors not instantiated.");
            System.out.println(ex);
        }
    }
    
    /**
     * Instantiate an elevator object with above and below ground floors.
     * Pre: A system exists to control the elevator
     * Post: An elevator object was created and set to be on the first floor
     * @param numFloors is a positive number greater than one that is the number of above-ground floors the elevator can access
     * @param shaftID is the shaft in which the elevator operates, or 0 in a single elevator system
     * @param numSubFloors is a number greater than or equal to zero that is the number of below-ground floors the elevator can access
     * @param hasThirteen is True if the system has a 13th floor, otherwise False
     */
    public Elevator(int numFloors, int shaftID, int numSubFloors, boolean hasThirteen) throws IllegalArgumentException
    {
        if (MIN_FLRS > numFloors || MIN_SUB_FLRS > numSubFloors ||
            numFloors > MAX_FLRS || numSubFloors > MAX_FLRS)
        {
            throw new IllegalArgumentException();
        }
        motion = 0;
        this.hasThirteen = hasThirteen;
        elevatorID = getNextID();
        currentFloor = 1 + offset;
        this.shaftID = shaftID;
        this.topFloor = (!hasThirteen && numFloors > 12) ? (numFloors + 1) : numFloors;
        this.offset = numSubFloors;
        bottomFloor = -offset;
        innerDoor = new Door();
        destinationList = new DestinationList(numFloors + numSubFloors);
        try
        {
            setFloorButtons();
        } catch (IllegalStateException ex) {
            System.out.println("Illegal state reached when created elevator! Floors not instantiated.");
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
            // Skip floor zero (and thirteen if required)
            if ((i - offset == 0) || (!hasThirteen && i == 13)) continue;
            // Generate labels for each floor for use in GUI applications
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
     * Increment elevator count and return an ID for an elevator
     */
    private int getNextID()
    {
        return (++count) + 100;
    }
    
    /**
     * 
     * @param floor
     * @return 
     */
    public boolean getButtonState(int floor)
    {
        return floorButtons[floor].getButtonState();
    }
    
    /**
     * 
     * @param floor
     * @return 
     */
    public boolean getButtonLightState(int floor)
    {
        return floorButtons[floor].getLightState();
    }
    
    /**
     * 
     * @return 
     */
    public boolean getDoorState()
    {
        return innerDoor.isOpen();
    }
    
    /**
     * Get the current number of elevators created.
     * @return the current number of elevators
     */
    public int getElevatorCount()
    {
        return count;
    }
    
    /**
     * Get the ID of the elevator object.
     * @return the elevator ID as an integer
     */
    public int getElevatorID()
    {
        return elevatorID;
    }
    
    /**
     * 
     * @return 
     */
    public int getShaftID()
    {
        return shaftID;
    }
    
    /**
     * Get the current floor the elevator is on.
     * @return the current floor as an integer
     */
    public int getCurrentFloor()
    {
        return currentFloor;
    }
    
    /**
     * Get the top floor the elevator has access to.
     * @return the top floor as an integer
     */
    public int getTopFloor()
    {
        return topFloor;
    }
    
    /**
     * Get the lowest floor the elevator has access to.
     * @return the lowest floor as an integer (first floor is 1, basements are
     * reverse indexed starting at -1 and descending, floor zero never exists)
     */
    public int getBottomFloor()
    {
        return bottomFloor;
    }
    
     /**
      * Get the current destination for the elevator.
      * @return the next location the elevator will stop at given it's current state, as an integer
      */
    public int getDestination()
    {
        return destination;
    }
    
    /**
     * Get the current direction the elevator is traveling in.
     * @return 1 if the elevator is moving up, 0 if it is stationary, and -1 if it is moving down
     */
    public int getDirection()
    {
        return motion;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isArrived()
    {
        return hasArrived;
    }
    
    /**
     * Adds a floor number to the destination list for this elevator.
     * PRE: The request was generated by a floor button
     * POST: The floor is added to the elevator's list of destinations, or the 
     * elevator signals it's arrival if the floor is the current floor, the
     * floor button is activated and the button lit.
     * @param floorID is a floor that is accessible by the elevator
     */
    public void selectFloor(int floorID) throws IllegalArgumentException
    {
        // Close elevator doors once user selects a floor
        innerDoor.close();
//      DEBUG:  System.out.println("Top floor: " + topFloor);
        if (floorID > topFloor || floorID < bottomFloor)
        {
            throw new IllegalArgumentException();
        }
        floorButtons[floorID].activate();
        // Signal arrived if the current floor is selected
        if (floorID == currentFloor)
        {
            arrived();
        // If the elevator has no destination, set the floorID as the destination
        } else if (destination == 0) {
            destination = floorID;
        // If the selected floor should be the next destination, put the current
        // destination back in the destination list and use the selected floor
        // as the new next destination
        } else if (motion == 1 && floorID > currentFloor && floorID < destination) {
            destinationList.addDestination(destination, currentFloor, null);
            destination = floorID;
        } else if (motion == -1 && floorID < currentFloor && floorID > destination) {
            destinationList.addDestination(destination, currentFloor, null);
            destination = floorID;
        } else {
            destinationList.addDestination(floorID, currentFloor, null);
        }
        // If the elevator is not moving, start it in the direction of the latest request
        if (motion == 0 && destination != 0)
        {
            motion = (destination > currentFloor ? 1 : -1);
        }
    }
    
    /**
     * Adds a floor number to the destination list for this elevator.
     * PRE: The request was generated by a call button
     * POST: The floor is added to the elevator's list of destinations, or the 
     * elevator signals it's arrival if the floor is the current floor
     * @param callBtn is the call button making the elevator service request
     */
    public void addServiceRequest(CallButton callBtn) throws IllegalArgumentException
    {
        int floorID = callBtn.getFloorID();
        String direction = callBtn.getDirection();
        
        if (floorID > topFloor || floorID < bottomFloor)
        {
            throw new IllegalArgumentException();
        }
        // Check if call should be new destination
        
        
        // Signal arrived if the current floor is selected
        if (floorID == currentFloor)
        {
            arrived();
        // If the elevator has no destination, set the floorID as the destination
        } else if (destination == 0) {
            destination = floorID;
        // If the selected floor should be the next destination, put the current
        // destination back in the destination list and use the selected floor
        // as the new next destination
        } else if (motion == 1 && floorID > currentFloor && floorID < destination && direction.equals(UP)) {
            destinationList.addDestination(destination, currentFloor, "null");
            destination = floorID;
        } else if (motion == -1 && floorID < currentFloor && floorID > destination && direction.equals(DOWN)) {
            destinationList.addDestination(destination, currentFloor, "null");
            destination = floorID;
        } else {
            destinationList.addDestination(floorID, currentFloor, direction);
        }
    }
    
    /**
     * Conduct a single time event for an elevator.
     * Completes elevator motion if movement is warranted, and updates
     * the state based on the new location.
     */
    public void tick() throws IllegalStateException
    {
        /****************** Testing ******************
        System.out.println("***** Elevator ("+elevatorID+","+shaftID+") Pre Tick *****");
        System.out.println("Arrival: "+hasArrived);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        /********************************************/
        hasArrived = false;
        // Close door before moving, if open
        if (innerDoor.isOpen()) innerDoor.close();
        
        // Move in the current direction for the elevator
        switch (motion) {
            case 0:
                break;
            case 1:
                incrementFloor();
                break;
            case -1:
                decrementFloor();
                break;
            default:
                throw new IllegalStateException();
        }
        // Check if elevator is at next destination
        if (currentFloor == destination)
        {
            arrived();
        }
        // Update motion for next destination
        if (destination == 0)
        {
            motion = 0;
        } else if (destination > currentFloor) {
            motion = 1;
        } else if (destination < currentFloor) {
            motion = -1;
        } else {
            throw new IllegalStateException();
        }
    }
    
    /**
     * Move the elevator up one floor.
     * PRE: The elevator is not on the top floor
     * POST: The elevator ascends one floor
     * @throws IllegalStateException if the elevator tries to ascend above the highest floor
     */
    private void incrementFloor() throws IllegalStateException
    {
        int tmpFloor = currentFloor;
        currentFloor++;
        // Move past null floors
        if (currentFloor == 0 || (!hasThirteen && currentFloor == 13))
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
     * PRE: The elevator is not on the bottom floor
     * POST: The elevator descends one floor
     * @throws IllegalStateException if the elevator tries to descend below the lowest floor
     */
    private void decrementFloor() throws IllegalStateException
    {
        int tmpFloor = currentFloor;
        currentFloor--;
        // Move past null floors
        if (currentFloor == 0 || (!hasThirteen && currentFloor == 13))
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
     * Play a chime noise if supported, otherwise output a chime message to screen.
     * PRE: The elevator was not at the destination on the previous tick
     * POST: A chime was played or a notice was output to screen
     */
    private void playChime()
    {
        System.out.println("Elevator: " + elevatorID + " played it's chime!"); 
    }

    private void arrived() {
        if(!hasArrived) {
            floorButtons[currentFloor].deactivate();
            innerDoor.open();
            playChime();
            hasArrived = true;
            destination = destinationList.getNextDestination(motion).floorID;
        }
    }
}