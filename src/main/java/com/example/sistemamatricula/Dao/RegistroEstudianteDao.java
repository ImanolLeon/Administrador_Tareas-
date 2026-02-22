package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class RegistroEstudianteDao {

    public boolean ingresarDatosEstudiante(String nombre, String apellido, String telefono, LocalDate fecha_nacimiento,int id_usuario){
        String consultasql = """
            INSERT INTO estudiante (nombres, apellidos, telefono,fecha_nacimiento,id_usuario)
           
            VALUES (?, ?, ?, ?, ?)
                """;

        try(Connection connection = ConexionBd.getConexion();
            PreparedStatement rs = connection.prepareStatement(consultasql)){
            rs.setString(1,nombre);
            rs.setString(2,apellido);
            rs.setString(3,telefono);
            rs.setDate(4,Date.valueOf(fecha_nacimiento));
            rs.setInt(5,id_usuario);
            return rs.executeUpdate()==1;

        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }
}
