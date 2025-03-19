package data_collectors;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String URL = "https://skillbox-java.github.io/";
        String root = "data";
        Aggregator aggregator = new Aggregator(URL, root);
        aggregator.makeStationsJSONFile();
        aggregator.makeLinesAndStationsJSONFile();

    }
}

