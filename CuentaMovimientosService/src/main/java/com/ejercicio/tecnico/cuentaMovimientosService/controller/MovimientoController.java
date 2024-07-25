package com.ejercicio.tecnico.cuentaMovimientosService.controller;

import com.ejercicio.tecnico.cuentaMovimientosService.entity.Movimiento;
import com.ejercicio.tecnico.cuentaMovimientosService.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @GetMapping("/{id}")
    public Optional<Movimiento> getMovimientoById(@PathVariable Long id) {
        return movimientoService.getMovimientoById(id);
    }

    @PostMapping
    public ResponseEntity<?> createMovimiento(@Valid @RequestBody Movimiento movimiento) {
        if ("Deposito".equals(movimiento.getTipoMovimiento()) && movimiento.getValor() <= 0) {
            return ResponseEntity.badRequest().body("El valor del depósito debe ser un valor positivo");
        } else if ("Retiro".equals(movimiento.getTipoMovimiento()) && movimiento.getValor() >= 0) {
            return ResponseEntity.badRequest().body("El valor del retiro debe ser un valor negativo");
        }
        return ResponseEntity.ok(movimientoService.createMovimiento(movimiento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovimiento(@PathVariable Long id, @Valid @RequestBody Movimiento movimientoDetails) {
        if ("Deposito".equals(movimientoDetails.getTipoMovimiento()) && movimientoDetails.getValor() <= 0) {
            return ResponseEntity.badRequest().body("El valor del depósito debe ser un valor positivo");
        } else if ("Retiro".equals(movimientoDetails.getTipoMovimiento()) && movimientoDetails.getValor() >= 0) {
            return ResponseEntity.badRequest().body("El valor del retiro debe ser un valor negativo");
        }
        return ResponseEntity.ok(movimientoService.updateMovimiento(id, movimientoDetails));
    }

    @DeleteMapping("/{id}")
    public void deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
    }
}