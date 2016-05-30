/*
*/
package lab07;

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
     * PRE: A button exists for the light to operate with<br>
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
     * Change the button light state to OFF.<br>
     * 
     * PRE: The button is in the ON state<br>
     * POST: The button was set to OFF<br>
     * Cleanup: N/A<br>
     */
    public void turnOff()
    {
        isLit = false;
    }
    
    /**
     * Get the state of the button light.<br>
     * 
     * PRE: N/A<br>
     * POST: N/A<br>
     * Cleanup: N/A<br>
     * 
     * @return true if the button light is ON, false if the button light is OFF<br>
     */
    public boolean getState()
    {
        return isLit;
    }
}
