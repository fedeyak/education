package data_collectors;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"lineNumber"})
@JsonPropertyOrder({"name", "line", "date", "depth", "hasConnection"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Station {
    @JsonSetter("station_name")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String stationName;
    private String lineName;
    private String lineNumber;
    private boolean hasConnection;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate openingDate;
    private String depth;

    public Station(String stationName, String lineName, String lineNumber, boolean hasConnection) {
        this.stationName = stationName;
        this.lineName = lineName;
        this.lineNumber = lineNumber;
        this.hasConnection = hasConnection;
    }

    public Station(String stationName, LocalDate openingDate) {
        this.stationName = stationName;
        this.openingDate = openingDate;
    }

    @JsonGetter("name")
    public String getStation_name() {
        return stationName;
    }

    @JsonGetter("line")
    public String getLineName() {
        return lineName;
    }

    @JsonGetter("date")
    public LocalDate getOpeningDate() {
        return openingDate;
    }

    @Override
    public String toString() {
        return "Станция " + stationName + ", линия " + lineNumber;
    }
}
