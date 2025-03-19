package data_collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Aggregator {

    private final String URL;
    private final String root;
    private StationCollector collector;
    public ObjectMapper objectMapper;
    List<Station> stationList;
    Map<String, List<Station>> stations = new HashMap<>();

    public Aggregator(String URL, String root) {
        this.URL = URL;
        this.root = root;
        collector = new StationCollector();
        objectMapper = new ObjectMapper().findAndRegisterModules();
        stationList = collector.collectStations(URL, root);
    }

    public void makeStationsJSONFile() throws IOException {
        stations.put("stations" , stationList);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("target/stations.json"), stations);
    }

    public void makeLinesAndStationsJSONFile() throws IOException {
        HTMLParser htmlParser = new HTMLParser();
        StringBuilder linesAndStations = new StringBuilder();
        List<Line> lines = htmlParser.getLines(URL);
        linesAndStations.append("{\n");

        for (Line line : lines) {
            linesAndStations.append("\t\"" + line.getLineNumber() + "\": [\n");
            stationList.stream()
                    .filter(station -> station.getLineNumber().equals(line.getLineNumber()))
                    .map(station -> "\t\t\"" + station.getStationName() + "\",\n")
                    .forEach(station -> linesAndStations.append(station));

            linesAndStations.deleteCharAt(linesAndStations.length()-2);
            linesAndStations.append("\t],\n");
        }
        linesAndStations.deleteCharAt(linesAndStations.length()-2);
        linesAndStations.append("}\n");

        Files.writeString(new File("target/lines.json").toPath(), linesAndStations, StandardCharsets.UTF_8);
    }
}
