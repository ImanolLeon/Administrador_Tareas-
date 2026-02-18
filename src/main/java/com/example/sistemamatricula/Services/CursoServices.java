package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.CursoDao;
import com.example.sistemamatricula.Modelo.Curso;

public class CursoServices {

    CursoDao cursoDao= new CursoDao();

    public void registrarCurso(String nombre, int creditos){

        if (nombre==null || nombre.isBlank()){
            throw new RuntimeException("Error en el nombre");
        }
        if (creditos>10 || creditos==0){
            throw new RuntimeException("Créditos inválidos");
        }

        Curso curso= new Curso();
        curso.setNombre(nombre);
        curso.setCreditos(creditos);

       int validacion = cursoDao.registrarCurso(curso);;
       if (validacion <=0){
           throw new RuntimeException("Erros al registrar");
       }
    }

}
