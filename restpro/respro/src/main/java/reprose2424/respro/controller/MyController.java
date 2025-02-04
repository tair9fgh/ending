package reprose2424.respro.controller;//

import org.springframework.web.bind.annotation.*;
import reprose2424.respro.dbconnection.PostgresDB;
import reprose2424.respro.entities.DataPool;
import reprose2424.respro.entities.Flight;
import reprose2424.respro.entities.IDataPool;
import reprose2424.respro.entities.Passenger;
import org.springframework.web.bind.annotation.GetMapping;//
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;//
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/main")
public class MyController {

    @Autowired
    private ObjectMapper oMapper;

    @Autowired
    public MyController(ObjectMapper oMapper) {
        this.oMapper = oMapper;
    }


    IDataPool datapool = new DataPool();

    ///////////////////////////////////////////////////////////create Flight
    @GetMapping("/create_passengerfunc")

    public String create_passengerfunc(@RequestParam String firstName, @RequestParam String lastName,
                                       @RequestParam int age, @RequestParam String passportNumber) {
        PostgresDB mycon = new PostgresDB();
        Connection con = null;
        try {
            con = mycon.connect();
        } catch (Exception e) {
            System.out.println("Exception 1");
        }
        String jsonText = null;
        Passenger passenger1 = new Passenger();
        passenger1.setfirstName(firstName);
        passenger1.setlastName(lastName);
        passenger1.setage(age);
        passenger1.setpassportNumber(passportNumber);
        Passenger s2 = null;
        try {
            s2 = mycon.createPassenger(con, passenger1);
        } catch (Exception e) {
            System.out.println("Exception 2");
        }
        try {
            jsonText = oMapper.writeValueAsString(s2);
        } catch (JsonProcessingException e) {
            System.out.println("Error System");
        }
        return jsonText;
    }
    ///////////////////////////////////////////////////////////create Flight

    ///////////////////////////////////////////////////////////create Passenger

    @GetMapping("/create_flightfunc")
    public String create_flightfunc(@RequestParam String flightNumber, @RequestParam String fromdestin, @RequestParam String destin,
                                    @RequestParam String depTime, @RequestParam String arriveTime, @RequestParam int availSeats,
                                    @RequestParam double priceofSeat, @RequestParam String flightType, @RequestParam boolean freesnacks) {

        PostgresDB mycon = new PostgresDB();
        Connection con = null;
        try {
            con = mycon.connect();
        } catch (Exception e) {
            System.out.println("Exception 1");
        }
        String jsonData = null;
        Flight flight1 = new Flight();
        flight1.setflightNumber(flightNumber);
        flight1.setFromdestin(fromdestin);
        flight1.setarriveTime1(arriveTime);
        flight1.setdestination(destin);
        flight1.setdepartureTime(depTime);
        flight1.setavailableSeats(availSeats);
        flight1.setprice(priceofSeat);
        flight1.setflightType(flightType);
        flight1.setfreesnacks(freesnacks);
        Flight s2 = null;
        try {
            s2 = mycon.createFlight(con, flight1);
        } catch (Exception e) {
            System.out.println("Exception 2");
        }
        try {
            jsonData = oMapper.writeValueAsString(s2);
        } catch (JsonProcessingException e) {
            System.out.println("Some error with passenger");
        }

        return jsonData;
    }
    ///////////////////////////////////////////////////////////create Passenger


    ///////////////////////////////////////////////////////////create Reservation
    @GetMapping("/create_reservfunc")
    public String create_reservfunc(@RequestParam String resID,
                                    @RequestParam String flightNumber,
                                    @RequestParam String passportNumber) {
        PostgresDB mycon = new PostgresDB();
        Connection con = null;
        try {
            con = mycon.connect();
        } catch (Exception e) {
            System.out.println("Exception 1");
        }
        String jsonText = null;
        boolean s2 = false;
        try {
            s2 = mycon.createReservation(con, resID, flightNumber, passportNumber);
        } catch (Exception e) {
            System.out.println("Exception 2");
        }
        try {
            jsonText = oMapper.writeValueAsString(s2);//responding
        } catch (JsonProcessingException e) {
            System.out.println("Error System");
        }
        return jsonText;
    }
    ///////////////////////////////////////////////////////////create Reservation


