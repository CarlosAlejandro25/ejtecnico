package com.ejercicio.tecnico.clientePersonaService.repository;

import com.ejercicio.tecnico.clientePersonaService.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}