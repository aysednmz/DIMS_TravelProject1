package com.sau.travelpro1.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Travel {

    private LocalDate date;
    private int passengerId;
    private int busId;
    private String price;
    private String seat;
    private LocalTime time;
    private int travelId;

    public Travel(){
    }
    public Travel(int busId,java.time.LocalDate date,int passengerId,String price,String seat,java.time.LocalTime time, int travelId){
        this.date = date;
        this.passengerId= passengerId;
        this.price =  price;
        this.seat=seat;
        this.time=time;
        this.travelId=travelId;
        this.busId=busId;
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }



    @Override
    public String toString() {
        return "Travel{" +
                "BusId=" + busId +
                ",TravelDate='" +  date + '\'' +
                ",TravelPassengerId='" + passengerId + '\'' +
                ", TravelPrice='" +  price + '\'' +
                ", TravelSeat='" +  seat + '\'' +
                ", TravelTime='" +  time + '\'' +
                '}';
    }

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public int getPassengerId() {
        return passengerId;
    }
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId  ;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getBusId() {return busId;}



}