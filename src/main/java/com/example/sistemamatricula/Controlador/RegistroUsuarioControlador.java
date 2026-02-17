package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Services.RegistroUsuariosService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegistroUsuarioControlador {
    @FXML
    private ToggleGroup Grupo1;

    @FXML
    private TextField txt_dni;

    @FXML
    private TextField txt_correo;

    @FXML
    private PasswordField txt_contrasena;

    private final RegistroUsuariosService registroUsuariosService= new RegistroUsuariosService();

    private MovimientoVentanas movimientoVentanas= new MovimientoVentanas();
    @FXML
    public void registrarDatos(){

        String dni =txt_dni.getText();
        String correo = txt_correo.getText();
        String contrasena = txt_contrasena.getText();
        if (Grupo1.getSelectedToggle()==null){
            System.out.println("debe seleccionar un rol");
            return;
        }
        //Seleccionamos el radio button con respecto al grupo
        //donde está
        RadioButton seleccionado =
                (RadioButton) Grupo1.getSelectedToggle();
        String rolString = seleccionado.getText().toLowerCase();




        try{
            int id_usuario=registroUsuariosService.registrarUsuario(dni,correo,contrasena,rolString);
            System.out.println("SE REGISTRÓ");
            FXMLLoader loader;

            //Veremos si el rol es estudiante o profesor
            if (rolString.equalsIgnoreCase("estudiante")){
                loader = new FXMLLoader(getClass().getResource("/com/example/sistemamatricula/RegistroDatosEstudiantes.fxml"));

                Parent root = loader.load();

                RegistroDatosEstudiantesController controller = loader.getController();
                //Obtiene id
                controller.setId_usuario(id_usuario);
                Stage stage = (Stage) txt_dni.getScene().getWindow();
                stage.setScene(new Scene(root));
            } else if (rolString.equalsIgnoreCase("profesor")) {

                loader = new FXMLLoader(getClass().getResource("/com/example/sistemamatricula/RegistroDatosProfesor.fxml"));

                Parent root = loader.load();

                RegistroProfesorControlador controller = loader.getController();
                //El id debe esatr en el controlador porque lo está creando para esa variable
                controller.setIdUsuario(id_usuario);
                Stage stage = (Stage) txt_dni.getScene().getWindow();
                stage.setScene(new Scene(root));

            }


        }catch (Exception e){
            System.out.println("Error al registrar usuario");

        }




    }


}
