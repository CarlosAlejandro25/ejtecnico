package com.ejercicio.tecnico.cuentaMovimientosService.report;

import com.ejercicio.tecnico.cuentaMovimientosService.entity.Cuenta;
import com.ejercicio.tecnico.cuentaMovimientosService.entity.Movimiento;

import java.util.List;

public class ReporteEstadoCuenta {
    private String clienteId;
    private List<Cuenta> cuentas;
    private List<Movimiento> movimientos;

    // Getters and Setters
    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}