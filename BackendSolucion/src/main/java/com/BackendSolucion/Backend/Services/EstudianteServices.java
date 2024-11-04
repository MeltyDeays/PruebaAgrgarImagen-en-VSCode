package com.BackendSolucion.Backend.Services;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import com.BackendSolucion.Backend.Models.Estudiantes;
import com.BackendSolucion.Backend.Repository.EstudianteRepository;
import com.BackendSolucion.Backend.Repository.IEstudianteServices;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EstudianteServices implements IEstudianteServices {
        private static final Logger LOGGER = LoggerFactory.getLogger(EstudianteServices.class);
    
        @Autowired
        EstudianteRepository estudianteRepository;
    
        @Override
        public Estudiantes createestudiantes(Estudiantes estudiante, MultipartFile fotografia) {
            try {
                byte [] FotoBytes = fotografia.getBytes();
                Path ruta = Paths.get("C:\\Users\\Celes\\Downloads\\BackendSolucion (2)\\BackendSolucion\\src\\main\\resources\\Fotografias\\" + fotografia.getOriginalFilename());
                Files.write(ruta, FotoBytes);
                estudiante.setUbicacionFotografia(ruta.toString());
                return estudianteRepository.save(estudiante);
            } catch (Exception e) {
                LOGGER.error("Error while creating estudiante: {}", e.getMessage());
                throw new RuntimeException("Error creating user");
            }
        }
        @Override
        public HashMap<String, String> deleteestudiante(int Id) {
            try {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully!");
        estudianteRepository.deleteById(Id);
        return response;
        } catch (Exception e) {
        LOGGER.error("Error while deleting user: {}", e.getMessage());
        throw new RuntimeException("Error deleting user");
        }
    }

    @Override
    public Estudiantes updateEstudiante(int id, Estudiantes estudiante) {
        try {
            // Verifica si el estudiante existe
            if (!estudianteRepository.existsById(id)) {
                throw new RuntimeException("Estudiante no encontrado");
            }
            // Establece el ID del estudiante
            estudiante.setP_id(id); // Usa el método setP_id
            return estudianteRepository.save(estudiante);
        } catch (Exception e) {
            LOGGER.error(String.format("Error while updating estudiante: %s", e.getMessage()));
            throw new RuntimeException("Error updating user");
        }
    }
    

        @Override
        public List<Estudiantes> listEstudiantes() {
            try {
        return estudianteRepository.findAll();
            } catch (Exception e) {
                LOGGER.error(String.format("Error while deleting estudiante: %s", e.getMessage()));

        throw new RuntimeException("Error listing users");
    }
}

}
