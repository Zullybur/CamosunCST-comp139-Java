
public class EventSimulator implements EventSimulatorInterface {

    protected ElevatorSystem sys;

    public EventSimulator(int numFloors) throws IllegalArgumentException {
        sys = new ElevatorSystem(numFloors, null);
    }

    @Override
    public boolean isDownCallButtonLit(int floor)
            throws IllegalArgumentException {
        if (floor < 0 || sys.floors.length <= floor) {
            throw new IllegalArgumentException();
        }
        return sys.floors[floor].callButton.getDownLit();
    }

    @Override
    public boolean isUpCallButtonLit(int floor)
            throws IllegalArgumentException {
        if (floor < 0 || sys.floors.length <= floor) {
            throw new IllegalArgumentException();
        }
        return sys.floors[floor].callButton.getUpLit();
    }

    @Override
    public boolean isTargetButtonLit(int floor) throws IllegalArgumentException {
        if (floor < 0 || sys.floors.length <= floor) {
            throw new IllegalArgumentException();
        }
        return sys.elevator.buttons[floor].getLit();
    }

    @Override
    public boolean isElevatorDoorOpen() {
        return sys.elevator.door.getStatus() == Door.DOOR_STATUS.OPENED;
    }

    @Override
    public boolean isFloorDoorOpen(int floor) throws IllegalArgumentException {
        if (floor < 0 || sys.floors.length <= floor) {
            throw new IllegalArgumentException();
        }
        return sys.floors[floor].door.getStatus() == Door.DOOR_STATUS.OPENED;
    }

    @Override
    public int getCurrentElevatorFloor() {
        return sys.elevator.getCurrentFloor();
    }

    @Override
    public void callElevatorDown(int floor) throws IllegalArgumentException {
        if (floor < 0 || sys.floors.length <= floor) {
            throw new IllegalArgumentException();
        }

        sys.floors[floor].callButton.callElevator(Direction.DIRECTION.DOWN);
    }

    @Override
    public void callElevatorUp(int floor) throws IllegalArgumentException {
        if (floor < 0 || sys.floors.length <= floor) {
            throw new IllegalArgumentException();
        }

        sys.floors[floor].callButton.callElevator(Direction.DIRECTION.UP);
    }

    @Override
    public void selectFloor(int floor) throws IllegalArgumentException {
        if (floor < 0 || sys.floors.length <= floor) {
            throw new IllegalArgumentException();
        }

        sys.elevator.selectFloor(floor);
    }

    @Override
    public void tick() {
        sys.tick();
    }
}
