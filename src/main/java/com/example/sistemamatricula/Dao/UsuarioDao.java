package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Usuario;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {
    public Usuario login(String dni, String contrasena ) {
        //Buscar
        String sql = """
                    SELECT u.id_usuario, u.dni,
                           r.nombre AS rol,
                           e.nombre AS estado
                    FROM usuario u
                    JOIN rol r ON u.id_rol = r.id_rol
                    JOIN estado e ON u.id_estado = e.id_estado
                    WHERE u.dni = ?
                      AND u.password = ?
                      AND e.nombre = 'ACTIVO'
                """;
        Connection con = ConexionBd.getConexion();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,dni);
            ps.setString(2,contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("dni"),
                        rs.getString("rol"),
                        rs.getInt("estado"),
                        rs.getString("correo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en login");
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

