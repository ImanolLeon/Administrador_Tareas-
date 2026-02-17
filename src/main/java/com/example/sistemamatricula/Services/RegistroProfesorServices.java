package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.ProfesorDao;
import com.example.sistemamatricula.Modelo.Profesor;

public class RegistroProfesorServices {

    ProfesorDao profesorDao = new ProfesorDao();

    public void registrarProfesor(
            String nombres,String apellidos,
            String especialidad,String email,String telefono,
            int idUsuario
    ){

        //Validaciones

        if(nombres==null || nombres.isBlank()){
            throw new RuntimeException("Nombres obligatorios");
        }
        if(especialidad ==null || especialidad.isBlank()){
            throw  new RuntimeException("especialidad obligatoria");
        }
        if(email==null|| email.isBlank()){
            throw new RuntimeException("email obligatorio");
        }
        if (telefono==null|| telefono.isBlank()){
            throw new RuntimeException("telefono necesario");
        }

        if(idUsuario<=0){
            throw new RuntimeException("id de usuario no valido");
        }

        //Construccion del modelo
        Profesor profesor= new Profesor();
        profesor.setNombres(nombres);
        profesor.setApellidos(apellidos);
        profesor.setEspecialidad(especialidad);
        profesor.setEmail(email);
        profesor.setTelefono(telefono);
        profesor.setUsuario(idUsuario);

        //guardar Dao
        boolean registrado= profesorDao.registrarProfesor(profesor);
        if(!registrado){
            throw new RuntimeException("no se pudo registrar");
        }else{
            System.out.println("CURSO REGISTRADO!!");
        }

    }

}
