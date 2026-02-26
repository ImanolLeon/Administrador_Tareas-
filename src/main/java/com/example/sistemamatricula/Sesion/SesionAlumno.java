package com.example.sistemamatricula.Sesion;

public class SesionAlumno {
    private  static  SesionAlumno instancia;
    private int id_alumno;
    private String nombre_alumno;

    private SesionAlumno(){};

    public static SesionAlumno getInstance(){
        if (instancia == null){
            instancia= new SesionAlumno();
        }
        return instancia;
    }

    public static SesionAlumno getInstancia() {
        return instancia;
    }

    public static void setInstancia(SesionAlumno instancia) {
        SesionAlumno.instancia = instancia;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }
}
