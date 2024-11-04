package com.BackendSolucion.Backend.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.BackendSolucion.Backend.Models.Estudiantes;
import com.BackendSolucion.Backend.Repository.IEstudianteServices;
import java.util.HashMap;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/EstudianteController")

public class EstudianteController {
    @Autowired
    IEstudianteServices estudianteServices;



@PostMapping(value = "/insertarestudiante", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<Estudiantes> createestudiante(
    @RequestParam("estudiante") String estudiante,
    @RequestParam("fotografia") MultipartFile fotografia) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
        Estudiantes Oestudiante = objectMapper.readValue(estudiante, Estudiantes.class);
        Estudiantes createdEstudiante = estudianteServices.createestudiantes(Oestudiante, fotografia);
        return new ResponseEntity<>(createdEstudiante, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



@DeleteMapping("/eliminarestudiantes/{id}")
public ResponseEntity<HashMap<String, String>> deleteestudiante(@PathVariable int id) {
    try {
        HashMap<String, String> response = estudianteServices.deleteestudiante(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
@PutMapping("/actualizarestudiantes/{id}")
    public ResponseEntity<Estudiantes> updateEstudiante(@PathVariable int id, @RequestBody Estudiantes estudiante) {
        try {
            Estudiantes updatedEstudiante = estudianteServices.updateEstudiante(id, estudiante);
            return new ResponseEntity<>(updatedEstudiante, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarestudiantes")
    public ResponseEntity<List<Estudiantes>> listEstudiantes() {
        try {
            List<Estudiantes> estudiantes = estudianteServices.listEstudiantes();
            return new ResponseEntity<>(estudiantes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

