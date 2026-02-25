package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.AlumnoDTO;
import com.example.sistemamatricula.Modelo.Usuario;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    public Usuario login(String dni, String contrasena) {

        String sql = """
      SELECT u.id_usuario,
                         u.dni,
                         u.correo,
                         u.id_rol   AS rol,
                         u.id_estado AS estado
                  FROM usuario u
                  WHERE u.dni = ?
                    AND u.password = ?
                    AND u.id_estado = 1;
                
    """;

        try (Connection con = ConexionBd.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("dni"),
                        rs.getInt("rol"),
                        rs.getInt("estado"),
                        rs.getString("correo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    public List<AlumnoDTO>  mostrarUsuariosEstado (){
        List<AlumnoDTO> alumnos = new ArrayList<>();
        String consultaSql = """
                select usuario.id_usuario ,
                estudiante.nombres as nombre ,
                estudiante.apellidos as apellido,
                estado.nombre as estado
                from usuario
                inner join estudiante
                on usuario.id_usuario = estudiante.id_usuario
                inner join estado
                on usuario.id_estado = estado.id_estado
                """;
        try (Connection connection = ConexionBd.getConexion();
              PreparedStatement preparedStatement = connection.prepareStatement(consultaSql);
              ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    AlumnoDTO alumnoDTO = new AlumnoDTO(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("estado")
                    ) ;
                    alumnos.add(alumnoDTO);
                }

                return  alumnos;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }

    }
}

