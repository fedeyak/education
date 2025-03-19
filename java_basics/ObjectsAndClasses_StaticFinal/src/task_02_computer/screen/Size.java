package tasks.task_02_computer.screen;

public enum Size {
    SIZE_10(10),
    SIZE_20(20),
    SIZE_30(30),
    SIZE_40(40),
    SIZE_50(50);

    int ScreenDiagonal;

    Size(int screenDiagonal) {
        this.ScreenDiagonal = screenDiagonal;
    }

    public int getScreenDiagonal() {
        return ScreenDiagonal;
    }
}
