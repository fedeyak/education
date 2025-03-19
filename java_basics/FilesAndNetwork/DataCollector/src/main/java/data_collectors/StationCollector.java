package data_collectors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StationCollector {

    public List<Station> collectStations(String URL, String root) {

        List<Station> stationsFromHTML = collectFromHTML(URL);
        List<Station> stationsFromJSON = collectFromJSON(root);
        List<Station> stationsFromCSV = collectFromCSV(root);

        return joinStations(stationsFromHTML, stationsFromJSON, stationsFromCSV);
    }


    private  List<Station> collectFromHTML(String URL) {
        HTMLParser htmlParser = new HTMLParser();
        return htmlParser.getStations(URL);
    }

    private  List<Station> collectFromJSON(String root) {
        List<Station> stations = new ArrayList<>();
        List<String> jsonFiles = new ArrayList<>();
        File folder = new File(root);
        FileFinder finder = new FileFinder();
        JSONParser<Station> jsonParser = new JSONParser<>();

        jsonFiles.addAll(finder.fileLister(folder, "json"));
        for (String path : jsonFiles) {
            try {
                stations.addAll(jsonParser.parseToList(path, Station.class));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stations;
    }

    private  List<Station> collectFromCSV(String root) {
        List<Station> stations = new ArrayList<>();
        List<String> csvFiles = new ArrayList<>();
        File folder = new File(root);
        FileFinder finder = new FileFinder();
        CSVParser csvParser = new CSVParser();

        csvFiles.addAll(finder.fileLister(folder, "csv"));
        for (String path : csvFiles) {
            stations.addAll(csvParser.parseStations(path));
        }
        return stations;
    }

    private List<Station> joinStations(List<Station> htmlStations, List<Station> jsonStations, List<Station> csvlStations) {
        List<Station> stations = htmlStations;
        for (Station station : stations) {

            for (Station jsonStation : jsonStations) {
                if (station.getStationName().equals(jsonStation.getStationName())) {
                    station.setDepth(jsonStation.getDepth());
                    break;
                }
            }

            for (Station csvStation : csvlStations) {
                if (station.getStationName().equals(csvStation.getStationName())) {
                    station.setOpeningDate(csvStation.getOpeningDate());
                    break;
                }
            }
        }
        return stations;
    }
}
