package com.example.sistemamatricula.Modelo;

public class Usuario {
    private  int id_Usuario;
    private String dni;
    private int rol;
    private int estado;
    private String correo;

    public Usuario(int id_Usuario, String dni, int rol, int estado,String correo) {
        this.id_Usuario = id_Usuario;
        this.dni = dni;
        this.rol = rol;
        this.estado = estado;
        this.correo=correo;
    }
    public int getIdUsuario() {
        return id_Usuario;
    }

    public String getDni() {
        return dni;
    }

    public int getRol() {
        return rol;
    }

    public int getEstado() {
        return estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
