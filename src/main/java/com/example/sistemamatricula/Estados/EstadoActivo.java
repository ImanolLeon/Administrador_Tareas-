package com.example.sistemamatricula.Estados;

import com.example.sistemamatricula.Modelo.Usuario;

public class EstadoActivo implements EstadoUsuario {


    @Override
    public void activar(Usuario usuario) {
        System.out.println("El usuario ya está activo");
    }

    @Override
    public void suspender(Usuario usuario) {
        usuario.setEstado(new EstadoSuspendido());
    }

    @Override
    public void bloquear(Usuario usuario) {
        usuario.setEstado(new EstadoBloqueado());
    }

    @Override
    public int getIdEstado() {
        return 1;
    }
}