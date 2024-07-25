package com.ejercicio.tecnico.cuentaMovimientosService.repository;

import com.ejercicio.tecnico.cuentaMovimientosService.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByClienteId(String clienteId);
}