package com.ejercicio.tecnico.cuentaMovimientosService.repository;

import com.ejercicio.tecnico.cuentaMovimientosService.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaIdInAndFechaBetween(List<Long> cuentaIds, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}