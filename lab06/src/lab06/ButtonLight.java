/*
*/
package lab06;

/**
 * Controls a light behind any button type in an elevator system.
 * @author MattCasiro
 * Created: 23 May 2016
 */
public class ButtonLight
{
    // true = button light is on
    // false = button light is off
    private boolean isLit;
    
    /**
     * Instantiate a button light in the OFF state.<br>
     * 
     * PRE: N/A<br>
     * POST: The light for the button was created in the OFF state<br>
     * Cleanup: N/A<br>
     */
    public ButtonLight()
    {
        isLit = false;
    }
    
    /**
     * Change the button light state to ON.<br>
     * 
     * PRE: The button is in the OFF state<br>
     * POST: The button was set to ON<br>
     * Cleanup: N/A
     * 
     */
    public void turnOn()
    {
        isLit = true;
    }
    
    /**
     * Change the button light state to OFF.
     * PRE: The button is in the ON state
     * POST: The button was set to OFF
     */
    public void turnOff()
    {
        isLit = false;
    }
    
    /**
     * Get the state of the button light.
     * @return true if the button light is ON, false if the button light is OFF
     */
    public boolean getState()
    {
        return isLit;
    }
}
