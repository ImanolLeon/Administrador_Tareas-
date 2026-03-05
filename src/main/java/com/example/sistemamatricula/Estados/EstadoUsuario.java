package com.example.sistemamatricula.Estados;

import com.example.sistemamatricula.Modelo.Usuario;

public interface EstadoUsuario {

    void activar(Usuario usuario);
    void suspender(Usuario usuario);
    void bloquear(Usuario usuario);

    int getIdEstado(); // Para sincronizar con BD
}
