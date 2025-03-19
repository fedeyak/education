package tasks.task_02_computer.processor;

public enum ProcessorFrequency {

    FREQUENCY_1200(1200),
    FREQUENCY_3100(3100),
    FREQUENCY_3200(3200),
    FREQUENCY_3300(3300),
    FREQUENCY_3500(3500),
    FREQUENCY_3600(3600);

    int frequency;

    ProcessorFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }
}
