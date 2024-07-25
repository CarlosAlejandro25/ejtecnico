package com.ejercicio.tecnico.cuentaMovimientosService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cuenta ID es obligatoria")
    private Long cuentaId;

    private LocalDateTime fecha;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    @Pattern(regexp = "^(Deposito|Retiro)$", message = "El tipo de movimiento solo puede ser 'Deposito' o 'Retiro'")
    private String tipoMovimiento;

    @NotNull(message = "El valor es obligatorio")
    private Double valor;

    private double saldo;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        if ("Deposito".equals(this.tipoMovimiento) && valor < 0) {
            throw new IllegalArgumentException("El valor del depósito debe ser un valor positivo");
        } else if ("Retiro".equals(this.tipoMovimiento) && valor > 0) {
            throw new IllegalArgumentException("El valor del retiro debe ser un valor negativo");
        }
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}