package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.RegistroEstudianteDao;
import com.example.sistemamatricula.Dao.RegistroUsuarioDao;

import java.time.LocalDate;

public class RegistroUsuariosService {
    //llamada al Dao
    private final RegistroUsuarioDao registroDao = new RegistroUsuarioDao();
    private final RegistroEstudianteDao registroEstudianteDao= new RegistroEstudianteDao();

    public int registrarUsuario(String dni,String correo,String contrasena,String rol){

        if(dni==null || dni.isBlank()){
            throw new RuntimeException("Dni obligatorio");
        }

        int id_rol =convertirRol(rol);

        if(!correo.contains("@")){
            throw new RuntimeException("Correo no valido");
        }

      int id_usuario= registroDao.registro(dni,correo,contrasena,id_rol);
        if(id_usuario<= 0){
            throw new RuntimeException("No se pudo registrar");
        }
        return id_usuario;

    }

    public int convertirRol(String mensaje){

        return switch (mensaje.toLowerCase()){
            case "estudiante" -> 2;
            case "profesor" -> 3;
            default -> throw new RuntimeException("rol invalido");
        };

    }

    public void registarDatosPersonales(String nombre , String apellido, String telefono, LocalDate fecha,int id_usuario)
    {
        if (nombre.isEmpty()){
            throw new RuntimeException("Error en el nombre estudiante");
        }
        if (apellido.isEmpty()){
            throw new RuntimeException("Error en el nombre apellido");
        }
        if (telefono.isEmpty()){
            throw new RuntimeException("Error en el teledono Estudiante");
        }
        if (fecha== null){

            throw  new RuntimeException("Error en la fecha de nacimiento del estudiante");
        }

        boolean estudianteRegistrado = registroEstudianteDao.ingresarDatosEstudiante(nombre,apellido,telefono,fecha,id_usuario);
        if (!estudianteRegistrado){
            throw  new RuntimeException("Error en el agregao del Estudiante");
        }

    }




}




