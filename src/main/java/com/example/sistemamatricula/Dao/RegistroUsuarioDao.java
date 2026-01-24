package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.*;

public class RegistroUsuarioDao {


    public int registro(String dni, String correo, String contrasena, int rol) {

        String sql = """
            INSERT INTO usuario (dni, correo, password, id_rol)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dni);
            ps.setString(2, correo);
            ps.setString(3, contrasena);
            ps.setInt(4, rol);

          if (ps.executeUpdate()==1){
              ResultSet rs = ps.getGeneratedKeys();
              if (rs.next()){
                  return rs.getInt(1);
              }

          }

          return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}


