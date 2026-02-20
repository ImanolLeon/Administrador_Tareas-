package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Dao.AulaDao;
import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Modelo.Aula;
import com.example.sistemamatricula.Services.AulaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AulaController {

    @FXML
    private TableView<Aula> tablaAulas;

    @FXML
    private TableColumn<Aula,String> colNombre;

    @FXML
    private TableColumn<Aula,Integer> colCapacidad;

    AulaService aulaService = new AulaService();
    AulaDao aulaDao = new AulaDao();
    MovimientoVentanas movimientoVentanas= new MovimientoVentanas();

    @FXML
    public void initialize(){
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        cargarCursos();
    }

    public void cargarCursos(){
        ObservableList<Aula> listaAula= FXCollections.observableArrayList(aulaDao.listarAulas());
        tablaAulas.setItems(listaAula);
    }

    public void eliminarTarea(){
        Aula seleccion= tablaAulas.getSelectionModel().getSelectedItem();
        if(seleccion==null){
            System.out.println("Seleccione un aula");
            return;
        }
        boolean eliminado = aulaDao.eliminarAula(seleccion);
        if (eliminado){
            tablaAulas.getItems().remove(seleccion);
        }else{
            System.out.println("no se pudo eliminar");
        }


    }

    public void retroceder(){
        movimientoVentanas.mover("Principal.fxml","Principañ");
    }
    public void agregarTarea(){
        movimientoVentanas.mover("Aula/CreacionAula.fxml","Crear aula");

    }
}
