package com.example.sistemamatricula.Controlador;

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



}
