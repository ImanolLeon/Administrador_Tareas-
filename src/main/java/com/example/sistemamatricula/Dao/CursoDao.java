package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Curso;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CursoDao {

    public int devolverId(String nombreCurso) {
        String sql = """
                SELECT id_curso
                FROM curso
                WHERE nombre = ?;
                
                """;

        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            //Enviar el parametro
            preparedStatement.setString(1, nombreCurso);
            //ejecutar consulta
            ResultSet rs = preparedStatement.executeQuery();
            //leer resultado

            if (rs.next()) {
                return rs.getInt("id_curso");
            };



        } catch (Exception e) {
            new RuntimeException(e);
        }

        //No existe
        return -1;
    }


    public  int registrarCurso(Curso curso){
        String sql = """
                Insert into curso(nombre,creditos,cantidadEstudiantes)
                values (?,?,?)
                """;
        try(Connection connection= ConexionBd.getConexion();
            PreparedStatement preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, curso.getNombre());
            preparedStatement.setInt(2, curso.getCreditos());
            preparedStatement.setInt(3, curso.getCantidad_estudiantes());

            if ( preparedStatement.executeUpdate()==1){
                ResultSet rs= preparedStatement.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1);
                }

            }



        }catch (Exception e){
            throw new  RuntimeException(e);
        }
       return -1;

    }


    public boolean eliminarCurso(Curso curso){

        String sql = "Delete from Curso where id_curso = ?";
        try(Connection connection= ConexionBd.getConexion();
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1,curso.getId_curso());
            return preparedStatement.executeUpdate()==1;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
