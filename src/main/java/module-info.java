module com.example.sistemamatricula {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;



    opens com.example.sistemamatricula to javafx.fxml;
    opens com.example.sistemamatricula.Controlador to javafx.fxml;
    exports com.example.sistemamatricula;
    exports com.example.sistemamatricula.Controlador;
    exports com.example.sistemamatricula.Escena;
    opens com.example.sistemamatricula.Escena to javafx.fxml;
    opens com.example.sistemamatricula.Modelo to javafx.base;

}