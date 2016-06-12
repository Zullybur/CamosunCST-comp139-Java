
import java.awt.Toolkit;

public class Chime {

    /**
     * Sounds the system chime<br>
     *
     * Precondition: N/A<br>
     * Postcondition: N/A<br>
     * Cleanup: N/A<br>
     *
     */
    public static void soundChime() {
        Toolkit.getDefaultToolkit().beep();
    }
}
