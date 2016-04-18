package lab03;

public class Signal {
    /**
     * Enumeration of possible signal colours in order: GREEN, YELLOW, RED.
     * <p>
     * Preconditions:   N/A.<br>
     * Postconditions:  An enumerated list of signal colours will be created.<br>
     * Cleanup:         N/A.
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
     * Default constructor for the Signal class, which models a three-state<br>
     * signal light with the colours GREEN, YELLOW, and RED.
     * <p>
     * Preconditions:   N/A.<br>
     * Postconditions:  A Signal object is created and the colour is set to GREEN.<br>
     * Cleanup:         N/A.
     */
    public Signal() {
        colour = SignalColour.GREEN;
    }
    /**
     * Changes the Signal object's colour attribute to the next listed colour<br>
     * in the order GREEN &gt; YELLOW &gt; RED &gt; GREEN.
     * <p>
     * Preconditions:   N/A.<br>
     * Postconditions:  The Signal object's colour will have incremented.<br>
     * Cleanup:         N/A.
     */
    public void tick() {
        colour = colour.next();
    }
    /**
     * Retrieves the current colour of the Signal object.
     * <p>
     * Preconditions:   N/A.<br>
     * Postconditions:  N/A.<br>
     * Cleanup:         N/A.
     * <p>
     * @return the current colour of the Signal object.
     */
    public SignalColour getColour() {
        return colour;
    }
}
