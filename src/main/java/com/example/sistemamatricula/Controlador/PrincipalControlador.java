package com.example.sistemamatricula.Controlador;

import com.example.sistemamatricula.Escena.MovimientoVentanas;

public class PrincipalControlador {
    MovimientoVentanas movimientoVentanas = new MovimientoVentanas();

    //solamente de datos que necesitas , más no de botones ni label


    public void eliminarCurso(){
    movimientoVentanas.mover("Curso/EliminacionCursos.fxml","Lista de cursos");
    }

    public void crearCurso(){
        movimientoVentanas.mover("Curso/CreacionCurso.fxml","Creación Curso");
    }

    public void irAulas(){
        movimientoVentanas.mover("Aula/Aula.fxml","Aulas");
    }

    public void irSecciones(){
        movimientoVentanas.mover("Seccion/Seccion.fxml","Seccion");
    }
}
