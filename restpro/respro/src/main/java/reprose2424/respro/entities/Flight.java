package reprose2424.respro.entities;

import java.util.Objects;

public class Flight extends Entity {
    public Flight() {

    }

    public Flight(String flightNumber, String fromdestin, String destin,
                  String depTime, String arriveTime, int availSeats,
                  double priceofSeat, String flightType, boolean freesnacks) {
        this.flightNumber = flightNumber;
        this.fromdestin = fromdestin;
        this.arriveTime = arriveTime;
        this.destin = destin;
        this.depTime = depTime;
        this.availSeats = availSeats;
        this.priceofSeat = priceofSeat;
        this.flightType = flightType;
        this.freesnacks = freesnacks;
    }

    private String flightNumber;
    private String fromdestin;// here
    private String arriveTime;// here
    private String destin;
    private String depTime;
    private int availSeats;
    private double priceofSeat;
    private String flightType;
    private boolean freesnacks;

    private Reservation reservation;

    ///////here
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight team = (Flight) o;
        return Objects.equals(flightNumber, team.flightNumber) && Objects.equals(fromdestin,
                team.fromdestin)
                && Objects.equals(arriveTime, team.arriveTime)
                && Objects.equals(destin, team.destin) && Objects.equals(depTime, team.depTime)
                && Objects.equals(availSeats, team.availSeats) && Objects.equals(priceofSeat,
                team.priceofSeat)
                && Objects.equals(flightType,
                team.flightType)
                && Objects.equals(freesnacks,
                team.freesnacks);
    }

    @Override
    public final int hashCode() {
        // return Objects.hash(field1, field2);
        int result = 11;
        if (flightNumber != null) {
            result = 21 * result + flightNumber.hashCode();
        }
        if (fromdestin != null) {
            result = 21 * result + fromdestin.hashCode();
        }
        if (arriveTime != null) {
            result = 21 * result + arriveTime.hashCode();
        }
        if (destin != null) {
            result = 21 * result + destin.hashCode();
        }
        if (depTime != null) {
            result = 21 * result + depTime.hashCode();
        }
        result = 21 * result + availSeats;
        result = 21 * result + Double.hashCode(priceofSeat);
        result = 21 * result + (freesnacks ? 1 : 0);
        return result;
    }


    /////////////////////////////fromdestin
    public String getFromdestin() {
        return this.fromdestin;
    }

    public void setFromdestin(String fromdestin) {
        this.fromdestin = fromdestin;
    }

    /////////////////////////////arriveTime1
    public String getarriveTime1() {
        return this.arriveTime;
    }

    public void setarriveTime1(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    ////////////////////////////flight number
    @Override
    public String getUniqueIdentifier() {// getflightnumber
        return this.flightNumber;
    }

    public void setflightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    ////////////////////////////destination

    public String getdestination() {
        return this.destin;
    }

    public void setdestination(String destin) {
        this.destin = destin;
    }

    ////////////////////////////departure time
    public String getdepartureTime() {
        return this.depTime;
    }

    public void setdepartureTime(String depTime) {
        this.depTime = depTime;
    }

    ////////////////////////////available seats
    public int getavailableSeats() {
        return this.availSeats;
    }

    public void setavailableSeats(int availSeats) {
        this.availSeats = availSeats;
    }

    ////////////////////////////price
    public double getprice() {
        return this.priceofSeat;
    }

    public void setprice(double priceofSeat) {
        this.priceofSeat = priceofSeat;
    }

    ////////////////////flighttype
    public String getflightType() {
        return this.flightType;
    }

    public void setflightType(String flightType) {
        this.flightType = flightType;
    }

    //////////////////freesnacks
    public boolean getfreesnacks() {
        return this.freesnacks;
    }

    public void setfreesnacks(boolean freesnacks) {
        this.freesnacks = freesnacks;
    }

    //////reduce seats
    public void reduceNumberSeats() {
        this.availSeats -= 1;
    }

    /////// add seats
    public void addNumberSeats() {
        this.availSeats += 1;
    }

    ///////////output
    @Override
    public String toString() {
        return "Flight details: " + this.flightNumber + ", " + this.fromdestin + ", " + this.destin
                + ", " + this.depTime + ", " + this.arriveTime +
                ", " + this.availSeats + ", " + this.priceofSeat + ", " + this.flightType
                + ", free snacks: " + this.freesnacks;
    }
    //////////

    public boolean isAvailable() {
        if (this.availSeats <= 0) return false;
        else return true;
    }


}
