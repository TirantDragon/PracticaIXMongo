module com.mycompany.practicaixmongo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;


    opens com.mycompany.practicaixmongo to javafx.fxml;
    exports com.mycompany.practicaixmongo;
}