package tasks.task_02_computer.keyboard;

public class KeyBoard {
    final KeyboardType type;
    final BackLight backlight;
    final  int weight;

    public KeyBoard(KeyboardType type, BackLight backlight, int weight) {
        this.type = type;
        this.backlight = backlight;
        this.weight = weight;
    }

    public KeyboardType getType() {
        return type;
    }

    public BackLight getBacklight() {
        return backlight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "type - " + type +
                ", backlight - " + backlight;

    }
}
