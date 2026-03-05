package com.example.sistemamatricula.Modelo;

public class AlumnoDTO {
    private int id_alumno;
    private String nombre;
    private String apellido;
    private String nombreEstado;
    private int idEstado; //  importante

    public AlumnoDTO(int id_alumno, String nombre, String apellido,
                     String nombreEstado, int idEstado) {
        this.id_alumno = id_alumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreEstado = nombreEstado;
        this.idEstado = idEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }
    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}
