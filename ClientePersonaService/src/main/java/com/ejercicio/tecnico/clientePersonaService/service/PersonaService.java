package com.ejercicio.tecnico.clientePersonaService.service;

import com.ejercicio.tecnico.clientePersonaService.entity.Persona;
import com.ejercicio.tecnico.clientePersonaService.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona updatePersona(Long id, Persona personaDetails) {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        persona.setNombre(personaDetails.getNombre());
        persona.setGenero(personaDetails.getGenero());
        persona.setEdad(personaDetails.getEdad());
        persona.setIdentificacion(personaDetails.getIdentificacion());
        persona.setDireccion(personaDetails.getDireccion());
        persona.setTelefono(personaDetails.getTelefono());
        return personaRepository.save(persona);
    }

    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }
}