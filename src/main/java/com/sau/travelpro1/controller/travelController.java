package com.sau.travelpro1.controller;

import com.sau.travelpro1.db.TravelCrudOps;
import com.sau.travelpro1.dto.Travel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class travelController {

    @FXML private TextField travelId, travelDate, travelPrice, travelSeat, travelTime;
    @FXML
    private TextField travelBusId;
    @FXML
    private TextField travelPassengerId;
    @FXML private Button saveTravel, updateTravel, deleteTravel, closeTravel, getTravel, clearTravel;

    @FXML
    void travelClose(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void TravelClear(ActionEvent event) {
        travelId.clear();
        travelDate.clear();
        travelPrice.clear();
        travelSeat.clear();
        travelTime.clear();
        travelBusId.clear();
        travelPassengerId.clear();
    }

    @FXML
    void TravelIdGet(ActionEvent event) {
        checkId(travelId.getText(), event);
        TravelCrudOps crudOperations = new TravelCrudOps();
        int id = Integer.parseInt(travelId.getText());
        Optional<Travel> travel = crudOperations.getTravelById(id);
        if(travel.isPresent()){
            travelId.setText(Integer.toString(travel.get().getTravelId()));
            travelBusId.setText(Integer.toString(travel.get().getBusId()));
            travelPassengerId.setText(Integer.toString(travel.get().getPassengerId()));
            travelPrice.setText(travel.get().getPrice());
            travelDate.setText(String.valueOf(travel.get().getDate()));
            travelSeat.setText(travel.get().getSeat());
            travelTime.setText(String.valueOf(travel.get().getTime()));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Travel with id " + id + " not found");
            alert.showAndWait();
        }
    }

    @FXML
    void TravelSave(ActionEvent event) {
      try{
          checkId(travelId.getText(), event);
          Travel travel = new Travel();
          travel.setTravelId(Integer.parseInt(travelId.getText()));
          travel.setPassengerId(Integer.parseInt(travelPassengerId.getText()));
          travel.setBusId(Integer.parseInt(travelBusId.getText()));
          travel.setPrice(travelPrice.getText());
          travel.setSeat(travelSeat.getText());
          travel.setTime(LocalTime.parse(travelTime.getText()));
          travel.setDate(LocalDate.parse(travelDate.getText()));
          TravelCrudOps crudOperations = new TravelCrudOps();
          int res = crudOperations.TravelSave(travel);
          if(res > 0){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Success");
              alert.setHeaderText("Travel with id " + travelId.getText() + " saved");
              alert.showAndWait();
          } else if(res == -1){
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setHeaderText("There another travel with id: " + travelId.getText());
              alert.showAndWait();
          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setHeaderText("Error on save travel!");
              alert.showAndWait();
          }

      } catch (Exception e) {
          if (e.getMessage() != null && e.getMessage().contains("violates foreign key constraint")) {
              showAlert(Alert.AlertType.ERROR, "Error", "Check your PassengerId and BusId.");}
          throw new RuntimeException(e);
      }
    }
    // Utility
    public void checkId(String id, ActionEvent event) {
        if (id.isEmpty() || Integer.parseInt(id) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Id is wrong!");
            alert.showAndWait();
            TravelClear(event);
        }
    }


    @FXML
    void TravelUpdate(ActionEvent event) {
        try{
            checkId(travelId.getText(), event);
            Travel travel = new Travel();
            travel.setTravelId(Integer.parseInt(travelId.getText()));
            travel.setPassengerId(Integer.parseInt(travelPassengerId.getText()));
            travel.setBusId(Integer.parseInt(travelBusId.getText()));
            travel.setPrice(travelPrice.getText());
            travel.setSeat(travelSeat.getText());
            travel.setTime(LocalTime.parse(travelTime.getText()));
            travel.setDate(LocalDate.parse(travelDate.getText()));
            TravelCrudOps crudOperations = new TravelCrudOps();
            int res = crudOperations.TravelUpdate(travel);
            if(res > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Travel with id " + travelId.getText() + " id updated");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error on update car!");
                alert.showAndWait();
            }

        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("violates foreign key constraint")) {
                showAlert(Alert.AlertType.ERROR, "Error", "Check your PassengerId and BusId.");}
            throw new RuntimeException(e);
        }
    }

    @FXML
    void TravelDelete(ActionEvent event) {
        if (checkId(travelId.getText())) {
            TravelCrudOps crud = new TravelCrudOps();
            int res = crud.deleteTravelById(Integer.parseInt(travelId.getText()));
            if (res > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Deleted", "Travel with id " + travelId.getText() + "deleted.");
                TravelClear(event);
            }
        }
    }

    private boolean checkId(String id) {
        if (id == null || id.isEmpty() || !id.matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid ID!");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.showAndWait();
    }
}