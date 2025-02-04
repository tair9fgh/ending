package reprose2424.respro.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class DataPool implements IDataPool {
    private Map<String, Flight> flights = new HashMap<>();
    private Map<String, Passenger> passengers = new HashMap<>();

    @Override
    public Map<String, Flight> filtermap(String fromDestin, String Destin,
                                         String DepTime) {
        Map<String, Flight> flights2 = flights.entrySet().stream()
                .filter(entry -> {
                    Flight flight = entry.getValue();
                    return flight.getFromdestin() != null &&
                            flight.getdestination() != null &&
                            flight.getdepartureTime() != null &&
                            flight.getFromdestin().equals(fromDestin)
                            &&
                            flight.getdestination().equals(Destin) &&
                            flight.getdepartureTime()
                                    .split(" ")[0].equals(
                                    DepTime.split(" ")[0]);
                })
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));
        return flights2;
    }

    public void printFilteredFlights(Map<String, Flight> flights) {
        for (Flight flight : flights.values()) {
            System.out.println(flight);
        }
    }

    public void sortByPrice(Map<String, Flight> flights) {
        Map<String, Flight> sortedFlights = flights.entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(
                        entry -> entry.getValue().
                                getprice())) // Sort by price
                .collect(Collectors.toMap(
                        Map.Entry::getKey,// Key remains the same
                        Map.Entry::getValue,
                        // Value remains the same
                        (e1, e2) -> e1,
                        // Handle duplicate keys (not expected here)
                        LinkedHashMap::new
                        // Preserve insertion order
                ));
        for (Flight flight : sortedFlights.values()) {
            System.out.println(flight);
        }
    }


    @Override
    public void addFlights(Flight flight) {
        if (!flights.containsKey(flight.getUniqueIdentifier())) {
            flights.put(flight.getUniqueIdentifier(), flight);
        } else {
            System.out.println("Flight Number already exists");
        }
    }

    @Override
    public void addPassenger(Passenger passenger) {
        if (!passengers.containsKey(passenger.getUniqueIdentifier())) {
            passengers.put(passenger.getUniqueIdentifier(), passenger);
        } else {
            System.out.println("Passport already exists");
        }
    }

    @Override
    public Flight getFlight(String flightNumber) {
        Flight fl = flights.get(flightNumber);
        System.out.println(fl);
        return fl;
    }

    @Override
    public Passenger getPassenger(String passportNumber) {
        Passenger pr = passengers.get(passportNumber);
        System.out.println(pr);
        return pr;
    }

    @Override
    public void printAllFlights() {
        for (Flight flight : flights.values()) {
            System.out.println(flight);
        }
    }

    @Override
    public void printAllPassengers() {
        for (Passenger passenger : passengers.values()) {
            System.out.println(passenger);
        }
    }

    ////change time
    @Override
    public void changeTimetoCurrent(LocalDateTime current) {
        if (current == null) current = LocalDateTime.now();
        List<String> flightsremoved = new ArrayList<>();
        for (Flight flight : flights.values()) {
            String timefrom = flight.getdepartureTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime giventime = LocalDateTime.parse(timefrom, formatter);
            if (current.isAfter(giventime)) {
                flightsremoved.add(flight.getUniqueIdentifier());
                // flights.remove(flight.getUniqueIdentifier());
            }
        }
        for (String id : flightsremoved) {
            flights.remove(id);
        }
        ////
    }

}

