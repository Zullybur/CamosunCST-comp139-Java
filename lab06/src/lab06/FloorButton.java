/*
 */
package lab06;

/**
 * A Floor Button object that is identified for a specific floor and has the ability to
 * be lit or un-lit by a light.
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class FloorButton
{
    private boolean isActive;
    private int floorID;
    private String floorName;
    private ButtonLight btnLight;
    
    /**
     * Create a button that corresponds to a specific floor and contains a light
     * @param floorID is the floor the button belongs to
     * @param floorName is the label for the floor
     */
    public FloorButton(int floorID, String floorName)
    {
        isActive = false;
        this.floorID = floorID;
        this.floorName = floorName;
        btnLight = new ButtonLight();
    }
    
    /**
     * Get the ID of the floor this button represents.
     * @return the floor ID value as an integer
     */
    public int getFloorID()
    {
        return floorID;
    }
    
    /**
     * Get the name label of the floor this button represents.
     * @return the floor name as a String
     */
    public String getFloorName()
    {
        return floorName;
    }
    
    /**
     * Get the current state of the button.
     * @return true if the button is active, false if the button is inactive
     */
    public boolean getButtonState()
    {
        return isActive;
    }
    
    /**
     * Get the current state of the light for the button.
     * @return true if the light is on, false if the light is off
     */
    public boolean getLightState()
    {
        return btnLight.getState();
    }
    
    /**
     * Activate the floor button.
     * PRE: The button is not active
     * POST: The button was activated and the light turned on
     */
    public void activate()
    {
        isActive = true;
        btnLight.turnOn();
    }
    
    /**
     * Deactivate the floor button.
     * PRE: The button is activated
     * POST: The button was deactivated and the light was turned off
     */
    public void deactivate()
    {
        isActive = false;
        btnLight.turnOff();
    }
}