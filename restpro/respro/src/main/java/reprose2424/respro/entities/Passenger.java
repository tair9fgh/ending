package reprose2424.respro.entities;

// package airreservationsystem;

import java.util.Objects;

public class Passenger extends Entity {
    public Passenger() {
        
    }

    public Passenger(String firstName, String lastName, int age, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.passportNumber = passportNumber;
    }

    private String firstName;
    private String lastName;
    private int age;
    private String passportNumber;

    // here
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger team2 = (Passenger) o;
        return Objects.equals(firstName, team2.firstName) && Objects.equals(lastName,
                team2.lastName)
                && Objects.equals(age, team2.age)
                && Objects.equals(passportNumber, team2.passportNumber);
    }

    @Override
    public final int hashCode() {
        // return Objects.hash(field1, field2);
        int result = 11;
        if (firstName != null) {
            result = 22 * result + firstName.hashCode();// another not 31
        }
        if (lastName != null) {
            result = 22 * result + lastName.hashCode();
        }
        if (passportNumber != null) {
            result = 22 * result + passportNumber.hashCode();
        }
        return result;
    }

    //////////////////////////firstName
    public String getfirstName() {
        return this.firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    //////////////////////////lastName
    public String getlastName() {
        return this.lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    //////////////////////////age
    public int getage() {
        return this.age;
    }

    public void setage(int age) {
        this.age = age;
    }

    //////////////////////////passportNumber
    @Override
    public String getUniqueIdentifier() {
        return this.passportNumber;
    }

    public void setpassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    ///////////output
    @Override
    public String toString() {
        return "Passenger details: " + this.firstName + ", " + this.lastName + ", " +
                this.age + ", " + this.passportNumber;
    }
}

