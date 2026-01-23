package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class RegistroEstudianteDao {
    RegistroUsuarioDao id_usuario = new RegistroUsuarioDao();
    int id_estudiante= id_usuario.getId_usuarioRegistrado();

    public boolean ingresarDatosEstudiante(String nombre, String apellido, String telefono, Date fecha_nacimiento){
        String consultasql = """
            INSERT INTO estudiante (nombre, apellidos, telefono,fecha_nacimiento,id_usuario)
           
            VALUES (?, ?, ?, ?, ?)
                """;

        try(Connection connection = ConexionBd.getConexion();
            PreparedStatement rs = connection.prepareStatement(consultasql)){
            rs.setString(1,nombre);
            rs.setString(2,apellido);
            rs.setString(3,telefono);
            rs.setDate(4,fecha_nacimiento);
            rs.setInt(5,id_estudiante);
            return rs.executeUpdate()>1;

        }
        catch (Exception e){
            throw new RuntimeException(e);

        }
    }
}
