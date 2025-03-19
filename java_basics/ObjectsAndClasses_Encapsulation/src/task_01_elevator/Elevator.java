package task_01_elevator;

public class Elevator {
    private int currentFloor = 1;
    private final int MIN_FLOOR;
    private final int MAX_FLOOR;
    private final ElevatorOperator operator;

    public Elevator(int minFloor, int maxFloor) {
        this.MIN_FLOOR = minFloor;
        this.MAX_FLOOR = maxFloor;
        operator = new ElevatorOperator(this);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getMIN_FLOOR() {
        return MIN_FLOOR;
    }

    public int getMAX_FLOOR() {
        return MAX_FLOOR;
    }

    public void move(int floor) {
        operator.move(floor);
    }
}
