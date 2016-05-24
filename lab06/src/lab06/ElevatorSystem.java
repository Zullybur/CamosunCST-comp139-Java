/*
 */
package lab06;

/**
 * An elevator system controller.
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class ElevatorSystem {
    private static final int MAX_FLRS = 999;
    private static final int MIN_FLRS = 2;
    private static final int MIN_SUB_FLRS = 0;
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    private boolean hasThirteen;
    private int topFloor, bottomFloor;
    private int offset;             // Offset is used to account for sub-floors when returning values
    private CallRequestList upRequestList;
    private CallRequestList downRequestList;
    private Door[][] outerDoors;
    private CallButton[] upButtonList;
    private CallButton[] downButtonList;
    private Elevator[] elevators;
    
    public ElevatorSystem(int numFloors, int numSubFloors, boolean hasThirteen, int numElevatorShafts)
            throws IllegalArgumentException
    {
        if (MIN_FLRS > numFloors || MIN_SUB_FLRS > numSubFloors ||
            numFloors > MAX_FLRS || numSubFloors > MAX_FLRS)
        {
            throw new IllegalArgumentException();
        }
        this.hasThirteen = hasThirteen;
        // Add a floor if skipping thirteen unless floor count is under 13
        this.topFloor = (!hasThirteen && numFloors > 12) ? (numFloors + 1) : numFloors;
        this.offset = numSubFloors;
        upRequestList = new CallRequestList(UP);
        downRequestList = new CallRequestList(DOWN);
        setCallButtons(upButtonList, UP);
        setCallButtons(downButtonList, DOWN);
        createDoors();
        createElevators();
    }
    
    /**
     * Generate the buttons for each floor as required
     */
    private void setCallButtons(CallButton[] buttonList, String direction)
    {
        int tmp = topFloor + offset;
        // Add one to account for ignoring floor zero
        buttonList = new CallButton[tmp + 1];
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
        // instantiate Doors in nested loops (Floor -> Shaft)
        outerDoors[floor][shaft] = new Door()
    }
    
    /**
     * Create elevator objects for each shaft
     */
    private void createElevators()
    {
        // instatiate elevators
    }
    
    /**
     * 
     * @param floorID
     * @param direction
     */
    public void callElevator(int floorID, String direction)
    {
        // button stuff
    }
    
    /**
     * 
     */
    public void tick()
    {
        // check elevator states
    }
}
