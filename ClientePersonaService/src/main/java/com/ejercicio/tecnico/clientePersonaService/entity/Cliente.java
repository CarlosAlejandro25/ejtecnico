package com.ejercicio.tecnico.clientePersonaService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.io.Serializable;

@Entity
public class Cliente extends Persona implements Serializable {
    @NotBlank(message = "El ID del cliente es obligatorio")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,12}$", message = "El ID del cliente debe tener entre 8 y 12 caracteres alfanuméricos")
    private String clienteId;

    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "La contraseña debe tener al menos una mayúscula, una minúscula, un número y un carácter especial")
    private String contrasena;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public void setEdad(Integer edad) {
        super.setEdad(edad);
    }

    @Override
    public void setIdentificacion(String identificacion) {
        super.setIdentificacion(identificacion);
    }

    @Override
    public void setDireccion(String direccion) {
        super.setDireccion(direccion);
    }

    @Override
    public void setTelefono(String telefono) {
        super.setTelefono(telefono);
    }

    // Getters and Setters
    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean isEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}