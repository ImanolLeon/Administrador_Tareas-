package com.example.sistemamatricula;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stagePrincipal;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Ingreso");
        stage.setScene(scene);
        stage.show();
        stagePrincipal=stage;
    }

    public static Stage getStageP() {
        return stagePrincipal;
    }



    public static void main(String[] args) {
        launch();
    }
}