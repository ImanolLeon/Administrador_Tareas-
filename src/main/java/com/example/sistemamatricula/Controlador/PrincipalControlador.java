package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Escena.MovimientoVentanas;

public class PrincipalControlador {
    MovimientoVentanas movimientoVentanas = new MovimientoVentanas();

    //solamente de datos que necesitas , más no de botones ni label


    public void eliminarCurso(){

    }

    public void crearCurso(){
        movimientoVentanas.mover("Curso/CreacionCurso.fxml","Creación Curso");
    }
}
