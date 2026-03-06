package com.example.sistemamatricula.Modelo;

public class AulaDTO {
    private int seccion;
    private String nombreCurso;
    private String aula;
    private int capacidad;
    private  int alumnosInscritos;

    public AulaDTO(int seccion, String nombreCurso, String aula, int capacidad, int alumnosInscritos) {
        this.seccion = seccion;
        this.nombreCurso = nombreCurso;
        this.aula = aula;
        this.capacidad = capacidad;
        this.alumnosInscritos = alumnosInscritos;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getAlumnosInscritos() {
        return alumnosInscritos;
    }

    public void setAlumnosInscritos(int alumnosInscritos) {
        this.alumnosInscritos = alumnosInscritos;
    }
}
