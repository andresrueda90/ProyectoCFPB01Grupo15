module com.example.filesgenerator {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;

    opens com.example.filesgenerator to javafx.fxml;
    exports com.example.filesgenerator;
}