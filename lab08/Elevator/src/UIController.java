
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class UIController implements UIControllerInterface {

    protected int numFloors;
    protected ElevatorSystem sys;
    protected UIView view;

    /**
     * Sets up the UIController<br>
     *
     * Precondition: N/A<br>
     * Postcondition: Model has been set up and connected to the view<br>
     * Cleanup: N/A<br>
     *
     * @param numFloors the number of floors in the building
     * @param view the UI view
     */
    public UIController(int numFloors, UIView view) {
        this.numFloors = numFloors;
        sys = new ElevatorSystem(numFloors, this);
        this.view = view;

        int delay = 500; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sys.tick();
            }
        };
        new Timer(delay, taskPerformer).start();
    }

    /**
     * Sets the up or down call button to a given status<br>
     *
     * Precondition: The floor is valid<br>
     * Postcondition: If the Direction is UP, the up button status has been set
     * to the given status. If the Direction is DOWN, the down button status has
     * been set to the given status. Other Directions are ignored<br>
     * Cleanup: N/A<br>
     *
     * @param dir the button that is to be given a new status
     * @param status true if the light is to be lit, false otherwise
     * @param floor the floor on which the button is located
     */
    @Override
    public void setCallButtonLit(Direction.DIRECTION dir, boolean status, int floor) {
        view.setCallButtonLit(dir, status, floor);
    }

    /**
     * Sets the target floor button to a given status<br>
     *
     * Precondition: The floor is valid<br>
     * Postcondition: The target button status has been set to the given
     * status<br>
     * Cleanup: N/A<br>
     *
     * @param status true if the light is to be lit, false otherwise
     * @param floor the floor tied to the target button
     */
    @Override
    public void setTargetButtonLit(boolean status, int floor) {
        view.setTargetButtonLit(status, floor);
    }

    /**
     * Sets the floor and elevator doors to the given status<br>
     *
     * Precondition: The floor is valid<br>
     * Postcondition: The door status has been set to the given status<br>
     * Cleanup: N/A<br>
     *
     * @param status true if the door is to be opened, false otherwise
     * @param floor the floor tied to the door
     */
    @Override
    public void setDoorOpen(boolean status, int floor) {
        view.setFloorDoorOpen(status, floor);
        view.setElevatorDoorOpen(status, floor);
    }

    /**
     * Presses the up button on a given floor<br>
     *
     * Preconditions: floor is valid<br>
     * Postconditions: The up button has been pressed<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor where the call was made
     *
     * @see CallButton#callElevator(Direction.DIRECTION)
     */
    @Override
    public void callUp(int floor) {
        // TODO: Improve this for a bonus
        sys.callElevator(floor, Direction.DIRECTION.UP);
//        sys.floors[floor].callButton.callElevator(Direction.DIRECTION.UP);
    }

    /**
     * Presses the down button on a given floor<br>
     *
     * Preconditions: floor is valid<br>
     * Postconditions: The down button has been pressed<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor where the call was made
     *
     * @see CallButton#callElevator(Direction.DIRECTION)
     */
    @Override
    public void callDown(int floor) {
        // TODO: Improve this for a bonus
        sys.callElevator(floor, Direction.DIRECTION.DOWN);
//        sys.floors[floor].callButton.callElevator(Direction.DIRECTION.DOWN);
    }

    /**
     * Presses a floor button<br>
     *
     * Preconditions: floor is valid<br>
     * Postconditions: The given button has been pressed<br>
     * Cleanup: N/A<br>
     *
     * @param floor the floor button that was pressed
     *
     * @see TargetFloorButton#selectFloor()
     */
    @Override
    public void selectFloor(int floor) {
        // TODO: Improve this for a bonus
        sys.addFloor(floor);
//        sys.elevator.buttons[floor].selectFloor();
    }
}