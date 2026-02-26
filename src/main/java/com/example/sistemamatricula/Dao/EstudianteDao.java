package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Estudiante;
import com.example.sistemamatricula.Util.ConexionBd;
import javafx.util.converter.LocalDateStringConverter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;

public class EstudianteDao {
    public Estudiante buscarEstudiante(int id_usuario) {
        String consultaSql= "select * from estudiante where id_usuario=? ";
        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(consultaSql)){
            preparedStatement.setInt(1,id_usuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Estudiante estudiante = new Estudiante();
                estudiante.setId_estudiante(resultSet.getInt("id_estudiante"));
                estudiante.setNombres(resultSet.getString("nombres"));
                estudiante.setApellidos(resultSet.getString("apellidos"));
                estudiante.setTelefono(resultSet.getString("telefono"));
                estudiante.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento").toLocalDate());
                estudiante.setId_usuario(id_usuario);
                return  estudiante;
            }else{
                return null;
            }

        }catch (Exception e){
        throw  new RuntimeException(e);
        }

    }
}
