package com.example.sistemamatricula.Estados;

import com.example.sistemamatricula.Modelo.Usuario;

public class EstadoBloqueado implements EstadoUsuario{
    @Override
    public void activar(Usuario usuario) {
        usuario.setEstado(new EstadoActivo());
    }

    @Override
    public void suspender(Usuario usuario) {
        System.out.println("Un usuario bloqueado no puede suspenderse");
    }

    @Override
    public void bloquear(Usuario usuario) {
        System.out.println("Ya está bloqueado");
    }

    @Override
    public int getIdEstado() {
        return 2;
    }
}
