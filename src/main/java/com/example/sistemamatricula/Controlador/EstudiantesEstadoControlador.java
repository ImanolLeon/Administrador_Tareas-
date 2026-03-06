package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Alerta.AlertaFX;
import com.example.sistemamatricula.Dao.UsuarioDao;
import com.example.sistemamatricula.Escena.MovimientoVentanas;
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
    MovimientoVentanas movimientoVentanas = new MovimientoVentanas();


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


    public void activarUsuario() {

        AlumnoDTO seleccionado = tablaEstudiante.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            alertaFX.mostrarAlerta("Error","Seleccione una sección");
            return;
        }

        //  Crear Usuario real
        Usuario usuario = new Usuario(
                seleccionado.getId_alumno(),
                null,
                0,
                seleccionado.getIdEstado(),
                null
        );

        // Aplicar patrón State
        usuario.activar();

        //  Guardar nuevo estado en BD
        boolean exito = usuarioDao.actualizarEstado(
                usuario.getIdUsuario(),
                usuario.getIdEstado()
        );

        if (exito) {
            alertaFX.mostrarAlerta("Bien","Activado correctamente");
            cargarListaAlumnos();
        } else {
            alertaFX.mostrarAlerta("Error","No se pudo activar");
        }
    }

    public void bloquearUsuario() {

        AlumnoDTO seleccionado = tablaEstudiante.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            alertaFX.mostrarAlerta("Error","Seleccione una sección");
            return;
        }

        // Crear Usuario real con su estado actual
        Usuario usuario = new Usuario(
                seleccionado.getId_alumno(),
                null,
                0,
                seleccionado.getIdEstado(),
                null
        );

        //  Aplicar patrón State
        usuario.bloquear();

        // Guardar nuevo estado en BD
        boolean exito = usuarioDao.actualizarEstado(
                usuario.getIdUsuario(),
                usuario.getIdEstado()
        );

        if (exito) {
            alertaFX.mostrarAlerta("Bien","Bloqueado correctamente");
            cargarListaAlumnos();
        } else {
            alertaFX.mostrarAlerta("Error","No se pudo bloquear");
        }
    }
    public void suspenderUsuario(){

        AlumnoDTO seleccionado = tablaEstudiante.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            alertaFX.mostrarAlerta("Error","Seleccione una sección");
            return;
        }

        // rear objeto Usuario real
        Usuario usuario = new Usuario(
                seleccionado.getId_alumno(),
                null, // si no usas dni aquí
                0,    // si no usas rol aquí
                seleccionado.getIdEstado(),
                null  // si no usas correo aquí
        );



        //  Aplicar patrón State
        usuario.suspender();
        if (usuario.getIdEstado()==2){
            alertaFX.mostrarAlerta("Error","No se puede cambiar de bloqueado a suspendido");
            return;
        }

        // Guardar nuevo estado
        boolean exito = usuarioDao.actualizarEstado(
                usuario.getIdUsuario(),
                usuario.getIdEstado()
        );

        if (exito) {
            alertaFX.mostrarAlerta("Bien","Cambiado a suspendido");
            cargarListaAlumnos();
        } else {
            alertaFX.mostrarAlerta("Error","orden sin exito");
        }
    }

    public void retroceder(){
        movimientoVentanas.mover("Principal.fxml","Principal");
    }
}



