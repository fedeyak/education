package data_collectors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    List<String> listOfFiles;

    public List<String> fileLister(File folder, String extension) {
        listOfFiles = new ArrayList<>();
        fileFinder(folder, extension);
        return listOfFiles;
    }

    private void fileFinder(File folder, String extension) {
        if (folder.isFile()) {
            String fileName = folder.getPath();
            if (extension.equals(getExtension(fileName))) {
                listOfFiles.add(fileName);
            }
            return;
        }
        File[] folders = folder.listFiles();
        for (File f : folders) {
            fileFinder(f, extension);
        }
    }

    private String getExtension(String fileName) {
        return fileName
                .replaceAll("^.+\\.", "")
                .replace("?.+$", "");
    }
}
