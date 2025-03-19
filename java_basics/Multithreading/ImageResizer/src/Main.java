package src;

import java.io.File;

public class Main {
    private static final int newWidth = 300;
    private static final int GPUAmount = 5;
    private static final String srcFolder = "data/out";
    private static final String dstFolder = "data/dst";
    private static final File srcDir = new File(srcFolder);
    private static final File[] photos = srcDir.listFiles();

    public static void main(String[] args) {

        int adjustedGPUAmount;
        int step;
        int rest;
        int totalFilesLength;
        long start = System.currentTimeMillis();

        if (GPUAmount > photos.length) {
            adjustedGPUAmount = photos.length;   // for the case if there are more GPUs then photos
        } else {
            adjustedGPUAmount = GPUAmount;
        }

        step = photos.length / adjustedGPUAmount;

        for (int i = 0; i < adjustedGPUAmount; i++) {
            ThreadStarter(step, (step * i), start);
        }

        totalFilesLength = step * adjustedGPUAmount;
        rest = photos.length - totalFilesLength;

        if (rest > 0) {
            ThreadStarter(rest, totalFilesLength, start);
        }
    }

    public static void ThreadStarter(int length, int beginning, long start) {
        File[] files = new File[length];
        System.arraycopy(photos, beginning, files, 0, files.length);
        ImageResizer resizer = new ImageResizer(newWidth, files, dstFolder, start);
        new Thread(resizer).start();
    }
}

