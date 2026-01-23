package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Services.RegistroEstudianteServices;
import com.example.sistemamatricula.Services.RegistroUsuariosService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class RegistroDatosEstudiantesController {
    RegistroEstudianteServices registroEstudianteServices= new RegistroEstudianteServices();
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtTelefono;
    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    public void registrarEstudiante(){
        String nombre = txtNombres.getText();
        String apellido = txtApellidos.getText();
        String telefono = txtTelefono.getText();
        LocalDate fecha_nacimiento = dpFechaNacimiento.getValue();
        try{
            registroEstudianteServices.registarDatosPersonales(nombre,apellido,telefono,fecha_nacimiento);
            System.out.println("Se registro el usuario "+ nombre);

        }catch (Exception e){
            System.out.println("Error al agregar datos de estudiante");
        }


    }

}



