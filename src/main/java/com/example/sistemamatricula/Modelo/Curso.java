package com.example.sistemamatricula.Modelo;

public class Curso {
private int id_curso;
private  String nombre;
private int creditos;
private  int cantidad_estudiantes;

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getCantidad_estudiantes() {
        return cantidad_estudiantes;
    }

    public void setCantidad_estudiantes(int cantidad_estudiantes) {
        this.cantidad_estudiantes = cantidad_estudiantes;
    }
}
