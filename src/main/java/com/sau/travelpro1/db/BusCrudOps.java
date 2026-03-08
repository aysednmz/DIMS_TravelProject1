package com.sau.travelpro1.db;


import java.sql.*;
import java.util.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import com.sau.travelpro1.dto.Bus;

public class BusCrudOps {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/DIMS_Project";
    static final String USER = "postgres";
    static final String PASS = "Nur101";


    // Get a bus by id
    public Optional<Bus> getBusById(int id) {
        Bus bus = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM  \"Busses\" WHERE \"busId\" = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                bus = new Bus();
                bus.setBusId(resultSet.getInt("busId"));
                bus.setBusAgency(resultSet.getString("agency"));
                bus.setBusOrigin(resultSet.getString("origin"));
                bus.setBusDestination(resultSet.getString("destination"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (bus != null)
            return Optional.of(bus);
        else
            return Optional.empty();
    }

    // Insert a bus by id
    public int insertBusById(Bus bus) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String params = bus.getBusId() + ", \'" + bus.getBusAgency() + "\',\'" + bus.getBusOrigin() + "\',\'" + bus.getBusDestination() + "\'";
            // Check if there exist a record on that id
            if(getBusById(bus.getBusId()).isPresent()) {
                result = -1;
            } else {
                String query = "INSERT INTO \"Busses\" (\"busId\", \"agency\", \"origin\", \"destination\") VALUES (" + params + ");";
                result = statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    // Delete a passengerId by id
    public int deleteBusById(int id) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM \"Busses\" WHERE \"busId\" = " + id;
            result = statement.executeUpdate(query);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    // Update a car by id
    public int updateBusById(Bus bus) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String params = bus.getBusId() + ", \'" + bus.getBusAgency() + "\',\'" + bus.getBusOrigin() + "\',\'" + bus.getBusDestination() + "\'";
            // Check if there exist a record on that id
            if(getBusById(bus.getBusId()).isPresent()) {
                String query = "UPDATE \"Busses\" SET " +
                        "agency =  \'" + bus.getBusAgency() +"\', " +
                        "origin = \'" + bus.getBusOrigin() + "\', " +
                        "destination = \'" + bus.getBusDestination() + "\' WHERE \"busId\" = " + bus.getBusId() + ";";
                //System.out.printf("Query: " + query);
                result = statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }




}
