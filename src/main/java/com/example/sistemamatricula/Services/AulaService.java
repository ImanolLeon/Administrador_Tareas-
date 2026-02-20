package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.AulaDao;
import com.example.sistemamatricula.Modelo.Aula;

public class AulaService {

    AulaDao aulaDao= new AulaDao();
    public void registrarAula(String nombre,int cantidadMaxima){

        if(nombre==null || nombre.isBlank()){
            throw new RuntimeException("Nombre incorrecto");
        }
        if(cantidadMaxima>40 || cantidadMaxima <0){
            throw new RuntimeException("Error en cantidad de alumnos");
        }

        Aula aula = new Aula();
        aula.setNombre(nombre);
        aula.setCapacidad(cantidadMaxima);
        int variable = aulaDao.agregarAula(aula);
        if (variable!= 0){
            System.out.println("Aula registrada");
        }


    }
}
