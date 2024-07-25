package com.ejercicio.tecnico.cuentaMovimientosService.controller;

import com.ejercicio.tecnico.cuentaMovimientosService.entity.Cuenta;
import com.ejercicio.tecnico.cuentaMovimientosService.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @GetMapping("/{id}")
    public Optional<Cuenta> getCuentaById(@PathVariable Long id) {
        return cuentaService.getCuentaById(id);
    }

    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@Valid @RequestBody Cuenta cuenta, @RequestParam String clienteId) {
        return ResponseEntity.ok(cuentaService.createCuenta(cuenta, clienteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @Valid @RequestBody Cuenta cuentaDetails) {
        return ResponseEntity.ok(cuentaService.updateCuenta(id, cuentaDetails));
    }

    @DeleteMapping("/{id}")
    public void deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
    }
}