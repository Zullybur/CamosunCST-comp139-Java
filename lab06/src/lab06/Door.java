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
     * Instantiate an OUTER door belonging to an elevator
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
     * 
     * @return 
     */
    public boolean isOpen()
    {
        return isOpen;
    }
    
    /**
     * 
     * @return 
     */
    public String getDoorType()
    {
        return doorType;
    }
    
    /**
     * 
     */
    public void open()
    {
        isOpen = true;
    }
    
    /**
     * 
     */
    public void close()
    {
        isOpen = false;
    }
}