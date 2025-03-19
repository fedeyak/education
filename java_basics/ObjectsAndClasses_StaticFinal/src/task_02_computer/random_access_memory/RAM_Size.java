package tasks.task_02_computer.random_access_memory;

public enum RAM_Size {
    SIZE_8(8),
    SIZE_12(12),
    SIZE_16(16),
    SIZE_32(32);

    int RAM_Size;

    RAM_Size(int RAM_Size) {
        this.RAM_Size = RAM_Size;
    }

    public int getRAM_Size() {
        return RAM_Size;
    }
}
