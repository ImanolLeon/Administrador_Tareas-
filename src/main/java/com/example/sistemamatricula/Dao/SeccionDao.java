package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Curso;
import com.example.sistemamatricula.Modelo.Profesor;
import com.example.sistemamatricula.Modelo.Seccion;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;

public class SeccionDao {

    public int agregarSeccion(Seccion seccion){
        String sql= """
                insert into seccion (id_curso,dia,hora_inicio,hora_fin,id_aula)
                values (?,?,?,?,?)
                """;

        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setInt(1,seccion.getId_curso());
            preparedStatement.setString(2,seccion.getDia());
            preparedStatement.setTime(3, Time.valueOf(seccion.getHora_inicio()));
            preparedStatement.setTime(4,Time.valueOf(seccion.getHora_fin()));
            preparedStatement.setInt(5,seccion.getId_aula());

            return preparedStatement.executeUpdate();


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public int asignarProfesor (Seccion seccion, Profesor profesor){
        String sql= """
                update seccion set id_profesor =?
                where id_seccion=?
                """;
        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,seccion.getId_seccion());
            preparedStatement.setInt(2,profesor.getId_profesor());

            return  preparedStatement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public boolean existeConflictoHorario(
            int idAula,
            String dia,
            LocalTime horaInicio,
            LocalTime horaFin
    ){
        String sql = """
        SELECT COUNT(*)
        FROM seccion
        WHERE id_aula = ?
        AND dia = ?
        AND (
            hora_inicio < ?
            AND hora_fin > ?
        )
    """;

        try(Connection con = ConexionBd.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, idAula);
            ps.setString(2, dia);
            ps.setTime(3, Time.valueOf(horaFin));
            ps.setTime(4, Time.valueOf(horaInicio));

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt(1) > 0;
            }

        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return false;
    }

}
