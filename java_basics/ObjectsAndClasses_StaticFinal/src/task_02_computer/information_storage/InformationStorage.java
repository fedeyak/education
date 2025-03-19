package tasks.task_02_computer.information_storage;

public class InformationStorage {

    final StorageType type;
    final StorageVolume volume;
    final int weight;

    public InformationStorage(StorageType type, StorageVolume volume, int weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public StorageType getType() {
        return type;
    }

    public StorageVolume getVolume() {
        return volume;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "type - " + getType() +
                ", volume - " + volume.getVolume();

    }
}
