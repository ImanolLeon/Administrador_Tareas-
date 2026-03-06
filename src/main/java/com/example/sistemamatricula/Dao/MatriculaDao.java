package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.CursosMatriculadosDTO;
import com.example.sistemamatricula.Modelo.Matricula;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDao {

    public boolean eliminarMatricula(int idEstudiante, int idSeccion){

        String sql = "DELETE FROM matricula WHERE id_estudiante = ? AND id_seccion = ?";

        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idEstudiante);
            ps.setInt(2, idSeccion);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CursosMatriculadosDTO> cursosMatriculadosEstudiantes(int id_estudiante){
        List<CursosMatriculadosDTO> cursos= new ArrayList<>();
        String consultaSql= """
                SELECT
                id_matricula,
                c.nombre AS curso,
                s.dia,
                s.hora_inicio,
                s.hora_fin,
                p.nombres AS profesor,
                a.nombre AS aula
                FROM matricula m
                JOIN seccion s ON m.id_seccion = s.id_seccion
                JOIN curso c ON s.id_curso = c.id_curso
                JOIN profesor p ON s.id_profesor = p.id_profesor
                JOIN aula a ON s.id_aula = a.id_aula
                WHERE m.id_estudiante = ?;
                """;
        try (Connection connection= ConexionBd.getConexion();
              PreparedStatement preparedStatement= connection.prepareStatement(consultaSql)){

            preparedStatement.setInt(1,id_estudiante);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                CursosMatriculadosDTO cursosMatriculados= new CursosMatriculadosDTO(
                        resultSet.getString("aula"),
                        resultSet.getString("profesor"),
                        resultSet.getTime("hora_fin"),
                        resultSet.getTime("hora_inicio"),
                        resultSet.getString("dia"),
                        resultSet.getString("curso"),
                        resultSet.getInt("id_matricula")
                );
                cursos.add(cursosMatriculados);
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return cursos;

    }

    public int matricular(LocalDate fecha,int id_estudiante,int seccion){
        String sql = "INSERT INTO matricula (fecha, id_estudiante,id_seccion) VALUES (?, ?,?)";
        try(Connection  connection= ConexionBd.getConexion();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setDate(1, Date.valueOf(fecha));
            ps.setInt(2, id_estudiante);
            ps.setInt(3,seccion);

            return ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }

        return -1;
    }


    public int contarCursosEstudiante(int idEstudiante) {
        int cantidad = 0;
        String sql = "SELECT COUNT(*) FROM matricula WHERE id_estudiante = ?";

        try {
            Connection con = ConexionBd.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idEstudiante);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cantidad = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cantidad;
    }

    public boolean yaTieneCurso(int idEstudiante, int idCurso) {

        String sql = """
        SELECT 1
        FROM matricula m
        JOIN seccion s ON m.id_seccion = s.id_seccion
        WHERE m.id_estudiante = ?
        AND s.id_curso = ?
    """;

        try {
            Connection con = ConexionBd.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idEstudiante);
            ps.setInt(2, idCurso);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean hayCapacidad(int idSeccion) {

        String sql = """
        SELECT a.capacidad, COUNT(m.id_estudiante) AS inscritos
        FROM seccion s
        JOIN aula a ON s.id_aula = a.id_aula
        LEFT JOIN matricula m ON m.id_seccion = s.id_seccion
        WHERE s.id_seccion = ?
        GROUP BY a.capacidad
    """;

        try {
            Connection con = ConexionBd.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idSeccion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int capacidad = rs.getInt("capacidad");
                int inscritos = rs.getInt("inscritos");

                return inscritos < capacidad;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public int obtenerCursoDeSeccion(int idSeccion) {

        String sql = "SELECT id_curso FROM seccion WHERE id_seccion = ?";

        try (Connection con = ConexionBd.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSeccion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_curso");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}
