package com.sau.travelpro1.controller;

import com.sau.travelpro1.db.PassengerCrudOps;
import com.sau.travelpro1.dto.Passenger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Optional;

public class passengerController {
    @FXML
    private Button updatePassenger;


    @FXML
    private Button clearPassenger;

    @FXML
    private Button closePassenger;

    @FXML
    private Button deletePassenger;

    @FXML
    private Button getPassenger;

    @FXML
    private Button savePassenger;


    @FXML
    private TextField passengerAddress;

    @FXML
    private TextField passengerId;

    @FXML
    private TextField passengerName;


    @FXML
    private TextField telephone;



    @FXML
    void passengerClose(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void passengerDelete(ActionEvent event) {
        try{
            checkId(passengerId.getText(), event);
            PassengerCrudOps crudOperations = new PassengerCrudOps();
            int id = Integer.parseInt(passengerId.getText());
            int result = crudOperations.deletePassengerById(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("passenger with id " + passengerId.getText() + " deleted");
            alert.showAndWait();
            passengerClear(event);
        }
        catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("violates foreign key constraint")) {
                showAlert(Alert.AlertType.ERROR, "Error", "This passenger cannot be deleted because it is linked to a travel.");}
            throw new RuntimeException(e);
        }
    }
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.showAndWait();
    }

    @FXML
    void passengerIdGet(ActionEvent event) {
        checkId(passengerId.getText(), event);
        PassengerCrudOps crudOperations = new PassengerCrudOps();
        int id = Integer.parseInt(passengerId.getText());
        Optional<Passenger> passenger = crudOperations.getPassengerById(id);
        if(passenger.isPresent()){
            passengerId.setText(Integer.toString(passenger.get().getPassengerId()));
            passengerAddress.setText(passenger.get().getPassengerAddress());
            passengerName.setText(passenger.get().getPassengerName());
            telephone.setText(passenger.get().getTelephone());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("passenger with id " + id + " not found");
            alert.showAndWait();
        }

    }

    @FXML
    public void checkId(String id, ActionEvent event) {
        if (id.isEmpty() || Integer.parseInt(id) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Id is wrong!");
            alert.showAndWait();
            passengerDelete(event);
        }
    }
    @FXML
    void passengerSave(ActionEvent event) {


        checkId(passengerId.getText(), event);
        Passenger passenger = new Passenger();
        passenger.setPassengerAddress(passengerAddress.getText());
        passenger.setPassengerName(passengerName.getText());
        passenger.setTelephone(telephone.getText());
        passenger.setPassengerId(Integer.parseInt(passengerId.getText()));
        PassengerCrudOps crudOperations = new PassengerCrudOps();
        int res = crudOperations.insertPassengerById(passenger);
        if(res > 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Passenger with id " + passengerId.getText() + " saved");
            alert.showAndWait();
        } else if(res == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("There another Passenger with id: " + passengerId.getText());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error on save Passenger!");
            alert.showAndWait();
        }

    }






    @FXML
    void passengerUpdate(ActionEvent event) {
        checkId(passengerId.getText(), event);
        Passenger passenger = new Passenger();
        passenger.setPassengerAddress(passengerAddress.getText());
        passenger.setPassengerName(passengerName.getText());
        passenger.setTelephone(telephone.getText());
        passenger.setPassengerId(Integer.parseInt(passengerId.getText()));
        PassengerCrudOps crudOperations = new PassengerCrudOps();
        int res = crudOperations.updatePassengerById(passenger);
        if(res > 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Passenger with id " + passengerId.getText() + " id updated");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error on update Passenger!");
            alert.showAndWait();
        }
    }
    @FXML
    void passengerClear (ActionEvent event) {
        passengerId.clear();
        passengerAddress.setText("");
        passengerName.setText("");
        telephone.setText("");
    }



}




