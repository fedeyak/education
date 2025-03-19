package data_collectors;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataCollectorsTest {


    @Test
    public void HTMLParserTest(){
        String URL = "https://skillbox-java.github.io/";
        HTMLParser htmlParser = new HTMLParser();

        System.out.println("========== Список линий ==========");
        List<Line> lines = htmlParser.getLines(URL);
        lines.forEach(System.out::println);
        System.out.println();

        System.out.println("========== Список станций ==========");
        List<Station> stations = htmlParser.getStations(URL);
        stations.forEach(System.out::println);
    }

    @Test
    public void FileFinderTest() {
        String path = "data";
        List<String> files = new ArrayList<>();

        FileFinder finder = new FileFinder();
        File folder = new File(path);

        files.addAll(finder.fileLister(folder, "csv"));
        files.addAll(finder.fileLister(folder, "json"));

        files.forEach(System.out::println);
    }

    @Test
    public void JSONParserTest() throws IOException {
        String path = "data/2/4/depths-1.json";

        JSONParser<Station> parser = new JSONParser<>();
        List<Station> stations = parser.parseToList(path, Station.class);
        stations.forEach(station ->
                System.out.println(station.getStationName() + ", глубина: " + station.getDepth()));
    }

    @Test
    public void CSVStationParserTest() {
        String path = "data/0/5/dates-2.csv";
        CSVParser stationParser = new CSVParser();
        List<Station> stations =  stationParser.parseStations(path);
        stations.forEach(station ->
                System.out.println(station.getStationName() + ", дата создания: " + station.getOpeningDate()));
    }
}
