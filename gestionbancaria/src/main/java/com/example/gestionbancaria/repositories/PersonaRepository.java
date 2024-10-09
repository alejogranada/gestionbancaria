package com.example.gestionbancaria.repositories;

import com.example.gestionbancaria.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByIdentificacion(String identificacion);
}