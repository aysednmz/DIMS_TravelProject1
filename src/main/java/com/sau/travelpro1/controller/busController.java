package com.sau.travelpro1.controller;
import com.sau.travelpro1.db.BusCrudOps;
import com.sau.travelpro1.dto.Bus;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Optional;
public class busController {

    @FXML
    private Button updateBus;
    @FXML
    private Button clearBus;
    @FXML
    private Button closeBus;
    @FXML
    private Button deleteBus;
    @FXML
    private Button getBus;
    @FXML
    private Button saveBus;


    @FXML
    private TextField busAgency;
    @FXML
    private TextField busId;
    @FXML
    private TextField busOrigin;
    @FXML
    private TextField busDestination;

    @FXML
    void busClose(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void busDelete(ActionEvent event) {
        try{
            checkId(busId.getText(), event);
            BusCrudOps crudOperations = new BusCrudOps();
            int id = Integer.parseInt(busId.getText());
            int result = crudOperations.deleteBusById(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Bus with id " + busId.getText() + " deleted");
            alert.showAndWait();
            BusClear(event);
        }
         catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("violates foreign key constraint")) {
                showAlert(Alert.AlertType.ERROR, "Error", "This bus cannot be deleted because it is linked to a travel.");}
            throw new RuntimeException(e);
        }
    }

    @FXML
    void BusIdGet(ActionEvent event) {
            checkId(busId.getText(), event);
            BusCrudOps crudOperations = new BusCrudOps();
            int id = Integer.parseInt(busId.getText());
            Optional<Bus> bus = crudOperations.getBusById(id);
            if(bus.isPresent()){
                busId.setText(Integer.toString(bus.get().getBusId()));
                busAgency.setText(bus.get().getBusAgency());
                busOrigin.setText(bus.get().getBusOrigin());
                busDestination.setText(bus.get().getBusDestination());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Bus with id " + id + " not found");
                alert.showAndWait();
            }
    }
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.showAndWait();
    }


    @FXML
    public void checkId(String id, ActionEvent event) {
        if (id.isEmpty() || Integer.parseInt(id) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Id is wrong!");
            alert.showAndWait();
            busDelete(event);
        }
    }







    @FXML
    void BusSave(ActionEvent event) {


        checkId(busId.getText(), event);
        Bus bus = new Bus();
        bus.setBusAgency(busAgency.getText());
        bus.setBusOrigin(busOrigin.getText());
        bus.setBusDestination(busDestination.getText());
        bus.setBusId(Integer.parseInt(busId.getText()));
        BusCrudOps crudOperations = new BusCrudOps();
        int res = crudOperations.insertBusById(bus);
        if(res > 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Bus with id " + busId.getText() + " saved");
            alert.showAndWait();
        } else if(res == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("There another Bus with id: " + busId.getText());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error on save Bus!");
            alert.showAndWait();
        }

    }



    @FXML
    void BusUpdate(ActionEvent event) {
        checkId(busId.getText(), event);
        Bus bus = new Bus();
        bus.setBusAgency(busAgency.getText());
        bus.setBusOrigin(busOrigin.getText());
        bus.setBusDestination(busDestination.getText());
        bus.setBusId(Integer.parseInt(busId.getText()));
        BusCrudOps crudOperations = new BusCrudOps();
        int res = crudOperations.updateBusById(bus);
        if(res > 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Bus with id " + busId.getText() + " id updated");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error on update Bus!");
            alert.showAndWait();
        }
    }


    @FXML
    void BusClear (ActionEvent event) {
        busId.clear();
        busAgency.setText("");
        busOrigin.setText("");
        busDestination.setText("");
    }



}
