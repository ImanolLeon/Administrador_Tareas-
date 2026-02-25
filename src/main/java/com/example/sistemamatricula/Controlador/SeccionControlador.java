package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Alerta.AlertaFX;
import com.example.sistemamatricula.Dao.AulaDao;
import com.example.sistemamatricula.Dao.CursoDao;
import com.example.sistemamatricula.Escena.MovimientoVentanas;
import com.example.sistemamatricula.Modelo.Aula;
import com.example.sistemamatricula.Modelo.Curso;
import com.example.sistemamatricula.Services.SeccionService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SeccionControlador {

    @FXML
    private ComboBox<Curso> comboCurso;
    @FXML
    private ComboBox<Aula> comboAula;
    @FXML
    private TextField txtDia;
    @FXML
    private TextField txtHoraInicio;
    @FXML
    private TextField txtHoraFin;

    CursoDao cursoDao = new CursoDao();
    AulaDao aulaDao = new AulaDao();
    AlertaFX alerta = new AlertaFX();
    SeccionService service= new SeccionService();
    MovimientoVentanas movimientoVentanas = new MovimientoVentanas();


    @FXML
    public void initialize() {
        cargarCursos();
        cargarAulas();
    }


    private void cargarCursos() {
        comboCurso.getItems().setAll(cursoDao.listaCursos());
    }


    private void cargarAulas() {
        //lista todos los elementos de aula , basandonos en el toString
        comboAula.getItems().setAll(aulaDao.listarAulas());
    }



    public void crearSeccion(){

            try {
                //selecciona el curso (comboCurso.getValue())
                //devuelve su id --int (getId_curso())
                int idCurso = comboCurso.getValue().getId_curso();
                int idAula = comboAula.getValue().getId_aula();
                String dia = txtDia.getText();

                //Formato hora para que acepte 3:00 como 03:00
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

                LocalTime horaInicio = LocalTime.parse(txtHoraInicio.getText(), formatter);
                LocalTime horaFin = LocalTime.parse(txtHoraFin.getText(), formatter);
                if (horaFin.isBefore(horaInicio)) {
                    alerta.mostrarAlerta("Error", "La hora fin no puede ser menor que la hora inicio");
                    return;
                }


                service.agregarSeccion(
                        idCurso,
                        idAula,
                        dia,
                        horaInicio,
                        horaFin
                );

                alerta.mostrarAlerta("Éxito", "Sección creada correctamente");

            } catch (Exception e) {

                alerta.mostrarAlerta("Error", e.getMessage());
            }
        }
        public void retroceder(){
        movimientoVentanas.mover("Principal.fxml", "Principal");
        }



    }

