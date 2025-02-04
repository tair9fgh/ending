package reprose2424.respro.entities;

// package airreservationsystem;

public class Reservation extends Entity {
    public Reservation() {
    }

    public Reservation(String resID) {
        this.resID = resID;
    }

    private String resID;
    private Flight flight;
    private Passenger passenger;

    private String confirm;

    ///////////////////reservationID
    @Override
    public String getUniqueIdentifier() {
        return this.resID;
    }

    public void setreservationID(String resID) {
        this.resID = resID;
    }

    ///////////////////which flight
    public Flight getflights() {
        return this.flight;
    }

    public void setflights(Flight flight) {
        this.flight = flight;
    }

    ///////////////////which passenger
    public Passenger getpassenger() {
        return this.passenger;
    }

    public void setpassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    //////////////////////confirm cancel
    public void confirmReservation() {
        if (this.flight.getavailableSeats() <= 0) this.confirm = "No Seats";
        else {
            this.confirm = "Confirmed";
            this.flight.reduceNumberSeats();
        }
    }

    public void cancelReservation() {
        if (this.confirm == "No Seats") ;
        else if (this.confirm == "Confirmed") this.flight.addNumberSeats();
        this.confirm = "Canceled";
        this.passenger = null;
        this.flight = null;
        this.resID = null;
    }

    ///////////////////reservation status
    public String getreservationStatus() {
        return this.confirm;
    }

    @Override
    public String toString() {
        return "Reservation details: " + this.resID + ", " + this.flight + ", " + this.passenger +
                ", " + this.confirm;
    }
}

