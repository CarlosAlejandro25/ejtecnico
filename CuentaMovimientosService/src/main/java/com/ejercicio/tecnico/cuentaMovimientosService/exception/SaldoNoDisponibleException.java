package com.ejercicio.tecnico.cuentaMovimientosService.exception;

public class SaldoNoDisponibleException extends RuntimeException {
    public SaldoNoDisponibleException(String message) {
        super(message);
    }
}