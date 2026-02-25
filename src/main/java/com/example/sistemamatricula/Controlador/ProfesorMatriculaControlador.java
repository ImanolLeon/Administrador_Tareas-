package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Alerta.AlertaFX;
import com.example.sistemamatricula.Dao.SeccionDao;
import com.example.sistemamatricula.Modelo.SeccionDTO;
import com.example.sistemamatricula.Sesion.SesionProfesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

public class ProfesorMatriculaControlador {
    @FXML
    private TableView<SeccionDTO> tablaSecciones;

    @FXML
    private TableColumn<SeccionDTO,String> colCurso;
    @FXML
    private TableColumn<SeccionDTO,Integer> colIdSeccion;
    @FXML
    private TableColumn<SeccionDTO,Time> colDia;
    @FXML
    private TableColumn<SeccionDTO, Time> colHoraInicio;
    @FXML
    private TableColumn<SeccionDTO,String> colHoraFin;
    @FXML
    private TableColumn<SeccionDTO,String> colAula;
    @FXML
    private TableColumn<SeccionDTO,String> colProfesor;
    private SeccionDao seccionDAO = new SeccionDao();
    AlertaFX alertaFX= new AlertaFX();
    int idProfesorLogueado = SesionProfesor.getInstancia().getIdProfesor();

    @FXML
    public void initialize() {

        // Vincular columnas con propiedades del DTO
        colIdSeccion.setCellValueFactory(new PropertyValueFactory<>("id_seccion"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
        colDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        colHoraInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        colHoraFin.setCellValueFactory(new PropertyValueFactory<>("horaFin"));
        colAula.setCellValueFactory(new PropertyValueFactory<>("nombreAula"));
        colProfesor.setCellValueFactory(new PropertyValueFactory<>("nombreProfesor"));

        cargarSecciones();


    }
    private void cargarSecciones() {
        ObservableList<SeccionDTO> lista =
                FXCollections.observableArrayList(seccionDAO.listarSecciones());

        tablaSecciones.setItems(lista);

    }

    public void registrarProfesor(){

        SeccionDTO seleccion = tablaSecciones.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            alertaFX.mostrarAlerta("Error","Seleccione una sección");
            return;
        }

       boolean exito = seccionDAO.asignarProfesor(seleccion.getId_seccion(),idProfesorLogueado);

        if (exito) {
            alertaFX.mostrarAlerta("Bien","Asignado correctamente");
            cargarSecciones();
        } else {
            alertaFX.mostrarAlerta("Error","La sección ya tiene profesor");
        }
    }

    public void eliminarRegistroMatricula(){
        SeccionDTO seleccionado = tablaSecciones.getSelectionModel().getSelectedItem();


        if (seleccionado == null) {
            alertaFX.mostrarAlerta("Error","Seleccione una sección");
            return;
        }
        boolean exito = seccionDAO.retirarProfesor(seleccionado.getId_seccion(),idProfesorLogueado);
        if(exito){
            alertaFX.mostrarAlerta("Se retiró correctamente ","Retirado");
            cargarSecciones();
        }else{
            alertaFX.mostrarAlerta("Error","No se puiede retirar de la seccion");
        }
    }


}
