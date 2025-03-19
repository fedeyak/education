package data_collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTMLParser {
    Document document;
    Elements linesElements;
    Elements stationsElements;

    public List<Line> getLines(String URL) {
        List<Line> lines = new ArrayList<>();
        document = parseWebPage(URL);

        linesElements = document.select("span.js-metro-line");
        for (int i = 1; i <= linesElements.size(); i++) {
            String rawData = linesElements
                    .get(i - 1)
                    .toString();
            String lineNumber = getLineNumber(rawData);
            String lineName = getLineName(rawData);

            lines.add(new Line(lineNumber, lineName));
        }
        return lines;
    }

    public List<Station> getStations(String URL) {
        List<Line> lines = getLines(URL);
        List<Station> stations = new ArrayList<>();
        document = parseWebPage(URL);
        stationsElements = document.select("p.single-station");
        int lineIndex = -1;

        for (int i = 0; i < stationsElements.size(); i++) {
            String[] rawStation = stationsElements
                    .get(i)
                    .text()
                    .split("[.] ");
            if (rawStation[0].equals("1")) {
                lineIndex++;
            }


            String stationName = rawStation[1];
            String lineName = lines.get(lineIndex).getLineName();
            String lineNumber = lines.get(lineIndex).getLineNumber();
            Boolean hasConnection = hasConnection(i);

            stations.add(new Station(stationName, lineName, lineNumber, hasConnection));

        }
        return stations;
    }

    private Document parseWebPage(String URL) {
        try {
            return Jsoup.connect(URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getLineNumber(String rawData){
        return clearString(rawData)[1];
    }

    private String getLineName(String rawData){
        return clearString(rawData)[2].replaceAll("[^А-Яа-я0-9\\- +]", "").replaceAll(" [Лл]иния", "");
    }

    private String[] clearString(String rawData) {
        String[] benefication = rawData.split("data-line=");
        return benefication[1].split("\"");
    }

    private boolean hasConnection(int index) {
        return stationsElements
                .get(index)
                .toString()
                .contains("переход на станцию");
    }
}
