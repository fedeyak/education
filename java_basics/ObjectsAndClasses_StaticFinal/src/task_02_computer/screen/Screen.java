package tasks.task_02_computer.screen;

public class Screen {

    final Size size;
    final ScreenType type;
    final int weight;

    public Screen(Size size, ScreenType type, int weight) {
        this.size = size;
        this.type = type;
        this.weight = weight;
    }

    public Size getSize() {
        return size;
    }

    public ScreenType getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "size - " + size.getScreenDiagonal() +
                ", type - " + type;
    }
}
