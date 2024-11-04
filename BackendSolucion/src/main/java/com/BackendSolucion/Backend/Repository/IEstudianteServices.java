package com.BackendSolucion.Backend.Repository;

import com.BackendSolucion.Backend.Models.Estudiantes;
import java.util.HashMap;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;


public interface IEstudianteServices {
    public Estudiantes createestudiantes(Estudiantes estudiante, MultipartFile fotografia);
    
    public HashMap<String, String> deleteestudiante (int Id);

    public Estudiantes updateEstudiante(int id, Estudiantes estudiante);
    
    public List<Estudiantes> listEstudiantes();
}

