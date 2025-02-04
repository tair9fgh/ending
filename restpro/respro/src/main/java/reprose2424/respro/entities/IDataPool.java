package reprose2424.respro.entities;

import java.time.LocalDateTime;
import java.util.Map;

public interface IDataPool {
    void addFlights(Flight flight);

    void addPassenger(Passenger passenger);

    Flight getFlight(String flightNumber);

    Passenger getPassenger(String passportNumber);

    void printAllFlights();

    void printAllPassengers();

    Map<String, Flight> filtermap(String fromdestin, String destin,
                                  String depTime);

    void printFilteredFlights(Map<String, Flight> flights);// after filtering

    void sortByPrice(Map<String, Flight> flights);

    void changeTimetoCurrent(LocalDateTime current);
}
