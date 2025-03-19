import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Flight> flights = findPlanesLeavingInTheNextTwoHours(airport);
        flights.forEach(System.out::println);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {

        final long MILLISECONDS_IN_HOUR = 3_600_000;
        final int HOURS_IN_QUESTIONS = 2;
        final long HOURS = MILLISECONDS_IN_HOUR * HOURS_IN_QUESTIONS;
        long currentTime = new Date().getTime();

        return airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                .filter(flight -> {
                    long departureTime = flight.getDate().getTime();
                    return (departureTime - currentTime >= 0) && (currentTime + HOURS >= departureTime);
                })
                .collect(Collectors.toList());
    }
}