package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Dao.MatriculaDao;
import com.example.sistemamatricula.Modelo.CursosMatriculadosDTO;
import com.example.sistemamatricula.Modelo.SeccionDTO;
import com.example.sistemamatricula.Sesion.SesionAlumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Time;

public class CursosMatriculadosController {
    @FXML
    private TableView<CursosMatriculadosDTO> tablaSecciones;
    @FXML
    private TableColumn<CursosMatriculadosDTO,String> colCurso;
    @FXML
    private TableColumn<CursosMatriculadosDTO, Time> colDia;
    @FXML
    private TableColumn<CursosMatriculadosDTO, Time> colHoraInicio;
    @FXML
    private TableColumn<CursosMatriculadosDTO,String> colHoraFin;
    @FXML
    private TableColumn<CursosMatriculadosDTO,String> colAula;
    @FXML
    private TableColumn<CursosMatriculadosDTO,String> colProfesor;

    MatriculaDao matriculaDao = new MatriculaDao();
    int id_estudiante= SesionAlumno.getInstance().getId_alumno();

    @FXML
    public void initialize() {

        // Vincular columnas con propiedades del DTO
        colCurso.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
        colDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        colHoraInicio.setCellValueFactory(new PropertyValueFactory<>("hora_inicio"));
        colHoraFin.setCellValueFactory(new PropertyValueFactory<>("hora_fin"));
        colAula.setCellValueFactory(new PropertyValueFactory<>("aula"));
        colProfesor.setCellValueFactory(new PropertyValueFactory<>("nombreProfesor"));

        cargarSecciones();


    }

    private void cargarSecciones() {
        ObservableList<CursosMatriculadosDTO> lista =
                FXCollections.observableArrayList(matriculaDao.cursosMatriculadosEstudiantes(id_estudiante));
        tablaSecciones.setItems(lista);

    }
    public  void eliminarRegistroMatriculo(){}
    public void retroceder(){}
}
