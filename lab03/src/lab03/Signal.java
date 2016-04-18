package lab03;

public class Signal {
    /**
     * 
     * <p>
     * Preconditions:   <br>
     * Postconditions:  <br>
     * Cleanup:         
     * <p>
     */
    public enum SignalColour {
        GREEN, YELLOW, RED;
        
        private static final SignalColour[] colours = values();
        
        public SignalColour next() {
            return colours[(this.ordinal() + 1) % colours.length];
        }
    };
    protected SignalColour colour;
    /**
     * 
     * <p>
     * Preconditions:   <br>
     * Postconditions:  <br>
     * Cleanup:         
     * <p>
     */
    public Signal() {
        colour = SignalColour.GREEN;
    }
    /**
     * 
     * <p>
     * Preconditions:   <br>
     * Postconditions:  <br>
     * Cleanup:         
     * <p>
     */
    public void tick() {
        colour = colour.next();
    }
    /**
     * 
     * <p>
     * Preconditions:   <br>
     * Postconditions:  <br>
     * Cleanup:         
     * <p>
     * @return 
     */
    public SignalColour getColour() {
        return colour;
    }
}
