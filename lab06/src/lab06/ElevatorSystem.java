/*
 */
package lab06;

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
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    private boolean hasThirteen;
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
     * Instantiate an elevator system with a given number of floors and elevator shafts.
     * @param numFloors is the number of above-ground floors the elevator services
     * @param numSubFloors is the number of below-ground floors the elevator services
     * @param hasThirteen indicates whether or not there is a 13th floor
     * @param numShafts is the number of separate elevator shafts in the system
     * @throws IllegalArgumentException if numFloors or numSubFloors is out of the ranges:<br>
     * (MIN_FLRS <= numFloors <= MAX_FLRS) and (MIN_SUB_FLRS <= numSubFloors <= MAX_FLOORS)
     */
    public ElevatorSystem(int numFloors, int numSubFloors, boolean hasThirteen, int numShafts)
            throws IllegalArgumentException
    {
        if (MIN_FLRS > numFloors || MIN_SUB_FLRS > numSubFloors ||
            numFloors > MAX_FLRS || numSubFloors > MAX_FLRS)
        {
            throw new IllegalArgumentException();
        }
        this.hasThirteen = hasThirteen;
        this.numShafts = numShafts;
        // Add a floor if skipping thirteen unless floor count is under 13
        this.topFloor = (!hasThirteen && numFloors > 12) ? (numFloors + 1) : numFloors;
        this.offset = numSubFloors;
        this.bottomFloor = (offset != 0) ? -offset : 1;
        upRequestList = new CallRequestList(UP);
        downRequestList = new CallRequestList(DOWN);
        // Add one to account for ignoring floor zero
        upButtonList = new CallButton[topFloor + offset + 1];
        downButtonList = new CallButton[topFloor + offset + 1];
        setCallButtons(upButtonList, UP);
        setCallButtons(downButtonList, DOWN);
        createDoors();
        createElevators(numFloors, numSubFloors);
        openDoors = new Stack<>();
    }
    
    /**
     * Generate the buttons for each floor as required
     */
    private void setCallButtons(CallButton[] buttonList, String direction)
    {
        int tmp = topFloor + offset;
        for (int i = 0, j = offset; i <= tmp; i++)
        {
            // No up button on highest floor
            if (i == tmp && direction.equals(UP)) continue;
            // No down button on lowest floor
            if (i == 0 && direction.equals(DOWN)) continue;
            // Skip floor zero (and thirteen if required)
            if ((i - offset == 0) || (!hasThirteen && i == 13)) continue;
            
            // Generate labels for each floor for use in GUI applications
            String name;
            if (j > 0)
            {
                if (j < 10)         name = "B:00";
                else if (j < 100)   name = "B:0";
                else                name = "B:";
                buttonList[i] = new CallButton(-j, name + j, direction);
                j--;
            } else {
                if (i < 10)         name = "F:00";
                else if (i < 100)   name = "F:0";
                else                name = "F:";
                buttonList[i] = new CallButton(i-offset, name + (i-offset), direction);
            }
        }
    }
    
    /**
     * Create outer door objects for each shaft on each floor
     */
    private void createDoors()
    {
        int tmp = topFloor + offset;
        // Add one to account for no floor zero or shaft zero
        outerDoors = new Door[tmp + 1][numShafts + 1];
        // instantiate Doors in nested loops (Floors -> Shafts)
        for (int i = 0, j = offset; i <= tmp; i++)
        {
            // Skip floor zero (and thirteen if required)
            if ((i - offset == 0) || (!hasThirteen && i == 13)) continue;
            
            for (int s = 0; s < numShafts; s++)
            {
                // Generate labels for each door in the system
                String name;
                if (j > 0)
                {
                    if (j < 10)         name = "B:00" + j + "-" + s;
                    else if (j < 100)   name = "B:0" + j + "-" + s;
                    else                name = "B:" + j + "-" + s;
                    outerDoors[i][s] = new Door(-j, name, s);
                    j--;
                } else {
                    if (i < 10)         name = "F:00" + (i-offset) + "-" + s;
                    else if (i < 100)   name = "F:0" + (i-offset) + "-" + s;
                    else                name = "F:" + (i-offset) + "-" + s;
                    outerDoors[i][s] = new Door(i-offset, name, s);
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
            elevators[s] =  new Elevator(numFloors, numSubFloors, hasThirteen);
        }
    }
    
    /**
     * 
     * @param floor
     * @param shaft
     * @return 
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
     * 
     * @param floor
     * @param direction
     * @return 
     */
    public boolean isCallButtonActive(int floor, String direction)
    {
        return (direction.equals(UP) ? upButtonList[floor].getButtonState() : downButtonList[floor].getButtonState());
    }
    
    /**
     * 
     * @param floor
     * @param direction
     * @return 
     */
    public boolean isCallButtonLightLit(int floor, String direction)
    {
        return (direction.equals(UP) ? upButtonList[floor].getLightState() : downButtonList[floor].getLightState());
    }
    
    /**
     * 
     * @param floor
     * @param shaft
     * @return 
     */
    public boolean isElevatorButtonActive(int floor, int shaft)
    {
        return elevators[shaft].getButtonState(floor);
    }
    
    /**
     * 
     * @param floor
     * @param shaft
     * @return 
     */
    public boolean isElevatorButtonLightLit(int floor, int shaft)
    {
        System.out.println("Shaft " + shaft);
        return elevators[shaft - 1].getButtonLightState(floor);
    }
    
    
    public boolean isElevatorDoorOpen(int shaft)
    {
        return elevators[shaft - 1].getDoorState();
    }
    
    public int getElevatorLocation(int shaft)
    {
        return elevators[shaft - 1].getCurrentFloor();
    }
    
    /**
     * Call an elevator to service the specified floor
     * @param floor
     * @param direction
     */
    public void callElevator(int floor, String direction) throws IllegalArgumentException
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
     * 
     * @param floor
     * @param shaft 
     */
    public void selectFloor(int floor, int shaft) throws IllegalArgumentException
    {
        // Is this a valid way to check parameters for validity?
        try {
            outerDoors[elevators[shaft].getCurrentFloor()][shaft].close();
            elevators[shaft].selectFloor(floor);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException();
        }
    }
    /**
     * 
     */
    public void tick()
    {
        // Close any open doors
        while (!openDoors.empty())
        {
            openDoors.pop().close();
        }
        
        // Add destinations to elevators as possible
        for (Elevator e : elevators)
        {
            if (e.getDirection() >= 0)
            {
                upRequestList.getDestinations(e);
            }
            if (e.getDirection() <= 0)
            {
                downRequestList.getDestinations(e);
            }
        }
        // Tick all elevators and check if they have arrived at a destination
        for (Elevator e : elevators)
        {
            String direction;
            e.tick();
            if (e.isArrived())
            {
                if (e.getDirection() == 1)
                {
                    direction = UP;
                } else {
                    direction = DOWN;
                }
                elevatorArrived(e.getCurrentFloor(), e.getShaftID(), direction);
            }
        }
    }
    
    /**
     * 
     */
    private void elevatorArrived(int floorID, int shaftID, String direction) {
        outerDoors[floorID][shaftID].open();
        if (direction.equals(UP)) {
            upButtonList[floorID].deactivate();
        } else {
            downButtonList[floorID].deactivate();
        }
    }
}
