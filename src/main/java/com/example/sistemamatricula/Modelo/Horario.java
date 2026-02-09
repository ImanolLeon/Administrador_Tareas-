package com.example.sistemamatricula.Modelo;

import java.time.LocalDateTime;

public class Horario {
    private int id_horario;
    private String dia;
    private LocalDateTime hora_inicio;
    private LocalDateTime hora_fin;
    private int id_profesor_Curso;

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalDateTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalDateTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalDateTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalDateTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getId_profesor_Curso() {
        return id_profesor_Curso;
    }

    public void setId_profesor_Curso(int id_profesor_Curso) {
        this.id_profesor_Curso = id_profesor_Curso;
    }
}
