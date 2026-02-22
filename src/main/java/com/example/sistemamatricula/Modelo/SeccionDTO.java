package com.example.sistemamatricula.Modelo;

import java.sql.Time;

public class SeccionDTO {

    private int id_seccion;
    private String nombreCurso;
    private String dia;
    private Time horaInicio;
    private Time horaFin;
    private String nombreAula;
    private String nombreProfesor;

    // Constructor
    public SeccionDTO(int idSeccion, String nombreCurso, String dia,
                      Time horaInicio, Time horaFin,
                      String nombreAula, String nombreProfesor) {
        this.id_seccion = idSeccion;
        this.nombreCurso = nombreCurso;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.nombreAula = nombreAula;
        this.nombreProfesor = nombreProfesor;
    }

    // Getters

    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }
}

