import java.io.*;

public class FileUpdater {

    private final Pages pages;
    private final String outputTextFile = "output.txt";
    private final int initialSlashes;

    public FileUpdater(Pages pages, int initialSlashes) {
        this.pages = pages;
        this.initialSlashes = initialSlashes;
        createFile();
        addUrlsToFile();
    }

    private void createFile() {
        try {
            File outputFile = new File(outputTextFile);
            outputFile.createNewFile();
            PrintWriter pw = new PrintWriter(outputTextFile);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addUrlsToFile() {
        for (String url : pages.getLinks()) {
            String tabs = countTabs(url);
            try (FileWriter fw = new FileWriter(outputTextFile, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(tabs + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String countTabs(String url) {
        String tabs = "";
        int count = ((int) url.chars().filter(ch -> ch == '/').count()) - initialSlashes;
        for (int i = 0; i < count; i++) {
            tabs += "\t";
        }
        return tabs;
    }
}

