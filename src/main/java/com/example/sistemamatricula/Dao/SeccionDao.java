package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Seccion;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;

public class SeccionDao {

    public int agregarSeccion(Seccion seccion){
        String sql= """
                insert into seccion (id_curso,dia,hora_inicio,hora_fin,capacidad_maxima,id_profesor)
                values (?,?,?,?,?,?)
                """;

        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setInt(1,seccion.getId_curso());
            preparedStatement.setString(2,seccion.getDia());
            preparedStatement.setTime(3, Time.valueOf(seccion.getHora_inicio()));
            preparedStatement.setTime(4,Time.valueOf(seccion.getHora_fin()));
            preparedStatement.setInt(5,seccion.getCantidad_maxima());
            preparedStatement.setInt(6,seccion.getId_profesor());

            return preparedStatement.executeUpdate();


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
