package lab03;

public class Intersection {

    protected Signal N, S, W, E;
    protected int state = 0;
    /**
     * Default constructor for the Intersection class, which models a four-way
     * intersection where N,S and E,W signals remain in sync.
     * <p>
     * Preconditions:   N/A.<br>
     * Postconditions:  N, S, E, and W Signal objects are created<br>
     *                  E and W Signal objects are set to Red.<br>
     * Cleanup:         N/A.
     */
    public Intersection() {
        N = new Signal();
        S = new Signal();
        W = new Signal();
        E = new Signal();

        W.tick();
        E.tick();
        W.tick();
        E.tick();
    }
    /**
     * Retrieve the current colour of all signal lights as a string
     * <p>
     * Preconditions:   N/A.<br>
     * Postconditions:  N/A.<br>
     * Cleanup:         N/A.
     * <p>
     * @return a string indicating the current colour of each signal light.
     */
    public String getState() {
        return "N:" + N.getColour() + " S:" + S.getColour() + " W:" + W.getColour() + " E:" + E.getColour();
    }
    /**
     * 
     * <p>
     * Preconditions:   <br>
     * Postconditions:  <br>
     * Cleanup:         
     * <p>
     * @throws RuntimeException if source code fails a sanity check.
     */
    public void tick() throws RuntimeException {
       state = (state + 1) % 6;
       switch (state) {
            case 0:
            case 1:
            case 2:
                N.tick();
                S.tick();
                break;
            case 3:
            case 4:
            case 5:
                W.tick();
                E.tick();
                break;
            default:
                throw new RuntimeException("Illegal State");
        }

    }
}