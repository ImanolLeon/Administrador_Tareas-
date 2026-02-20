package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Services.RegistroUsuariosService;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class RegistroDatosEstudiantesController {
    RegistroUsuariosService registroEstudianteServices= new RegistroUsuariosService();
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtTelefono;
    @FXML
    private DatePicker dpFechaNacimiento;

    //Obtener id_usuario
    private int id_usuario;
    public void setId_usuario(int id_usuario){
        this.id_usuario=id_usuario;
    }


    MovimientoVentanas movimientoVentanas= new MovimientoVentanas();



    @FXML
    public void registrarEstudiante(){
        String nombre = txtNombres.getText();
        String apellido = txtApellidos.getText();
        String telefono = txtTelefono.getText();
        LocalDate fecha_nacimiento = dpFechaNacimiento.getValue();
        try{
            registroEstudianteServices.registarDatosPersonales(nombre,apellido,telefono,fecha_nacimiento,id_usuario);
            System.out.println("Se registro el usuario "+ nombre);
           movimientoVentanas.mover("Matricula/EstudianteMatricula.fxml","Matricula");

        }catch (Exception e){
            System.out.println("Error al agregar datos de estudiante");
        }


    }



}



