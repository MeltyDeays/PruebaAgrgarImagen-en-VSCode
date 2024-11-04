package com.BackendSolucion.Backend.Repository;

import org.springframework.stereotype.Repository;
import com.BackendSolucion.Backend.Models.Estudiantes;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiantes, Integer> {
}
