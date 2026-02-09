package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.MatriculaDao;
import com.example.sistemamatricula.Modelo.Matricula;

import java.time.LocalDate;
import java.util.List;

public class MatriculaService {
    private MatriculaDao  matriculaDao;

    public void matricular(int idEstudiante, List<Integer> cursos){

        Matricula matricula = new Matricula();
        matricula.setFecha(LocalDate.now());
        matricula.setId_estudiante(idEstudiante);

        matriculaDao.matricular(matricula);
    }
}
