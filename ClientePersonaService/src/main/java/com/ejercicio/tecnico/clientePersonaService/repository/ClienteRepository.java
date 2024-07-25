package com.ejercicio.tecnico.clientePersonaService.repository;

import com.ejercicio.tecnico.clientePersonaService.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}