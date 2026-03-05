package com.example.sistemamatricula.Modelo;

import java.time.LocalTime;

public class CursosMatriculadosDTO {
    private int id_matricula;
    private String nombreCurso;
    private  String dia;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private String nombreProfesor;
    private String aula;

    public CursosMatriculadosDTO(String aula, String nombreProfesor,
                                 LocalTime hora_fin, LocalTime hora_inicio,
                                 String dia, String nombreCurso, int id_matricula) {
        this.aula = aula;
        this.nombreProfesor = nombreProfesor;
        this.hora_fin = hora_fin;
        this.hora_inicio = hora_inicio;
        this.dia = dia;
        this.nombreCurso = nombreCurso;
        this.id_matricula = id_matricula;
    }

    public int getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(int id_matricula) {
        this.id_matricula = id_matricula;
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

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
}
