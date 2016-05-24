/*
*/
package lab06;

/**
 *
 * @author MattCasiro
 */
public class ButtonLight
{
    private boolean isLit;
    
    public ButtonLight()
    {
        isLit = false;
    }
    
    public void turnOn()
    {
        isLit = true;
    }
    
    public void turnOff()
    {
        isLit = false;
    }
    
    public boolean getState()
    {
        return isLit;
    }
}
