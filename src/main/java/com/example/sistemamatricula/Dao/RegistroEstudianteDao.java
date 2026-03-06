package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class RegistroEstudianteDao {

    public int ingresarDatosEstudiante(String nombre, String apellido, String telefono,
                                       LocalDate fecha_nacimiento, int id_usuario) {

        String consultasql = """
        INSERT INTO estudiante (nombres, apellidos, telefono, fecha_nacimiento, id_usuario)
        VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement ps = connection.prepareStatement(consultasql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, telefono);
            ps.setDate(4, Date.valueOf(fecha_nacimiento));
            ps.setInt(5, id_usuario);

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se insertó el estudiante");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // id_estudiante generado
            } else {
                throw new RuntimeException("No se pudo obtener el ID del estudiante");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
