package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.ProfesorDao;
import com.example.sistemamatricula.Modelo.Profesor;
import com.example.sistemamatricula.Sesion.SesionProfesor;

public class RegistroProfesorServices {

    ProfesorDao profesorDao = new ProfesorDao();

    public int registrarProfesor(
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
       int  id_profesor= profesorDao.registrarProfesor(profesor);
        //Obetener usuario
        SesionProfesor.getInstancia().setIdProfesor(profesor.getId_profesor());



        if(id_profesor<=0){
            throw new RuntimeException("no se pudo registrar");

        }else{
            System.out.println("Profesor REGISTRADO!!");
            SesionProfesor.getInstancia().setIdProfesor(id_profesor);

        }
        return id_profesor;

    }

}
