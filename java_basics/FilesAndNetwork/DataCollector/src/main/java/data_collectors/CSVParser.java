package data_collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    private List<String> lines = new ArrayList<>();
    private List<Station> stations = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public List<Station> parseStations(String path) {

        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (lines.get(0).equals("name,date")) {
            lines.remove(0);
        }

        for (String line : lines) {
            String[] station = line.split(",");
            stations.add(new Station(station[0], LocalDate.parse(station[1], formatter)));
        }

        return stations;
    }


}
