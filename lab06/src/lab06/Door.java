/*
 */
package lab06;

/**
 *
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class Door {
    private final String[] DOOR_TYPES = {"OUTER", "INNER"};
    private boolean isOpen;
    private int floorID, shaftNumber;
    private String doorType, floorName;
    
    /**
     * Instantiate an INNER door belonging to an elevator.
     * 
     * PRE: An elevator exists for the door to belong to<br>
     * POST: An elevator door was created for an elevator<br>
     * Cleanup: N/A<br>
     */
    public Door()
    {
        this.floorID = 0;
        this.floorName = "N/A";
        this.shaftNumber = 0;
        isOpen = false;
        doorType = DOOR_TYPES[1];
    }
    
    /**
     * Instantiate an OUTER door belonging to an elevator.<br>
     * 
     * PRE: A floor exists for the elevator to below to<br>
     * POST: A door was created for a floor<br>
     * Cleanup: N/A<br>
     * 
     * @param floorID is the id number for the floor the door is on
     * @param floorName is the label for the floor the door is on
     * @param shaftNumber is the shaft number that the door services
     */
    public Door(int floorID, String floorName, int shaftNumber)
    {
        this.floorID = floorID;
        this.floorName = floorName;
        this.shaftNumber = shaftNumber;
        isOpen = false;
        doorType = DOOR_TYPES[0];
    }
    
    /**
     * Get the state of the door.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return true if the door is open, otherwise false
     */
    public boolean isOpen()
    {
        return isOpen;
    }
    
    /**
     * Get the type of the door object.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return "OUTER" for an outer door or "INNER" for an inner door
     */
    public String getDoorType()
    {
        return doorType;
    }
    
    /**
     * Open the door.<br>
     * 
     * PRE: The door is closed<br>
     * POST: The door was opened<br>
     * Cleanup: N/A<br>
     */
    public void open()
    {
        isOpen = true;
    }
    
    /**
     * Close the door.<br>
     * 
     * PRE: The door is open<br>
     * POST: The door was closed<br>
     * Cleanup: N/A<br>
     */
    public void close()
    {
        isOpen = false;
    }
}