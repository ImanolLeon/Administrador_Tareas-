package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.DetalleMatriculaDao;
import com.example.sistemamatricula.Dao.MatriculaDao;
import com.example.sistemamatricula.Modelo.Curso;
import com.example.sistemamatricula.Modelo.DetalleMatricula;
import com.example.sistemamatricula.Modelo.Matricula;

import java.time.LocalDate;
import java.util.List;

public class MatriculaService {
    private MatriculaDao  matriculaDao;
    private  DetalleMatriculaDao detalleDao;

    public void matricular(int idEstudiante, List<Integer> cursos){

        Matricula matricula = new Matricula();
        matricula.setFecha(LocalDate.now());
        matricula.setId_estudiante(idEstudiante);

        int idMatricula=matriculaDao.matricular(matricula);
        if (idMatricula <= 0) {
            throw new RuntimeException("No se pudo registrar la matrícula");
        }else{
            for(int id_Curso : cursos){
                DetalleMatricula d = new DetalleMatricula();
                d.setId_matricula(idMatricula);
                d.setId_curso(id_Curso);
                detalleDao.registrarDetalleMatricula(d);
            }
        }



    }
}
