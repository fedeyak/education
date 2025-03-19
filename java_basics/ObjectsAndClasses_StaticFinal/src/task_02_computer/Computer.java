package tasks.task_02_computer;

import tasks.task_02_computer.information_storage.InformationStorage;
import tasks.task_02_computer.keyboard.KeyBoard;
import tasks.task_02_computer.processor.Processor;
import tasks.task_02_computer.random_access_memory.RandomAccessMemory;
import tasks.task_02_computer.screen.Screen;

public class Computer {

    final String vendor;

    final String name;
    InformationStorage storage;
    KeyBoard keyBoard;
    Processor processor;
    RandomAccessMemory randomAccessMemory;
    Screen screen;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
    }

    public Computer(String vendor, String name,
                    InformationStorage storage, KeyBoard keyBoard,
                    Processor processor, RandomAccessMemory randomAccessMemory, Screen screen) {
        this.vendor = vendor;
        this.name = name;
        this.storage = storage;
        this.keyBoard = keyBoard;
        this.processor = processor;
        this.randomAccessMemory = randomAccessMemory;
        this.screen = screen;
    }

//    ========== getters ==========

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public InformationStorage getStorage() {
        return storage;
    }

    public KeyBoard getKeyBoard() {
        return keyBoard;
    }

    public Processor getProcessor() {
        return processor;
    }

    public RandomAccessMemory getRandomAccessMemory() {
        return randomAccessMemory;
    }

    public Screen getScreen() {
        return screen;
    }

//    ========== setters ==========

    public void setStorage(InformationStorage storage) {
        this.storage = storage;
    }

    public void setKeyBoard(KeyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void setRandomAccessMemory(RandomAccessMemory randomAccessMemory) {
        this.randomAccessMemory = randomAccessMemory;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

//    ====================

    public int totalWeight() {
        return storage.getWeight() + keyBoard.getWeight() + processor.getWeight() +
                randomAccessMemory.getWeight() + screen.getWeight();
    }

    @Override
    public String toString() {
        return "Computer information\n" +
                "vendor: " + vendor + '\n' +
                "name: " + name + '\n' +
                "storage: " + storage + "\n" +
                "keyBoard: " + keyBoard + "\n" +
                "processor: " + processor + "\n" +
                "RAM: " + randomAccessMemory + "\n" +
                "screen: " + screen + "\n" +
                "total weight: " + totalWeight() + " kg";
    }
}
