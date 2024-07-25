package com.ejercicio.tecnico.clientePersonaService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "El nombre solo puede contener letras y un espacio entre palabras")
    private String nombre;

    @NotBlank(message = "El género es obligatorio")
    @Pattern(regexp = "^(Masculino|Femenino)$", message = "El género solo puede ser Masculino o Femenino")
    private String genero;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18")
    @Max(value = 65, message = "La edad máxima es 65")
    private Integer edad;

    @NotBlank(message = "La identificación es obligatoria")
    @Pattern(regexp = "^\\d{10}$", message = "La identificación debe tener 10 dígitos")
    private String identificacion;

    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener 10 dígitos")
    private String telefono;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}