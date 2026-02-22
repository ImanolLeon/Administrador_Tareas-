package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Alerta.AlertaFX;
import com.example.sistemamatricula.Dao.SeccionDao;
import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Modelo.Seccion;
import com.example.sistemamatricula.Modelo.SeccionDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Time;

public class SeccionesControlador {
    @FXML
    private TableView<SeccionDTO> tablaSecciones;

    @FXML
    private TableColumn<SeccionDTO,String> colCurso;
    @FXML
    private TableColumn<SeccionDTO,Integer> colIdSeccion;
    @FXML
    private TableColumn<SeccionDTO, Time> colDia;
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
    MovimientoVentanas movimientoVentanas= new MovimientoVentanas();

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
    public void eliminarSeccion(){

        SeccionDTO seleccion = tablaSecciones.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            alertaFX.mostrarAlerta("Error","Seleccione una sección");
            return;
        }

        boolean exito = seccionDAO.eliminarSeccion(seleccion.getId_seccion());
        if (exito){
            alertaFX.mostrarAlerta("exito","Eñliminado corectamente");
            cargarSecciones();
        }else{
            alertaFX.mostrarAlerta("ERROR","NO SE PUDO ELIMINAR");
        }
    }

    public  void retroceder(){
        movimientoVentanas.mover("Principal.fxml","Principal");
    }
}
