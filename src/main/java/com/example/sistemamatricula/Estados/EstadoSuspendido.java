package com.example.sistemamatricula.Estados;


import com.example.sistemamatricula.Modelo.Usuario;

public class EstadoSuspendido implements EstadoUsuario {

    @Override
    public void activar(Usuario usuario) {
        usuario.setEstado(new EstadoActivo());
    }

    @Override
    public void suspender(Usuario usuario) {
        System.out.println("Ya está suspendido");
    }

    @Override
    public void bloquear(Usuario usuario) {
        usuario.setEstado(new EstadoBloqueado());
    }

    @Override
    public int getIdEstado() {
        return 3;
    }
}
