package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.DetalleMatriculaDao;
import com.example.sistemamatricula.Dao.MatriculaDao;
import com.example.sistemamatricula.Modelo.Curso;
import com.example.sistemamatricula.Modelo.DetalleMatricula;
import com.example.sistemamatricula.Modelo.Matricula;

import java.time.LocalDate;
import java.util.List;

public class MatriculaService {
    private MatriculaDao  matriculaDao= new MatriculaDao();
    private  DetalleMatriculaDao detalleDao = new DetalleMatriculaDao();

    public String matricularEstudiante(int idEstudiante, int idSeccion) {

        int cursos = matriculaDao.contarCursosEstudiante(idEstudiante);

        if (cursos >= 7) {
            return "El estudiante ya tiene 7 cursos";
        }

        int idCurso = matriculaDao.obtenerCursoDeSeccion(idSeccion);

        if (matriculaDao.yaTieneCurso(idEstudiante, idCurso)) {
            return "El estudiante ya está matriculado en este curso";

        }
        if (matriculaDao.hayCruceHorario(idEstudiante, idSeccion)) {
            return "El horario se cruza con otro curso";

        }

        if (!matriculaDao.hayCapacidad(idSeccion)) {
            return "El aula está llena";
        }

        int r = matriculaDao.matricular(LocalDate.now(), idEstudiante, idSeccion);

        if (r > 0) {
            return "Matricula realizada correctamente";
        }

        return "Error al matricular";
    }


}
