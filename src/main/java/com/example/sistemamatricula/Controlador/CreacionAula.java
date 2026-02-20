package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Services.AulaService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreacionAula {
    AulaService aulaService = new AulaService();
    MovimientoVentanas movimientoVentanas= new MovimientoVentanas();
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCapacidad;


    public void registrarEstudiante(){

        String nombreAula = txtNombre.getText();
        int cantidadAlumnos = Integer.parseInt(txtCapacidad.getText());
        try {
            aulaService.registrarAula(nombreAula,cantidadAlumnos);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void retroceder(){
        movimientoVentanas.mover("Aula/Aula.fxml","Aula");
    }
}
