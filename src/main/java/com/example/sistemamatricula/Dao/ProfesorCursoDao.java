package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Curso;
import com.example.sistemamatricula.Modelo.Profesor;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProfesorCursoDao {

    public boolean registarProfesorCurso(int idProfesor, int idCurso){
        String sql = """
                Insert into ProfesorCurso (id_profesor,id_curso)
                values (?,?)
                """;

        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement preparedStatement= connection.prepareStatement(sql)){

            preparedStatement.setInt(1,idProfesor);
            preparedStatement.setInt(2,idCurso);

            return preparedStatement.executeUpdate()>1;

        }catch (Exception e){
            throw  new RuntimeException(e);
        }

    }

    public boolean eliminarProfesorCurso(int idProfesorCurso){

        String sql= """
                Delete from profesor_horario 
                where id_profesor_curso = ?
                """;

        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement preparedStatement= connection.prepareStatement(sql) ){

            preparedStatement.setInt(1,idProfesorCurso);
            return preparedStatement.executeUpdate()>1;
             }catch (Exception e){
            throw new RuntimeException(e);
        }

    }


}
