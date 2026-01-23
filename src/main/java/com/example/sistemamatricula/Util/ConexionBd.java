package com.example.sistemamatricula.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBd {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_matricula?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String Pass = "imanolleon";

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL,USER,Pass);
        }catch (SQLException e){
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
            return null;
        }

    }}


