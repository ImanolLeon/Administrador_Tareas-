package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Services.RegistroProfesorServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroProfesorControlador {
    RegistroProfesorServices registroProfesorServices= new RegistroProfesorServices();
    MovimientoVentanas movimientoVentanas= new MovimientoVentanas();
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEspecialdad;
    @FXML
    private TextField txtGmail;

    //id_usuario
    private int id_usuario;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    //Button
    public void registrarProfesor(){
        String nombre = txtNombres.getText();
        String apellido= txtApellidos.getText();
        String telefono = txtTelefono.getText();
        String especialidad= txtEspecialdad.getText();
        String gmail = txtGmail.getText();


        try {
            int id_profesor= registroProfesorServices.registrarProfesor(nombre,apellido,especialidad,gmail,telefono,id_usuario);
            System.out.println("Se registró al profesor"+ nombre);

           movimientoVentanas.mover("Matricula/ProfesorMatricula.fxml","Profesor");

            }catch (Exception e){
            throw new RuntimeException(e);
        }


    }




}
