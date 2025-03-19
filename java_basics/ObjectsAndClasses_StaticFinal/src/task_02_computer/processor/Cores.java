package tasks.task_02_computer.processor;

public enum Cores {

    CORES_1(1),
    CORES_2(2),
    CORES_4(4),
    CORES_8(8),
    CORES_10(10),
    ;

    int NumberOfCores;

    Cores(int numberOfCores) {
        NumberOfCores = numberOfCores;
    }

    public int getNumberOfCores() {
        return NumberOfCores;
    }
}
