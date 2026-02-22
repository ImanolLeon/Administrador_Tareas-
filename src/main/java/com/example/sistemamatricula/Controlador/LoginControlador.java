package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Dao.ProfesorDao;
import com.example.sistemamatricula.Dao.UsuarioDao;
import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Modelo.Profesor;
import com.example.sistemamatricula.Modelo.Usuario;
import com.example.sistemamatricula.Sesion.SesionProfesor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class LoginControlador {
    MovimientoVentanas ventanas= new MovimientoVentanas();

    @FXML
    private TextField dni;

    @FXML
    private PasswordField txtPassword;
    ProfesorDao profesorDao = new ProfesorDao();

    @FXML
    public void ingresar(){

        String username = dni.getText();
        String password = txtPassword.getText();

        //  Ver si están vacios
        if (username.isEmpty() || password.isEmpty()){
            //Alerta para saber si ingresaon o no datos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("DNI o contraseña incorrectos");
            alert.showAndWait();
            return;

        }

        UsuarioDao dao = new UsuarioDao();
        Usuario usuario= dao.login(username,password);

        if(usuario != null){


            switch (usuario.getRol()){
                case 1 : ventanas.mover("Principal.fxml","PanelAdmin");
                    break;
                case 2 : ventanas.mover("Matricula/EstudianteMatricula.fxml","Matricula estudiante");
                    break;

                case 3 :
                    Profesor profesor = profesorDao.buscarPorUsuario(usuario.getIdUsuario());

                    if(profesor!= null){
                        //Obtenemos el valor del id_profesor
                        SesionProfesor.getInstancia().setIdProfesor(profesor.getId_profesor());
                        SesionProfesor.getInstancia().setNombreProfesor(profesor.getNombres());
                        ventanas.mover("Matricula/ProfesorMatricula.fxml","Matricula profesor");

                    }else{
                        System.out.println("El usuario no está registrado como profesor");
                    }break;
                default:
                    System.out.println("Rol desconocido");
            }

        }
        else{
            System.out.println("Acceso denegado");
        }

    }


    @FXML
    public void registarUsuario(){
        ventanas.mover("RegistroUsuario.fxml","Registro");

    }



}
