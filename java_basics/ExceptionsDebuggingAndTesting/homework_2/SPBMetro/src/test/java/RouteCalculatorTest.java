import core.Line;
import core.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class RouteCalculatorTest {
    private static final double INTER_STATION_DURATION = 2.5;
    private static final double INTER_CONNECTION_DURATION = 3.5;
    StationIndex stationIndex = new StationIndex();
    Line animals = new Line(1, "AnimalsLine");
    Line shapes = new Line(2, "ShapesLine");
    Line flowers = new Line(3, "FlowersLine");
    String[] animalsArray = {"Cat", "Dog", "Rat", "Tiger", "Elephant", "Lion"};
    String[] shapesArray = {"Triangle", "Rectangle", "Circle", "Square", "Cube"};
    String[] flowersArray = {"Rose", "Dandelion", "Violet", "Camomile", "Tulip"};
    List<Line> lines = new ArrayList<>();
    RouteCalculator routeCalculator;

    public void setLines() {
        lines.add(animals);
        lines.add(shapes);
        lines.add(flowers);
        setLine(animals, animalsArray);
        setLine(shapes, shapesArray);
        setLine(flowers, flowersArray);
    }

    public void setLine(Line line, String[] stations) {
        for (String station : stations) {
            line.addStation(new Station(station, line));
        }
    }

    public void setStationIndex() {
        setLines();
        for (Line line : lines) {
            stationIndex.addLine(animals);
            for (Station station : line.getStations()) {
                stationIndex.addStation(station);
            }
        }
        // Station "Dog" is connected to Station "Circle"
        Station[] shapesAnimalsConnection = {shapes.getStations().get(2), animals.getStations().get(1)};
        stationIndex.addConnection(List.of(shapesAnimalsConnection));

        // Station "Elephant" is connected to Station "Violet"
        Station[] flowersAnimalsConnection = {flowers.getStations().get(2), animals.getStations().get(4)};
        stationIndex.addConnection(List.of(flowersAnimalsConnection));
    }

    @BeforeEach
    void setUp() {
        setStationIndex();
        routeCalculator = new RouteCalculator(stationIndex);
    }

    //    ============================ getShortestRoute Tests (one line) ====================================
    @Test
    @DisplayName("Проверяем правильность перечисления станций если введена одна и таже станция")
    void oneStationRouteReturnsCorrectStationsTest() {
        Station from = shapes.getStations().get(1);
        Station to = shapes.getStations().get(1);
        List<String> expectedLines = new ArrayList<>(List.of(shapesArray[1]));
        assertLinesMatch(expectedLines, getActualLines(from, to));
    }

    @Test
    @DisplayName("Проверяем правильность перечисления станций при поездке по линии")
    void oneLineRouteReturnsCorrectStationsTest() {
        Station from = shapes.getStations().get(1);
        Station to = shapes.getStations().get(3);
        List<String> expectedLines = new ArrayList<>(List.of(shapesArray[1], shapesArray[2], shapesArray[3]));
        assertLinesMatch(expectedLines, getActualLines(from, to));
    }

    @Test
    @DisplayName("Проверяем правильность перечисления станций при поездке по линии (в обратную сторону)")
    void oneLineBackwardsRouteReturnsCorrectStationsTest() {
        Station from = shapes.getStations().get(3);
        Station to = shapes.getStations().get(1);
        List<String> expectedLines = new ArrayList<>(List.of(shapesArray[3], shapesArray[2], shapesArray[1]));
        assertLinesMatch(expectedLines, getActualLines(from, to));
    }

    @Test
    @DisplayName("Проверяем правильность перечисления станций при поездке от начала линии к её концу")
    void oneLineRouteFromTopToBottomReturnsCorrectStationsTest() {
        Station from = shapes.getStations().get(0);
        Station to = shapes.getStations().get(shapesArray.length - 1);
        List<String> expectedLines = new ArrayList<>(List.of(shapesArray));
        assertLinesMatch(expectedLines, getActualLines(from, to));
    }

    @Test
    @DisplayName("Проверяем правильность перечисления станций при поездке от конца линии к её началу")
    void oneLineRouteFromBottomToTopReturnsCorrectStationsTest() {
        Station from = shapes.getStations().get(shapesArray.length - 1);
        Station to = shapes.getStations().get(0);
        List<String> expectedLines = new ArrayList<>(List.of(shapesArray));
        Collections.reverse(expectedLines);
        assertLinesMatch(expectedLines, getActualLines(from, to));
    }

//    ============================ getShortestRoute Tests (two lines one connection) ===================================

    @Test
    @DisplayName("Проверяем правильность перечисления станций при поездке c пересадкой. Одна станция (станция пересадки).")
    void oneConnectionRouteOneStationOnConnectionCorrectStationsTest() {
        Station from = animals.getStations().get(1); // animalsToShapesConnection
        Station to = shapes.getStations().get(2);    //shapesToAnimalsConnection
        List<String> expectedLines = new ArrayList<>(List.of("Dog", "Circle"));
        assertLinesMatch(expectedLines, getActualLines(from, to));
    }

    @Test
    @DisplayName("Проверяем правильность перечисления станций при поездке c пересадкой.")
    void oneConnectionRouteCorrectStationsTest() {
        Station from = shapes.getStations().get(0);
        Station to = animals.getStations().get(0);
        List<String> expectedLines = new ArrayList<>(List.of(shapesArray[0], shapesArray[1], shapesArray[2],
                                                        animalsArray[1], animalsArray[0]));
        assertLinesMatch(expectedLines, getActualLines(from, to));
    }

//    ============================ calculateDuration Tests (three lines two connections) ===============================

    @Test
    @DisplayName("Проверяем правильность перечисления станций при поездке: Три линии, две пересадки.")
    void twoConnectionRouteCorrectStationsTest() {
        Station from = shapes.getStations().get(0);
        Station to = flowers.getStations().get(0);
        List<String> expectedLines = new ArrayList<>(List.of
                (shapesArray[0], shapesArray[1], shapesArray[2],
                animalsArray[1], animalsArray[2], animalsArray[3], animalsArray[4],
                 flowersArray[2], flowersArray[1], flowersArray[0]));
        assertLinesMatch(expectedLines, getActualLines(from, to));
    }


//    ============================ calculateDuration Tests (one line) ====================================

    @Test
    @DisplayName("Проверяем правильность продолжительности поездки если введена одна и таже станция")
    void oneStationRouteReturnsCorrectCalculateDurationTest() {
        Station from = shapes.getStations().get(1);
        Station to = shapes.getStations().get(1);
        assertEquals(0.0, getTripDuration(from, to));
    }

    @Test
    @DisplayName("Проверяем правильность продолжительности поездки по одной линии")
    void oneLineFewStationsRouteReturnsCorrectCalculateDurationTest() {
        Station from = shapes.getStations().get(1);
        Station to = shapes.getStations().get(3);
        assertEquals(2 * INTER_STATION_DURATION, getTripDuration(from, to));
    }

    @Test
    @DisplayName("Проверяем правильность продолжительности поездки по одной линии от начала до конца")
    void oneLineRouteReturnsCorrectCalculateDurationTest() {
        Station from = shapes.getStations().get(0);
        Station to = shapes.getStations().get(shapesArray.length - 1);
        assertEquals((shapesArray.length - 1) * INTER_STATION_DURATION, getTripDuration(from, to));
    }

//    ============================ calculateDuration Tests (two lines, one connection) ====================================

    @Test
    @DisplayName("Проверяем правильность продолжительности поездки если введены только станции пересадки")
    void oneStationRouteOnConnectionReturnsCorrectCalculateDurationTest() {
        Station from = animals.getStations().get(1); // animalsToShapesConnection
        Station to = shapes.getStations().get(2);    //shapesToAnimalsConnection
        assertEquals(INTER_CONNECTION_DURATION, getTripDuration(from, to));
    }

    @Test
    @DisplayName("Проверяем правильность продолжительности поездки по двум линиям и одной пересадке")
    void twoLinesOneConnectionRouteReturnsCorrectCalculateDurationTest() {
        Station from = shapes.getStations().get(0);
        Station to = animals.getStations().get(0);
        assertEquals((2 * INTER_STATION_DURATION) + INTER_CONNECTION_DURATION + (1 * INTER_STATION_DURATION),
                getTripDuration(from, to));
    }

    //    ============================ calculateDuration Tests (three lines, two connections) ====================================

    @Test
    @DisplayName("Проверяем правильность продолжительности поездки по трём линиям с двумя пересадками")
    void threeLinesTwoConnectionsRouteReturnsCorrectCalculateDurationTest() {
        Station from = shapes.getStations().get(0);
        Station to = flowers.getStations().get(0);
        assertEquals((2 * INTER_STATION_DURATION) +
                        INTER_CONNECTION_DURATION +
                        (3 * INTER_STATION_DURATION) +
                        INTER_CONNECTION_DURATION +
                        (2 * INTER_STATION_DURATION),
                getTripDuration(from, to));
    }

    //    ============================ helper functions ====================================

    public List<String> getActualLines(Station from, Station to) {
        List<Station> stations = routeCalculator.getShortestRoute(from, to);
        return stations.stream().map(Station::getName).collect(Collectors.toList());
    }

    public double getTripDuration(Station from, Station to) {
        List<Station> stations = routeCalculator.getShortestRoute(from, to);
        return routeCalculator.calculateDuration(stations);
    }


}