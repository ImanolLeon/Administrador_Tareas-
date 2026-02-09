package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Curso;
import com.example.sistemamatricula.Modelo.DetalleMatricula;
import com.example.sistemamatricula.Modelo.Matricula;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DetalleMatriculaDao {
public int registrarDetalleMatricula( DetalleMatricula matricula){

    String sql= "INSERT INTO detalle_matricula (id_matricula,id_curso) values (?,?)";
    try (Connection connection = ConexionBd.getConexion();
         PreparedStatement ps = connection.prepareStatement(sql)){

             ps.setInt(1,matricula.getId_matricula());
             ps.setInt(2,matricula.getId_curso());

    }catch (Exception e){
        System.out.println(e);
    }
    return -1;
}


}
