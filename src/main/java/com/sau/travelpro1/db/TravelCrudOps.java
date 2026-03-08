package com.sau.travelpro1.db;

import com.sau.travelpro1.dto.Bus;
import com.sau.travelpro1.dto.Travel;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class TravelCrudOps {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/DIMS_Project";
    static final String USER = "postgres";
    static final String PASS = "Nur101";

    // Get a travel by id
    public Optional<Travel> getTravelById(int id) {
        Travel travel = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM \"Travels\" WHERE \"travelId\" = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                travel = new Travel();
                travel.setTravelId(resultSet.getInt("travelId"));
                travel.setPassengerId(resultSet.getInt("passengerId"));
                travel.setBusId(resultSet.getInt("busId"));
                travel.setPrice(resultSet.getString("price"));
                travel.setSeat(resultSet.getString("seat"));
                travel.setDate(resultSet.getDate("date").toLocalDate());
                travel.setTime(resultSet.getTime("time").toLocalTime());


            }
        } catch (SQLException throwables) {

        }
        if (travel != null)
            return Optional.of(travel);
        else
            return Optional.empty();
    }
    // Insert a travel by id
    public int TravelSave(Travel travel) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String params = travel.getTravelId() + ", \'" + travel.getPassengerId() + "\',\'" + travel.getBusId() + "\',\'" + travel.getDate() +"\',\'"+ travel.getPrice() + "\',\'" + travel.getSeat() +"\',\'"+ travel.getTime()+ "\'" ;
            // Check if there exist a record on that id
            if(getTravelById(travel.getTravelId()).isPresent()) {
                result = -1;
            } else {
                String query = "INSERT INTO \"Travels\" (\"travelId\", \"passengerId\", \"busId\", \"date\",\"price\",\"seat\",\"time\") VALUES (" + params + ");";
                result = statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    // Update a travel by id
    public int TravelUpdate(Travel travel) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String params = travel.getTravelId() + ", \'" + travel.getPassengerId() + "\',\'" + travel.getBusId() + "\',\'" + travel.getDate() +"\',\'"+ travel.getPrice() + "\',\'" + travel.getSeat() +"\',\'"+ travel.getTime()+ "\'" ;
            // Check if there exist a record on that id
            if(getTravelById(travel.getTravelId()).isPresent()) {
                String query = "UPDATE \"Travels\" SET " +
                        "\"passengerId\" =  \'" + travel.getPassengerId() +"\', " +
                        "\"busId\" =  \'" + travel.getBusId() +"\', " +
                        "\"date\" = \'" + travel.getDate() + "\', " +
                        "\"price\" =  \'" + travel.getPrice() +"\', " +
                        "\"seat\" =  \'" + travel.getSeat() +"\', " +
                        "\"time\" =  \'" + travel.getTime() +"\', " +
                        "\"travelId\" = \'" + travel.getTravelId() + "\' WHERE \"travelId\" = " + travel.getTravelId() + ";";

                //System.out.printf("Query: " + query);
                result = statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public int deleteTravelById(int id) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM \"Travels\" WHERE \"travelId\" = " + id;
            result = statement.executeUpdate(query);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }


}
