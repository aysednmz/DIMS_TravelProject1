package com.sau.travelpro1.dto;

public class Bus {

    private int busId;
    private String busAgency;
    private String busOrigin;
    private String busDestination;


    public Bus () {
    }

    public Bus(int busId, String busAgecy, String busOrigin, String busDestination) {
        this.busId = busId;
        this.busAgency = busAgecy;
        this.busOrigin = busOrigin;
        this. busDestination =  busDestination;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int id) {
        this.busId = id;
    }

    public String getBusAgency() {
        return busAgency;
    }

    public void setBusAgency(String busAgency) {
        this. busAgency =  busAgency;
    }

    public String getBusOrigin() {
        return busOrigin;
    }

    public void setBusOrigin(String model) {
        this.busOrigin = model;
    }

    public String getBusDestination() {
        return  busDestination;
    }

    public void setBusDestination(String  busDestination ) {
        this. busDestination=  busDestination;
    }



    @Override
    public String toString() {
        return "Bus{" +
                "BusId=" + busId +
                ",BusAgency='" +  busAgency + '\'' +
                ",BusOrigin='" + busOrigin + '\'' +
                ", BusDestination='" +  busDestination + '\'' +
                '}';
    }


}


