package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Profesor;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ProfesorDao {

    public boolean registrarProfesor(Profesor profesor){
        String sql = """
                
                Insert into profesor(nombres,apellidos,especialidad,email,telefono,id_usuario)
                values (?,?,?,?,?,?) 
                """;

        try(Connection connection= ConexionBd.getConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setString(1,profesor.getNombres());
            preparedStatement.setString(2,profesor.getApellidos());
            preparedStatement.setString(3, profesor.getEspecialidad());
            preparedStatement.setString(4,profesor.getEmail());
            preparedStatement.setString(5,profesor.getTelefono());
            preparedStatement.setInt(6,profesor.getUsuario());
            return preparedStatement.executeUpdate()>0;

        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

}
