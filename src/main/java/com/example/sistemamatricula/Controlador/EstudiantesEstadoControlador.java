package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Alerta.AlertaFX;
import com.example.sistemamatricula.Dao.UsuarioDao;
import com.example.sistemamatricula.Modelo.AlumnoDTO;
import com.example.sistemamatricula.Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EstudiantesEstadoControlador {
    @FXML
    private TableView<AlumnoDTO> tablaEstudiante;
    @FXML
    private TableColumn<AlumnoDTO,Integer> colIdUsuario;
    @FXML
    private TableColumn<AlumnoDTO,String> colNombre;
    @FXML
    private TableColumn<AlumnoDTO,String> colApellido;
    @FXML
    private TableColumn<AlumnoDTO,String> colEstado;

    UsuarioDao usuarioDao = new UsuarioDao();
    AlertaFX alertaFX= new AlertaFX();


    @FXML
    public void initialize(){
        //Vincular columans con la clase AlumnoDto
        colIdUsuario.setCellValueFactory(new PropertyValueFactory<>("id_alumno"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("nombreEstado"));

        cargarListaAlumnos();
    }

    private void cargarListaAlumnos(){
        ObservableList<AlumnoDTO> lista =
                FXCollections.observableArrayList(usuarioDao.mostrarUsuariosEstado());
        tablaEstudiante.setItems(lista);
    }

    public void suspenderUsuario(){
        AlumnoDTO seleccionado = tablaEstudiante.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            alertaFX.mostrarAlerta("Error","Seleccione una sección");
            return;
        }

        boolean exito = usuarioDao.suspenderUsuario(seleccionado.getId_alumno());

        if (exito) {
            alertaFX.mostrarAlerta("Bien","Cambiado a suspendido");
            cargarListaAlumnos();
        } else {
            alertaFX.mostrarAlerta("Error","orden sin exito");
        }

    }

    public void bloquearUsuario(){
        AlumnoDTO seleccionado = tablaEstudiante.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            alertaFX.mostrarAlerta("Error","Seleccione una sección");
            return;
        }

        boolean exito = usuarioDao.bloquearUsuario(seleccionado.getId_alumno());

        if (exito) {
            alertaFX.mostrarAlerta("Bien","bloqueado");
            cargarListaAlumnos();
        } else {
            alertaFX.mostrarAlerta("Error","orden sin exito");
        }
    }
    public void activarUsuario(){
        AlumnoDTO seleccionado = tablaEstudiante.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            alertaFX.mostrarAlerta("Error","Seleccione una sección");
            return;
        }

        boolean exito = usuarioDao.activarUsuario(seleccionado.getId_alumno());

        if (exito) {
            alertaFX.mostrarAlerta("Bien","Cambiado a activado");
            cargarListaAlumnos();
        } else {
            alertaFX.mostrarAlerta("Error","orden sin exito");
        }
    }



}
