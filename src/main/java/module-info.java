module com.sau.travelpro1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.sau.travelpro1 to javafx.fxml;
    exports com.sau.travelpro1;
    exports com.sau.travelpro1.controller;
    opens com.sau.travelpro1.controller to javafx.fxml;
    exports com.sau.travelpro1.db;
    opens com.sau.travelpro1.db to javafx.fxml;

}