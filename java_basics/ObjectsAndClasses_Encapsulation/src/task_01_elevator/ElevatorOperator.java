package task_01_elevator;

public class ElevatorOperator {
    private final Elevator elevator;
    private int currentFloor;
    private final int MIN_FLOOR;
    private final int MAX_FLOOR;

    public ElevatorOperator(Elevator elevator) {
        this.elevator = elevator;
        this.currentFloor = elevator.getCurrentFloor();
        this.MIN_FLOOR = elevator.getMIN_FLOOR();
        this.MAX_FLOOR = elevator.getMAX_FLOOR();
    }

    void move(int floor) {
        if (!wrongFloor(floor)) {
            System.out.println("Error. No such floor exists.");
        } else if (floor > currentFloor) {
            moveUp(floor);
        } else if (floor < currentFloor) {
            moveDown(floor);
        }
    }

    private boolean wrongFloor(int floor) {
        return floor >= MIN_FLOOR && floor <= MAX_FLOOR;
    }

    private void moveDown(int floor) {
        while (floor != currentFloor) {
            movingDown();
            System.out.println(currentFloor);
        }
    }

    private void moveUp(int floor) {
        while (floor != currentFloor) {
            movingUp();
            System.out.println(currentFloor);
        }
    }

    private void movingDown() {
        if (currentFloor > MIN_FLOOR) {
            currentFloor--;
        }
    }

    private void movingUp() {
        if (currentFloor < MAX_FLOOR) {
            currentFloor++;
        }
    }
}
