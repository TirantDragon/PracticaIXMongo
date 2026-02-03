module com.mycompany.practicaixmongo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mycompany.practicaixmongo to javafx.fxml;
    exports com.mycompany.practicaixmongo;
}