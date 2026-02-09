package com.example.sistemamatricula.Dao;

import com.example.sistemamatricula.Modelo.Curso;
import com.example.sistemamatricula.Modelo.Horario;
import com.example.sistemamatricula.Util.ConexionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HorarioDao {
    public List<Horario> verHorariosPorCurso(Curso curso){

        List<Horario> lista = new ArrayList<>();
    String consulta = """
            SELECT\s
                c.id_curso,
                c.nombre AS curso,
                p.nombres AS profesor,
                p.apellidos,
                h.dia,
                h.hora_inicio,
                h.hora_fin
            FROM profesor_curso pc
            JOIN curso c ON pc.id_curso = c.id_curso
            JOIN profesor p ON pc.id_profesor = p.id_profesor
            JOIN horario h ON pc.id_profesor_curso = h.id_profesor_curso
            WHERE c.id_curso = ?;
            
            """;
        try(Connection connection = ConexionBd.getConexion();
            PreparedStatement ps = connection.prepareStatement(consulta)){

            ps.setInt(1,curso.getId_curso());
            ResultSet  rs=ps.executeQuery();

            if(rs.next()){
                Horario horario= new Horario();
                horario.setId_horario(rs.getInt("id_horario"));
                horario.setDia(rs.getNString("dia"));
                horario.setHora_inicio(rs.getTimestamp("hora_inicio").toLocalDateTime());
                horario.setHora_fin(rs.getTimestamp("hora_fin").toLocalDateTime());
                horario.setId_profesor_Curso(rs.getInt("id_profesor_curso"));
                lista.add(horario);
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return lista;

    }



}
