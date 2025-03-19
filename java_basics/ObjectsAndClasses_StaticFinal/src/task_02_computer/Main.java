package tasks.task_02_computer;

import tasks.task_02_computer.information_storage.InformationStorage;
import tasks.task_02_computer.information_storage.StorageType;
import tasks.task_02_computer.information_storage.StorageVolume;
import tasks.task_02_computer.keyboard.BackLight;
import tasks.task_02_computer.keyboard.KeyBoard;
import tasks.task_02_computer.keyboard.KeyboardType;
import tasks.task_02_computer.processor.Cores;
import tasks.task_02_computer.processor.Processor;
import tasks.task_02_computer.processor.ProcessorFrequency;
import tasks.task_02_computer.processor.ProcessorManufacturer;
import tasks.task_02_computer.random_access_memory.RAM_Size;
import tasks.task_02_computer.random_access_memory.RAM_Type;
import tasks.task_02_computer.random_access_memory.RandomAccessMemory;
import tasks.task_02_computer.screen.Screen;
import tasks.task_02_computer.screen.ScreenType;
import tasks.task_02_computer.screen.Size;

public class Main {

    public static void main(String[] args) {

        Computer computer1 = new Computer("IBM", "IBM_PC",
                new InformationStorage(StorageType.HDD, StorageVolume.VOLUME_240, 20),
                new KeyBoard(KeyboardType.ERGONOMIC, BackLight.NO_BACKLIGHT, 30),
                new Processor(ProcessorFrequency.FREQUENCY_3100, Cores.CORES_8, ProcessorManufacturer.IBM, 40),
                new RandomAccessMemory(RAM_Type.STATIC_RAM, RAM_Size.SIZE_32, 50),
                new Screen(Size.SIZE_40, ScreenType.VA, 60));

        System.out.println(computer1);
        System.out.println();

        Computer computer2 = new Computer("Dell", "Dell_PC",
                new InformationStorage(StorageType.SSD, StorageVolume.VOLUME_500, 5),
                new KeyBoard(KeyboardType.WIRELESS, BackLight.HAS_BACKLIGHT, 6),
                new Processor(ProcessorFrequency.FREQUENCY_3600, Cores.CORES_10, ProcessorManufacturer.INTEL, 7),
                new RandomAccessMemory(RAM_Type.DYNAMIC_RAM, RAM_Size.SIZE_16, 8),
                new Screen(Size.SIZE_30, ScreenType.TN, 8));

        System.out.println(computer2);
    }


}
