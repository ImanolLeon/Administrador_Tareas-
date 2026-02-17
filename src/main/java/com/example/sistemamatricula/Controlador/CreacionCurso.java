package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Services.CursoServices;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreacionCurso {

    CursoServices cursoServices = new CursoServices();
    MovimientoVentanas movimientoVentanas= new MovimientoVentanas();

    @FXML
    private TextField txtNombreCurso;
    @FXML
    private TextField txtCreditosCurso;
    @FXML
    private TextField txtEstudiantesCurso;

    public void registrarCurso(){

        String nombre = txtNombreCurso.getText();
        int credito = Integer.parseInt(txtCreditosCurso.getText());
        int cantidadEstudiantes = Integer.parseInt(txtEstudiantesCurso.getText());

        try{
            cursoServices.registrarCurso(nombre,credito,cantidadEstudiantes);
            System.out.println("Curso :"+ nombre + " Registrado");


        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    public void irPagPrincipal(){
        movimientoVentanas.mover("Principal.fxml","Pricipal");
    }
}
