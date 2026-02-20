package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Aula;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AulaDao {

    public int agregarAula(Aula aula){
        String sql = "insert into aula(nombre,capacidad) values (?,?)";
        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,aula.getNombre());
            preparedStatement.setInt(2,aula.getCapacidad());

            return preparedStatement.executeUpdate();

        }catch (Exception e){
            throw  new RuntimeException(e);
        }

    }

    public  boolean eliminarAula(Aula aula){
        String sql = "delete from aula where id_aula = ?";
        try(Connection connection= ConexionBd.getConexion();
            PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setInt(1,aula.getId_aula());
        return preparedStatement.executeUpdate()==1;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }


    public List<Aula> listarAulas(){
        List<Aula> aulas = new ArrayList<>();
        String sql = "select * from aula";

        try (Connection connection = ConexionBd.getConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet set = preparedStatement.executeQuery())
        {
            while (set.next()){
                Aula aula= new Aula();
                aula.setId_aula(set.getInt("id_aula"));
                aula.setNombre(set.getString("nombre"));
                aula.setCapacidad(set.getInt("capacidad"));
                aulas.add(aula);
            }


        }catch (Exception e){
            throw  new RuntimeException(e);
        }
        return aulas;

    }

}
