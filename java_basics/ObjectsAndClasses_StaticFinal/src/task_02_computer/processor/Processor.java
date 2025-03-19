package tasks.task_02_computer.processor;

public class Processor {
    final ProcessorFrequency frequency;
    final Cores numberOfCores;
    final ProcessorManufacturer manufacturer;
    final  int weight;

    public Processor(ProcessorFrequency frequency, Cores numberOfCores, ProcessorManufacturer manufacturer, int weight) {
        this.frequency = frequency;
        this.numberOfCores = numberOfCores;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    public ProcessorFrequency getFrequency() {
        return frequency;
    }

    public Cores getNumberOfCores() {
        return numberOfCores;
    }

    public ProcessorManufacturer getManufacturer() {
        return manufacturer;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "frequency - " + frequency.getFrequency() +
                ", number of cores - " + numberOfCores.getNumberOfCores() +
                ", manufacturer - " + manufacturer;
    }
}
