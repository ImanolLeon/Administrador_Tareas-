package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.SeccionDao;
import com.example.sistemamatricula.Modelo.Seccion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SeccionService {
    SeccionDao seccionDao= new SeccionDao();

    public void agregarSeccion(
            int idCurso,
            int idAula,
            String dia,
            LocalTime horaInicio,
            LocalTime horaFin
    ) {
        if (idCurso <= 0) {
            throw new IllegalArgumentException("Curso inválido");
        }

        if (idAula <= 0) {
            throw new IllegalArgumentException("Aula inválida");
        }

        if (dia == null || dia.isBlank()) {
            throw new IllegalArgumentException("Día inválido");
        }

        if (horaInicio == null || horaFin == null) {
            throw new IllegalArgumentException("Horas inválidas");
        }

        if (horaFin.isBefore(horaInicio)) {
            throw new IllegalArgumentException("Hora fin no puede ser menor que hora inicio");
        }

        //Ver si hay conflicto
        if (seccionDao.existeConflictoHorario(idAula, dia, horaInicio, horaFin)) {
            throw new IllegalArgumentException("El aula ya está ocupada en ese horario");
        }

        Seccion seccion = new Seccion();
        seccion.setId_curso(idCurso);
        seccion.setId_aula(idAula);
        seccion.setDia(dia);
        seccion.setHora_inicio(horaInicio);
        seccion.setHora_fin(horaFin);

        seccionDao.agregarSeccion(seccion);

    }
    }
