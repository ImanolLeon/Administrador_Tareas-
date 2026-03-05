package com.example.sistemamatricula.Modelo;

import com.example.sistemamatricula.Estados.*;

public class Usuario {

    private int id_Usuario;
    private String dni;
    private int rol;
    private EstadoUsuario estado; //  ahora es objeto, no int
    private String correo;

    public Usuario(int id_Usuario, String dni, int rol, int idEstado, String correo) {
        this.id_Usuario = id_Usuario;
        this.dni = dni;
        this.rol = rol;
        this.estado = crearEstado(idEstado); //  convierte int → objeto estado
        this.correo = correo;
    }

    //  Convierte el id de la BD en el objeto estado correcto
    private EstadoUsuario crearEstado(int idEstado) {
        return switch (idEstado) {
            case 1 -> new EstadoActivo();
            case 2 -> new EstadoBloqueado();
            case 3 -> new EstadoSuspendido();
            default -> throw new IllegalArgumentException("Estado inválido");
        };
    }

    //  Métodos del patrón State
    public void suspender() {
        estado.suspender(this);
    }

    public void bloquear() {
        estado.bloquear(this);
    }

    public void activar() {
        estado.activar(this);
    }

    // Devuelve el id del estado actual (para guardar en BD)
    public int getIdEstado() {
        return estado.getIdEstado();
    }

    //  Permite cambiar el estado desde las clases Estado
    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    //  Getters normales
    public int getIdUsuario() {
        return id_Usuario;
    }

    public String getDni() {
        return dni;
    }

    public int getRol() {
        return rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}