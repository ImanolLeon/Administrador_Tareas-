package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Curso;
import com.example.sistemamatricula.Modelo.Profesor;
import com.example.sistemamatricula.Modelo.Seccion;
import com.example.sistemamatricula.Modelo.SeccionDTO;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SeccionDao {


    public List<SeccionDTO> listaSeccines (){
        List<SeccionDTO> secciones = new ArrayList<>();
        String sql = """
                select seccion.id_seccion as seccion,
                seccion.dia as dia ,seccion.hora_inicio ,seccion.hora_fin,
                curso.nombre as curso,profesor.nombres as profesor ,aula.nombre as aula
                from seccion
                inner join curso 	
                	on seccion.id_curso=curso.id_curso
                inner join aula
                	on seccion.id_aula= aula.id_aula
                inner join profesor
                	on seccion.id_profesor = profesor.id_profesor
                """;


    }


    public  boolean eliminarSeccion(int seccion_id){
        String sql = "delete from seccion where id_seccion=?";
        try (Connection connection= ConexionBd.getConexion();
             PreparedStatement preparedStatement= connection.prepareStatement(sql)){

            preparedStatement.setInt(1,seccion_id);
            return  preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    public boolean asignarProfesor( int idSeccion, int idProfesor){

        String sql = """
        UPDATE seccion
        SET id_profesor = ?
        WHERE id_seccion = ?
        AND id_profesor IS NULL
        """;

        try (Connection conn = ConexionBd.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProfesor);
            ps.setInt(2, idSeccion);

            int filas = ps.executeUpdate();

            return filas > 0; // si actualizó, se asignó

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    public boolean retirarProfesor(int idSeccion, int idProfesor) {

        String sql = """
        UPDATE seccion
        SET id_profesor = NULL
        WHERE id_seccion = ?
        AND id_profesor = ?
        """;

        try (Connection conn = ConexionBd.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSeccion);
            ps.setInt(2, idProfesor);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    public List<SeccionDTO> listarSecciones(){
        List<SeccionDTO> secciones = new ArrayList<>();
        String sql = """ 
                SELECT s.id_seccion, c.nombre AS 
                nombre_curso, s.dia, s.hora_inicio, s.
                hora_fin, a.nombre AS nombre_aula,
                 COALESCE(p.nombres, 'Sin asignar') 
                 AS nombre_profesor FROM seccion s 
                 JOIN curso c ON s.id_curso = c.id_curso
                  JOIN aula a ON s.id_aula = a.id_aula 
                  LEFT JOIN profesor p 
                  ON s.id_profesor = p.id_profesor """;
        try (Connection connection = ConexionBd.getConexion();
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
               ResultSet rs= preparedStatement.executeQuery()){


            while (rs.next()) {

                SeccionDTO seccion = new SeccionDTO(
                        rs.getInt("id_seccion"),
                        rs.getString("nombre_curso"),
                        rs.getString("dia"),
                        rs.getTime("hora_inicio"),
                        rs.getTime("hora_fin"),
                        rs.getString("nombre_aula"),
                        rs.getString("nombre_profesor")
                );

                secciones.add(seccion);
            }
        }catch (Exception e){
            throw  new RuntimeException(e);
        }

        return secciones;
    }

    public int agregarSeccion(Seccion seccion){
        String sql= """
                insert into seccion (id_curso,dia,hora_inicio,hora_fin,id_aula)
                values (?,?,?,?,?)
                """;

        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setInt(1,seccion.getId_curso());
            preparedStatement.setString(2,seccion.getDia());
            preparedStatement.setTime(3, Time.valueOf(seccion.getHora_inicio()));
            preparedStatement.setTime(4,Time.valueOf(seccion.getHora_fin()));
            preparedStatement.setInt(5,seccion.getId_aula());

            return preparedStatement.executeUpdate();


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public int asignarProfesor (Seccion seccion, Profesor profesor){
        String sql= """
                update seccion set id_profesor =?
                where id_seccion=?
                """;
        try (Connection connection = ConexionBd.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,seccion.getId_seccion());
            preparedStatement.setInt(2,profesor.getId_profesor());

            return  preparedStatement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public boolean existeConflictoHorario(
            int idAula,
            String dia,
            LocalTime horaInicio,
            LocalTime horaFin
    ){
        String sql = """
        SELECT COUNT(*)
        FROM seccion
        WHERE id_aula = ?
        AND dia = ?
        AND (
            hora_inicio < ?
            AND hora_fin > ?
        )
    """;

        try(Connection con = ConexionBd.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, idAula);
            ps.setString(2, dia);
            ps.setTime(3, Time.valueOf(horaFin));
            ps.setTime(4, Time.valueOf(horaInicio));

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt(1) > 0;
            }

        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return false;
    }

}
