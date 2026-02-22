package com.example.sistemamatricula.Sesion;



public class SesionProfesor {
    private static SesionProfesor instancia;
    private int idProfesor;
    private String nombreProfesor;

    private SesionProfesor(){}

    public static SesionProfesor getInstancia(){
        if(instancia == null){
            instancia = new SesionProfesor();
        }
        return instancia;
    }

    public int getIdProfesor() { return idProfesor; }
    public void setIdProfesor(int idProfesor) { this.idProfesor = idProfesor; }

    public String getNombreProfesor() { return nombreProfesor; }
    public void setNombreProfesor(String nombreProfesor) { this.nombreProfesor = nombreProfesor; }
}