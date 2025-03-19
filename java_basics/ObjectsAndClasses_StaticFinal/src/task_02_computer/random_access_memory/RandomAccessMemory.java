package tasks.task_02_computer.random_access_memory;

public class RandomAccessMemory {

    final RAM_Type type;
    final RAM_Size size;
    final  int weight;

    public RandomAccessMemory(RAM_Type type, RAM_Size size, int weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public RAM_Type getType() {
        return type;
    }

    public RAM_Size getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "type - " + type +
                ", size - " + size.getRAM_Size();
    }
}
