package tasks.task_02_computer.information_storage;

public enum StorageVolume {
    VOLUME_120(120),
    VOLUME_240(240),
    VOLUME_320(320),

    VOLUME_500(500);

    int volume;

    StorageVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}
