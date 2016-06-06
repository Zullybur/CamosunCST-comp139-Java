/*
 */
package lab07;

import java.util.Stack;

/**
 * An elevator system controller.
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class ElevatorSystem {
    public static final int MAX_FLRS = 999;
    public static final int MIN_FLRS = 2;
    public static final int MIN_SUB_FLRS = 0;
    private boolean hasThirteen, hasZero;
    private int topFloor, bottomFloor, numShafts;
    private int offset;             // Offset is used to account for sub-floors when returning values
    private CallRequestList upRequestList;
    private CallRequestList downRequestList;
    private Door[][] outerDoors;
    private Stack<Door> openDoors;
    private CallButton[] upButtonList;
    private CallButton[] downButtonList;
    private Elevator[] elevators;
    
    /**
     * Instantiate an elevator system with a given number of floors and elevator shafts.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param numFloors is the number of above-ground floors the elevator services
     * @param numSubFloors is the number of below-ground floors the elevator services
     * @param hasZero is true if the floors are zero indexed, false if there is no floor zero
     * @param hasThirteen indicates whether or not there is a 13th floor
     * @param numShafts is the number of separate elevator shafts in the system
     * @throws IllegalArgumentException if numFloors or numSubFloors is out of the ranges:<br>
     * (MIN_FLRS <= numFloors <= MAX_FLRS) and (MIN_SUB_FLRS <= numSubFloors <= MAX_FLOORS)
     */
    public ElevatorSystem(int numFloors, int numSubFloors, boolean hasZero, boolean hasThirteen, int numShafts)
            throws IllegalArgumentException
    {
        if (MIN_FLRS > numFloors || MIN_SUB_FLRS > numSubFloors ||
            numFloors > MAX_FLRS || numSubFloors > MAX_FLRS)
        {
            throw new IllegalArgumentException();
        }
        this.hasZero = hasZero;
        this.hasThirteen = hasThirteen;
        this.numShafts = numShafts;
        // Add a floor if skipping thirteen unless floor count is under 13
        this.topFloor = numFloors + (hasZero ? 0 : -1) + (hasThirteen ? 0 : 1);
        this.offset = numSubFloors;
        this.bottomFloor = (offset != 0) ? -offset : (hasZero ? 0 : 1);
        upRequestList = new CallRequestList(Direction.UP);
        downRequestList = new CallRequestList(Direction.DOWN);
        // Add one to account for ignoring floor zero
        upButtonList = new CallButton[topFloor + offset + 1];
        downButtonList = new CallButton[topFloor + offset + 1];
        outerDoors = new Door[topFloor + offset + 1][numShafts];
//        setCallButtons(upButtonList, Direction.UP);
//        setCallButtons(downButtonList, Direction.DOWN);
//        createDoors();
        createFloors();
        createElevators(numFloors, numSubFloors);
        openDoors = new Stack<>();
    }
    
    private void createFloors()
    {
        int tmp = topFloor + offset;
        for (int i = 0, j = offset; i <= tmp; i++)
        {
            // Skip floor zero (and thirteen if required)
            if ((!hasZero && i - offset == 0) || (!hasThirteen && i - offset == 13)) continue;
            
            // Generate labels for each floor for use in GUI applications
            String name;
            // Create subfloors
            if (j > 0)
            {
                if (j < 10)         name = "B:00";
                else if (j < 100)   name = "B:0";
                else                name = "B:";
                // Create up button
                upButtonList[i] = new CallButton(-j, name + j, Direction.UP);
                // Create down buttons
                downButtonList[i] = new CallButton(-j, name + j, Direction.DOWN);
                // Create outer doors for each shaft
                for (int s = 0; s < numShafts; s++)
                {
                    outerDoors[i][s] = new Door(-j, name, s);
                }
                // Create Elevator
                j--;
            // Create main floors
            } else {
                if (i < 10)         name = "F:00";
                else if (i < 100)   name = "F:0";
                else                name = "F:";
                // Create up bottons
                upButtonList[i] = new CallButton(i - offset, name + i, Direction.UP);
                // Create down buttons
                downButtonList[i] = new CallButton(i-offset, name + (i-offset), Direction.DOWN);
                
                for (int s = 0; s < numShafts; s++)
                {
                    outerDoors[i][s] = new Door(-j, name, s);
                }
            }
        }
    }
    
    /**
     * Create elevator objects for each shaft
     */
    private void createElevators(int numFloors, int numSubFloors)
    {
        elevators = new Elevator[numShafts];
        
        for (int s = 0; s < numShafts; s++)
        {
            elevators[s] =  new Elevator(numFloors, numSubFloors, hasZero, hasThirteen);
        }
    }
    
    /**
     * Get the state of the door for a given floor -> shaft combination.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param floor is the floor the door is on
     * @param shaft is the shaft the door belongs to
     * @return true if the door is open, false if the door is closed
     */
    public boolean isFloorDoorOpen(int floor, int shaft) throws IllegalArgumentException
    {
        if (bottomFloor > floor || floor > topFloor ||
            shaft < 0 || shaft > elevators.length - 1)
        {
            throw new IllegalArgumentException();
        }
        return outerDoors[floor][shaft].isOpen();
    }
    
    /**
     * Get the state of a given call button.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param floor is the floor the button is on
     * @param direction indicates if you are checking the UP button or the DOWN button
     * @return true if the button is active, false if the button is inactive
     */
    public boolean isCallButtonActive(int floor, Direction direction)
    {
        return (direction == Direction.UP ? upButtonList[floor].getButtonState() : downButtonList[floor].getButtonState());
    }
    
    /**
     * Get the state of a given call button's light.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param floor is the floor the button is on
     * @param direction indicates if you are checking the UP button or the DOWN button
     * @return true if the light is on, false if the light is off
     */
    public boolean isCallButtonLightLit(int floor, Direction direction)
    {
        return (direction == Direction.UP ? upButtonList[floor].getLightState() : downButtonList[floor].getLightState());
    }
    
    /**
     * Get the state of a given elevator's given floor button.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param floor is the button in the elevator to check
     * @param shaft is the shaft the elevator to check is in
     * @return true if the button is active, false if the button is inactive
     */
    public boolean isElevatorButtonActive(int floor, int shaft)
    {
        return elevators[shaft].getButtonState(floor);
    }
    
    /**
     * Get the state of a given elevator's given button's light.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param floor is the button in the elevator to check
     * @param shaft is the shaft the elevator to check is in
     * @return true if the light is on, false if the light is off
     */
    public boolean isElevatorButtonLightLit(int floor, int shaft)
    {
        return elevators[shaft].getButtonLightState(floor);
    }
    
    /**
     * Get the state of a given elevator's door.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param shaft is the shaft the elevator to check is in
     * @return true if the door is open, false otherwise
     */
    public boolean isElevatorDoorOpen(int shaft)
    {
        return elevators[shaft].getDoorState();
    }
    
    /**
     * Get the location of a given elevator.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @param shaft is the shaft in which the elevator resides
     * @return the floor the elevator is on as an integer
     */
    public int getElevatorLocation(int shaft)
    {
        return elevators[shaft].getCurrentFloor();
    }
    
    /**
     * Call an elevator to service the specified floor.<br>
     * 
     * PRE: N/A<br>
     * POST: The service request is logged for assignment by the system<br>
     * Cleanup: N/A<br>
     * 
     * @param floor is the floor requesting the service
     * @param direction is the intended direction of travel for the service request
     */
    public void callElevator(int floor, Direction direction) throws IllegalArgumentException
    {
        if (bottomFloor > floor || floor > topFloor)
        {
            throw new IllegalArgumentException();
        }
        switch (direction) {
            case UP:
                upRequestList.addDestination(upButtonList[floor]);
                break;
            case DOWN:
                downRequestList.addDestination(downButtonList[floor]);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
    
    /**
     * Record the selection of a destination from inside an elevator.<br>
     * 
     * PRE: N/A<br>
     * POST: The system updates the elevator with the given destination<br>
     * Cleanup: N/A<br>
     * 
     * @param floor is the floor the user wishes to stop at
     * @param shaft is the shaft in which the elevator resides
     * @throws IllegalArgumentException if floor or shaft are invalid
     */
    public void selectFloor(int floor, int shaft) throws IllegalArgumentException
    {
        try {
            elevators[shaft].selectFloor(floor);
            outerDoors[elevators[shaft].getCurrentFloor()][shaft].close();
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException();
        }
    }
    /**
     * Conduct a single time-event for the elevator system.<br>
     * 
     * PRE: N/A<br>
     * POST: All open floor doors are closed, all elevators are assigned any
     * possible destinations, all elevator time events are called, and arrival
     * states checked<br>
     * Cleanup: N/A<br>
     * 
     */
    public void tick()
    {
        // Close any open doors
        while (!openDoors.empty())
        {
            openDoors.pop().close();
        }
        
        // Add destinations to elevators
        upRequestList.getDestinations(elevators[0]);
        downRequestList.getDestinations(elevators[0]);
        
        // Tick all elevators and check if they have arrived at a destination
        for (Elevator e : elevators)
        {
            e.tick();
            System.out.println(e);
            if (e.isArrived())
            {
                elevatorArrived(e.getCurrentFloor(), e.getShaftID());
            }
        }
    }
    
    /**
     * Adjust door and button states when an elevator arrives.
     */
    private void elevatorArrived(int floorID, int shaftID) {
        outerDoors[floorID][shaftID].open();
        openDoors.push(outerDoors[floorID][0]);
        upButtonList[floorID].deactivate();
        downButtonList[floorID].deactivate();
    }
}