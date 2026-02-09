package com.example.sistemamatricula.Dao;

public class CursoDao {
    public int devolverId(String nombreCurso){
    String sql = """
            SELECT id_curso
            FROM curso
            WHERE nombre = ?;
            
            """;


    }
}
