package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.*;

public class RegistroUsuarioDao {
    private int id_usuarioRegistrado = 0;

    public boolean registro(String dni, String correo, String contrasena, int rol) {

        String sql = """
            INSERT INTO usuario (dni, correo, password, id_rol)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.setString(2, correo);
            ps.setString(3, contrasena);
            ps.setInt(4, rol);

          if(ps.executeUpdate()>1){
              ResultSet rs = ps.getGeneratedKeys();
              if(rs.next()){
                  id_usuarioRegistrado=rs.getInt(1);
              }
          }

          return ps.executeUpdate()>1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public int getId_usuarioRegistrado() {
        return id_usuarioRegistrado;
    }
}


