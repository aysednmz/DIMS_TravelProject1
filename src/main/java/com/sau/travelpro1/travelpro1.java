package com.sau.travelpro1;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class travelpro1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(travelpro1.class.getResource("View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        stage.setTitle("Travel Pro");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();





    }

}
