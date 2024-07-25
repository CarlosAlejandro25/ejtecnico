package com.ejercicio.tecnico.clientePersonaService.controller;

import com.ejercicio.tecnico.clientePersonaService.entity.Persona;
import com.ejercicio.tecnico.clientePersonaService.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/personas")
@Validated
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        return personaService.getPersonaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Persona> createPersona(@Valid @RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.createPersona(persona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @Valid @RequestBody Persona personaDetails) {
        return ResponseEntity.ok(personaService.updatePersona(id, personaDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }
}