    //    /////////////////////////
//    @PostMapping //create
//    @PutMapping //update
//    @PatchMapping//partially update
//    @DeleteMapping
//    /////////////////


    ///////////////////////////////////////////////////////////update passenger
    @PatchMapping("/update_pass")
    public String update_pass(@RequestParam String firstName, @PathVariable String passportNumber) {
        PostgresDB mycon = new PostgresDB();
        Connection con = null;
        Passenger pass1 = null;
        try {
            con = mycon.connect();
            pass1 = mycon.getPassenger(con, passportNumber);
            pass1.setfirstName(firstName);
            con = mycon.connect();
            mycon.updatePassenger(con, pass1, passportNumber);
        } catch (Exception e) {
            System.out.println("Exception 1");
        }
        String jsonData = null;
        try {
            jsonData = oMapper.writeValueAsString(pass1);
        } catch (JsonProcessingException e) {
            System.out.println("Error System");
        }
        return jsonData;
    }
    ///////////////////////////////////////////////////////////update passenger


    ///////////////////////////////////////////////////////////get passengerbyid
    @GetMapping("/get_passid")
    public String get_passid(@PathVariable String passportNumber) {
        PostgresDB mycon = new PostgresDB();
        Connection con = null;
        try {
            con = mycon.connect();
        } catch (Exception e) {
            System.out.println("Exception 1");
        }
        Passenger pass = null;
        String jsonText = null;
        try {
            pass = mycon.getPassenger(con, passportNumber);
        } catch (Exception e) {
            System.out.println("Exception 2");
        }
        try {
            jsonText = oMapper.writeValueAsString(pass);
        } catch (JsonProcessingException e) {
            System.out.println("Error System");
        }
        return jsonText;
    }
    ///////////////////////////////////////////////////////////get passengerbyid


    ///////////////////////////////////////////////////////////get flightbydestinationtime
    @GetMapping("/get_destinflight")//еще не понял порядок
    public String get_destinFlight(@RequestParam String fromDestin, @RequestParam String destin,
                                   @RequestParam String depTime) {
        PostgresDB mycon = new PostgresDB();
        Connection con = null;
        try {
            con = mycon.connect();
        } catch (Exception e) {
            System.out.println("Exception 1");
        }
        List<Flight> list = null;
        String jsonText = null;
        try {
            list = mycon.getAllFlights(con, fromDestin, destin,
                    depTime);
        } catch (Exception e) {
            System.out.println("Exception 2");
        }
        try {
            jsonText = oMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            System.out.println("Error System");
        }
        return jsonText;
    }
    ///////////////////////////////////////////////////////////get flightbydestinationtime


    ///////////////////////////////////////////////////////////delete reservation
    @DeleteMapping("/deletereserv")
    public String deletereserv(@PathVariable String resID) {
        PostgresDB mycon = new PostgresDB();
        Connection con = null;
        try {
            con = mycon.connect();
        } catch (Exception e) {
            System.out.println("Exception 1");
        }
        boolean s2 = false;
        try {
            s2 = mycon.deleteReservation(con, resID);
        } catch (Exception e) {
            System.out.println("Exception 2");
        }
        String jsonData = null;
        try {
            jsonData = oMapper.writeValueAsString(s2);
        } catch (JsonProcessingException e) {
            System.out.println("Error System");
        }
        return jsonData;
    }
    ///////////////////////////////////////////////////////////delete reservation


    ///////////////////////////////////////////////////////////delete reservation
    @DeleteMapping("/deleteflight")
    public String deleteflight(@PathVariable String flightNumber) {
        PostgresDB mycon = new PostgresDB();
        Connection con = null;
        try {
            con = mycon.connect();
        } catch (Exception e) {
            System.out.println("Exception 1");
        }
        boolean s2 = false;
        try {
            s2 = mycon.deletedFlight(con, flightNumber);
        } catch (Exception e) {
            System.out.println("Exception 2");
        }
        String jsonData = null;
        try {
            jsonData = oMapper.writeValueAsString(s2);
        } catch (JsonProcessingException e) {
            System.out.println("Error System");
        }
        return jsonData;
    }
    ///////////////////////////////////////////////////////////delete reservation


}






