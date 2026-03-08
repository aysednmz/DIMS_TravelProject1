package com.sau.travelpro1.db;

import java.sql.*;
import java.util.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import com.sau.travelpro1.dto.Passenger;


public class PassengerCrudOps<passenger> {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/DIMS_Project";
    static final String USER = "postgres";
    static final String PASS = "Nur101";

    // Get a passenger by id
    public Optional<Passenger> getPassengerById(int id) {
        Passenger passenger = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM \"Passengers\" WHERE \"passengerId\" = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                passenger = new Passenger();
                passenger.setPassengerId(resultSet.getInt("passengerId"));
                passenger.setPassengerAddress(resultSet.getString("passengerAdress"));
                passenger.setPassengerName(resultSet.getString("passengerName"));
                passenger.setTelephone(resultSet.getString("telephone"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (passenger != null)
            return Optional.of(passenger);
        else
            return Optional.empty();
    }

    // Insert a car by id
    public int insertPassengerById(Passenger passenger) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String params = passenger.getPassengerId() + ", \'" + passenger.getPassengerAddress() + "\',\'" + passenger.getPassengerName() + "\',\'" + passenger.getTelephone() + "\'";
            // Check if there exist a record on that id
            if(getPassengerById(passenger.getPassengerId()).isPresent()) {
                result = -1;
            } else {
                String query = "INSERT INTO \"Passengers\" (\"passengerId\", \"passengerAdress\", \"passengerName\", telephone) VALUES (" + params + ");";
                result = statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    // Delete a passengerId by id
    public int deletePassengerById(int id) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM \"Passengers\" WHERE \"passengerId\" = " + id;
            result = statement.executeUpdate(query);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    // Update a car by id
    public int updatePassengerById(Passenger passenger) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String params = passenger.getPassengerId() + ", \'" + passenger.getPassengerAddress() + "\',\'" + passenger.getPassengerName() + "\',\'" + passenger.getTelephone() + "\'";
            // Check if there exist a record on that id
            if(getPassengerById(passenger.getPassengerId()).isPresent()) {
                String query = "UPDATE \"Passengers\" SET " +
                        "\"passengerAdress\" =  \'" + passenger.getPassengerAddress() +"\', " +
                        "\"passengerName\" = \'" + passenger.getPassengerName() + "\', " +
                        "\"telephone\" = \'" + passenger.getTelephone() + "\' WHERE\"passengerId\"= " + passenger.getPassengerId() + ";";
                //System.out.printf("Query: " + query);
                result = statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}




