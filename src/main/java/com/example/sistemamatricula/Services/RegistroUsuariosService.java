package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.RegistroUsuarioDao;

public class RegistroUsuariosService {
    //llamada al Dao
    private final RegistroUsuarioDao registroDao = new RegistroUsuarioDao();

    public void registrarUsuario(String dni,String correo,String contrasena,String rol){

        if(dni==null || dni.isBlank()){
            throw new RuntimeException("Dni obligatorio");
        }

        int id_rol =convertirRol(rol);

        if(!correo.contains("@")){
            throw new RuntimeException("Correo no valido");
        }

        boolean registrado = registroDao.registro(dni,correo,contrasena,id_rol);
        if(!registrado){
            throw new RuntimeException("No se pudo registrar al usuario");
        }
    }

    public int convertirRol(String mensaje){

        return switch (mensaje.toLowerCase()){
            case "estudiante" -> 1;
            case "profesor" -> 2;
            default -> throw new RuntimeException("rol invalido");
        };
    }


}
