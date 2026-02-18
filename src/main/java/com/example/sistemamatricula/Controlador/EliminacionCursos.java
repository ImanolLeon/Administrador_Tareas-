package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Dao.CursoDao;
import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Modelo.Curso;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EliminacionCursos {

    //nombre de la tabla
    @FXML
    private TableView<Curso> tablaCursos;

    //Columna de las tablas con <Clase,TipoDato>
    @FXML
    private TableColumn<Curso,Integer> colId;
    @FXML
    private TableColumn<Curso,String> colNombre;
    @FXML
    private TableColumn<Curso,Integer> colCreditos;


    private final CursoDao cursoDao= new CursoDao();
    MovimientoVentanas movimientoVentanas = new MovimientoVentanas();

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id_curso"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCreditos.setCellValueFactory(new PropertyValueFactory<>("creditos"));
        cargarCursos();
    }


    public void cargarCursos(){
        ObservableList<Curso> lista = FXCollections.observableArrayList(cursoDao.listaCursos());
        tablaCursos.setItems(lista);
    }



    public void eliminarCurso(){
        Curso seleccionado =  tablaCursos.getSelectionModel().getSelectedItem();
        if (seleccionado==null){
            System.out.println("Seleccionar curso");
            return;
        }
        boolean eliminado= cursoDao.eliminarCurso(seleccionado);

        if(eliminado){
            //SI SE ELIMINA VA A REMOVERLO DE LA LISTA , el metodo de arriba lo elimina
            //lo que hace este que vamos a escribir es sacarlo de la lista
            tablaCursos.getItems().remove(seleccionado);
        }else{
            System.out.println("No se puedo eliminar");
        }

    }

    public void retroceder(){
        movimientoVentanas.mover("Principal.fxml","Principal");
    }
}
