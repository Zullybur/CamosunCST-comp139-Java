public interface CallElevatorSystemInterface {

    public void addFloor(int floor) throws IllegalArgumentException;

    public void callElevator(int id, Direction.DIRECTION dir);

    public int getNextFloor();

    public void removeFloor(int floor) throws IllegalArgumentException;
    
    public boolean checkButton(int floor, Direction.DIRECTION dir);
    
    public Direction.DIRECTION getDir();
}
