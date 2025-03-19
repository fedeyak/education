package task_01_elevator;

public class Main {
    public static void main(String[] args) {
        Elevator elevator = new Elevator(-3, 26);

        elevator.move(-3);
        elevator.move(-4);
        elevator.move(5);
        elevator.move(5);
        elevator.move(27);
        elevator.move(26);
    }
}
