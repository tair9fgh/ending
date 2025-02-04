package reprose2424.respro.entities;

import reprose2424.respro.dbconnection.PostgresDB;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws Exception {
        IDataPool datapool = new DataPool();
        PostgresDB mycon = new PostgresDB();
        Connection con = mycon.connect();

        Flight flight1 = new Flight("AI202", "Los Angeles", "New York",
                "2024-12-20 15:30", "2024-01-10 10:30 AM", 1,
                45.45, "Economy", false);
        Flight flight2 = new Flight("AI303", "Boston", "Los Angeles",
                "2024-12-20 18:00", "2024-01-11 14:15 PM",
                80, 45.45, "standart", false);
        Flight flight3 = new Flight("AI404", "Miami", "Chicago",
                "2024-12-21 09:00", "2024-01-12 19:45",
                60, 45.45, "Buisness", true);
        Flight flight4 = new Flight("AI505", "New York", "San Francisco",
                "2024-12-21 12:30", "2024-01-13 11:00 AM", 70,
                45.45, "Standart", false);
        Flight flight5 = new Flight("AI606", "Chicago", "Boston",
                "2024-12-22 07:00", "2024-01-14 17:30 PM", 55, 45.78,
                "Buisness", true);// this 2
        Flight flight6 = new Flight("AI707", "Chicago", "Boston",
                "2024-12-22 07:00", "2024-01-14 18:30 PM", 52, 45.79,
                "Buisness", true);// this 1
        Flight flight7 = new Flight("AI808", "Chicago", "Boston",
                "2024-12-22 07:00", "2024-01-14 12:30 PM", 25, 40.78,
                "Buisness", true);

        Passenger passenger1 = new Passenger("John", "Doe", 30, "P123456789");
        Passenger passenger2 = new Passenger("John", "Doe", 30,
                "P123456789");//"Jane", "Smith", 25, "P987654321"
        Passenger passenger3 = new Passenger("Michael", "Johnson", 40, "P112233445");
        Passenger passenger4 = new Passenger("Emily", "Davis", 28, "P223344556");
        Passenger passenger5 = new Passenger("Daniel", "Williams", 35, "P334455667");

        datapool.addFlights(flight1);
        datapool.addFlights(flight2);
        datapool.addFlights(flight3);
        datapool.addFlights(flight4);
        datapool.addFlights(flight5);
        datapool.addFlights(flight6);
        datapool.addFlights(flight7);
        datapool.addPassenger(passenger1);
        datapool.addPassenger(passenger2);
        datapool.addPassenger(passenger3);
        datapool.addPassenger(passenger4);
        datapool.addPassenger(passenger5);
        datapool.printAllFlights();
        datapool.printAllPassengers();
        datapool.getFlight(
                flight1.getUniqueIdentifier());// searching by flightnumber  //Mistake was
        System.out.println("below is first passenger details");
        datapool.getPassenger(
                passenger1.getUniqueIdentifier());// searching by passportnumber //use hashcode()
        System.out.println("filtered by departure, destination and date below:");
        datapool.printFilteredFlights(datapool.filtermap("Chicago", "Boston",
                "2024-12-22 07:00"));////////HERE TO FILTER!!!!
        System.out.println("Sorted by Price below:");
        datapool.sortByPrice(datapool.filtermap("Chicago", "Boston",
                "2024-12-22 07:00"));// HERE TO SORT BY PRICE
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime current = LocalDateTime.parse("2024-01-29 12:30", formatter);
        datapool.changeTimetoCurrent(null);// current
        System.out.println("all flights after changetime");
        datapool.printAllFlights();

        //
        System.out.println("passenger is equal to passenger2: " + passenger1.equals(passenger2));
        ////////////////////////////////////////////////////////////////////////////
        ProperPassenger<String, Passenger> pass1
                = mycon.getallPassengersByFirstNameletterlastnameletter(con,
                47);// эту функцию я не добавил в список так как слишком много она бы все равно работала
        // ProperPassenger<String, Flight> pass2 = getallPassengersByFirstNameletterlastnameletter(
        //                 47);

        pass1.filtermap_forowndefault("Tair", "Akbayev",
                19);// оп умолчанию тут по первой букве имен
        pass1.filtermap_db("Kayrat", "Nurtas", 45);// тут по возрасту больше и имя одинаковое
        ;// переписанное
        ////////////////////////////////////////////////////////////////////////////
        // pass2.filtermap_forowndefaultforflight(String Fromdestin,
        //                                  String Destin,
        //                                  String DepTime,
        // double PriceofSeat)
        ////////////////////////////////////////////////////////////////////////////

        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();// not available
        Reservation reservation3 = new Reservation();
        Reservation reservation4 = new Reservation();
        Reservation reservation5 = new Reservation();

        passenger1.setfirstName("John");
        passenger1.setlastName("Doe");
        passenger1.setage(30);
        passenger1.setpassportNumber("P123456789");
        System.out.println("print:" + passenger1);
        ///
        flight1.setflightNumber("AI202");
        flight1.setFromdestin("Los Angeles");
        flight1.setarriveTime1("2024-01-10 10:30 AM");
        flight1.setdestination("New York");
        flight1.setdepartureTime("2024-12-20 15:30");
        flight1.setavailableSeats(1);
        flight1.setprice(45.45);
        flight1.setflightType("Economy");
        flight1.setfreesnacks(true);
        ///
        reservation1.setreservationID("R001");
        reservation1.setflights(flight1);
        reservation1.setpassenger(passenger1);// HEEEEEEEEEREEEEEEEEEEEEEEREEEEEE!!!!!!!
        reservation1.confirmReservation();// after this you can check available and status
        System.out.println(reservation1.toString());
        System.out.println(reservation1.getreservationStatus());
        //
        System.out.println(flight1.isAvailable());
        //
        // reservation1.cancelReservation();// now it is canceled and is available for others
        // System.out.println(reservation1.getreservationStatus());
        // System.out.println(reservation1.getreservationID());// null
        // System.out.println(reservation1.getflights());// null
        // System.out.println(reservation1.getpassenger());// null
        // System.out.println(flight1.isAvailable());// true
        /////////////////////////


        // Here don't use first set res2!
        // reservation2.confirmReservation();
        // System.out.println(reservation2.toString());
        // System.out.println(reservation2.getreservationStatus());
        // System.out.println(flight1.isAvailable());
        // reservation2.cancelReservation();
        // System.out.println(reservation2.toString());

    }
}
