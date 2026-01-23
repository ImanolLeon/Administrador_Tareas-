package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Dao.UsuarioDao;
import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Modelo.Usuario;
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
            System.out.println("usuario ingresado , su rol es = " + usuario.getRol());
            ventanas.mover("Principal.fxml","Principal");
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
