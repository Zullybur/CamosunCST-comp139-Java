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
    private String doorType;
    
    /**
     * Instantiate a door of a specific type.
     * @param doorType
     * @throws IllegalArgumentException if the type is not valid
     */
    public Door(String doorType) throws IllegalArgumentException
    {
        boolean valid = isOpen = false;
        for (String type : DOOR_TYPES)
        {
            if (type.equals(doorType))
            {
                valid = true;
                this.doorType = doorType;
                break;
            }
        }
    }
    
    /**
     * TODO: Second constructor for outer doors
     * @param doorType
     * @param floorID
     * @param floorName
     */
    public Door(String doorType, int floorID, String floorName)
    {
        // instantiate inner door
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