package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Profesor;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfesorDao {

    public Profesor buscarPorUsuario(int idUsuario){
        String sql = " Select * from profesor where id_usuario=?";
        try (Connection conn = ConexionBd.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Profesor prof = new Profesor();
                prof.setId_profesor(rs.getInt("id_profesor"));
                prof.setNombres(rs.getString("nombres"));
                prof.setApellidos(rs.getString("apellidos"));
                prof.setEspecialidad(rs.getString("especialidad"));
                prof.setEmail(rs.getString("email"));
                prof.setTelefono(rs.getString("telefono"));
                prof.setUsuario(idUsuario);
                return prof;
            } else {
                return null;
            }

        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }

    public int  registrarProfesor(Profesor profesor){
        String sql = """
                
                Insert into profesor(nombres,apellidos,especialidad,email,telefono,id_usuario)
                values (?,?,?,?,?,?) 
                """;

        try(Connection connection= ConexionBd.getConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)
        ){

            preparedStatement.setString(1,profesor.getNombres());
            preparedStatement.setString(2,profesor.getApellidos());
            preparedStatement.setString(3, profesor.getEspecialidad());
            preparedStatement.setString(4,profesor.getEmail());
            preparedStatement.setString(5,profesor.getTelefono());
            preparedStatement.setInt(6,profesor.getUsuario());
           if(preparedStatement.executeUpdate()==1){
               ResultSet rs = preparedStatement.getGeneratedKeys();
               if (rs.next()){
                   return rs.getInt(1);
               }
           }
            return -1;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

}
