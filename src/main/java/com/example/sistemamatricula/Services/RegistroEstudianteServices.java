package com.example.sistemamatricula.Services;

import com.example.sistemamatricula.Dao.RegistroEstudianteDao;

import java.sql.Date;
import java.time.LocalDate;

public class RegistroEstudianteServices {
    private final RegistroEstudianteDao registroDao = new RegistroEstudianteDao();

    public void registarDatosPersonales(String nombre , String apellido, String telefono, LocalDate fecha)
   {
       if (!nombre.isEmpty()){
           throw new RuntimeException("Error en el nombre estudiante");
       }
       if (!apellido.isEmpty()){
           throw new RuntimeException("Error en el nombre apellido");
       }
       if (!telefono.isEmpty()){
           throw new RuntimeException("Error en el teledono Estudiante");
       }
      if (fecha== null){

          throw  new RuntimeException("Error en la fecha de nacimiento del estudiante");
      }

      boolean estudianteRegistrado = registroDao.ingresarDatosEstudiante
              (nombre,apellido,telefono,fecha);
      if (!estudianteRegistrado){
          throw  new RuntimeException("Error en el agregao del Estudiante");
      }



   }

}
