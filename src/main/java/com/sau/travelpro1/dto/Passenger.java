package com.sau.travelpro1.dto;

public class Passenger {
    private int passengerId;
    private String passengerAddress;
    private String passengerName;
    private String telephone;

    public Passenger() {
    }

    public Passenger(int passengerId, String passengerAddress, String passengerName, String telephone) {
        this.passengerId = passengerId;
        this.passengerAddress = passengerAddress;
        this.passengerName = passengerName;
        this. telephone =  telephone;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int id) {
        this.passengerId = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this. passengerName =  passengerName;
    }

    public String getPassengerAddress() {
        return passengerAddress;
    }

    public void setPassengerAddress(String model) {
        this.passengerAddress = model;
    }

    public String getTelephone() {
        return  telephone;
    }

    public void setTelephone(String  telephone ) {
        this. telephone=  telephone;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ",passengerName='" +  passengerName + '\'' +
                ",passengerAddress='" + passengerAddress + '\'' +
                ", telephone='" +  telephone + '\'' +
                '}';
    }
}
