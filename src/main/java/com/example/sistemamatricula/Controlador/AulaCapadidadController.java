package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Dao.AulaDao;
import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Modelo.Aula;
import com.example.sistemamatricula.Modelo.AulaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class AulaCapadidadController {
    @FXML
    private TableView<AulaDTO> tablaAulas;
    @FXML
    private TableColumn<AulaDTO,Integer> colSeccion;
    @FXML
    private TableColumn<AulaDTO,String> colCurso;
    @FXML
    private TableColumn<AulaDTO,String> colAula;
    @FXML
    private TableColumn<AulaDTO,Integer> colCapacidad;
    @FXML
    private TableColumn<AulaDTO,Integer> colAlumnos;
    AulaDao aulaDao = new AulaDao();
    MovimientoVentanas movimientoVentanas = new MovimientoVentanas();

    public void initialize(){
        colSeccion.setCellValueFactory(new PropertyValueFactory<>("seccion"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
        colAula.setCellValueFactory(new PropertyValueFactory<>("aula"));
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        colAlumnos.setCellValueFactory(new PropertyValueFactory<>("alumnosInscritos"));
        cargarCursos();
    }
    public void cargarCursos(){
        ObservableList<AulaDTO> listaAula= FXCollections.observableArrayList(aulaDao.listaAulas());
        tablaAulas.setItems(listaAula);
    }

    public  void retroceder(){
        movimientoVentanas.mover("Aula/Aula.fxml","Aulas");
    }
}
