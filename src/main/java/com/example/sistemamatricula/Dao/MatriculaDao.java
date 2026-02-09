package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Matricula;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MatriculaDao {


    public int matricular(Matricula matricula){
        String sql = "INSERT INTO matricula (fecha, id_estudiante) VALUES (?, ?)";
        try(Connection  connection= ConexionBd.getConexion();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setDate(1, Date.valueOf(matricula.getFecha()));
            ps.setInt(2, matricula.getId_estudiante());


        }catch (Exception e){
            System.out.println(e);
        }

        return -1;
    }

}
