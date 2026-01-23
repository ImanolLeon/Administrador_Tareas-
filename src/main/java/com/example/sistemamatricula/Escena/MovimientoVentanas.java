package com.example.sistemamatricula.Escena;

import com.example.sistemamatricula.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MovimientoVentanas {


    public void mover(String nombreArchivo,String titulo){
        try {
            Stage stage=HelloApplication.getStageP();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemamatricula/"+nombreArchivo));

            Scene scene= new Scene(loader.load());

            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
