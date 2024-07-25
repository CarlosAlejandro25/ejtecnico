package com.ejercicio.tecnico.cuentaMovimientosService.service;

import com.ejercicio.tecnico.cuentaMovimientosService.entity.Cuenta;
import com.ejercicio.tecnico.cuentaMovimientosService.entity.Movimiento;
import com.ejercicio.tecnico.cuentaMovimientosService.exception.SaldoNoDisponibleException;
import com.ejercicio.tecnico.cuentaMovimientosService.repository.CuentaRepository;
import com.ejercicio.tecnico.cuentaMovimientosService.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimiento> getMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    public Movimiento createMovimiento(Movimiento movimiento) {
        // Obtener la cuenta asociada
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(movimiento.getCuentaId());

        if (cuentaOpt.isPresent()) {
            Cuenta cuenta = cuentaOpt.get();
            double saldoActual = cuenta.getSaldoInicial();

            // Verificar si el saldo es suficiente para el movimiento
            if (saldoActual + movimiento.getValor() < 0) {
                throw new SaldoNoDisponibleException("Saldo no disponible");
            }

            // Actualizar el saldo basado en el valor del movimiento
            saldoActual += movimiento.getValor();

            // Actualizar el saldo de la cuenta
            cuenta.setSaldoInicial(saldoActual);
            cuentaRepository.save(cuenta);

            // Establecer el saldo en el movimiento
            movimiento.setSaldo(saldoActual);
            movimiento.setFecha(LocalDateTime.now());

            return movimientoRepository.save(movimiento);
        } else {
            throw new RuntimeException("Cuenta no encontrada");
        }
    }

    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        movimiento.setCuentaId(movimientoDetails.getCuentaId());
        movimiento.setFecha(movimientoDetails.getFecha());
        movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
        movimiento.setValor(movimientoDetails.getValor());
        movimiento.setSaldo(movimientoDetails.getSaldo());
        return movimientoRepository.save(movimiento);
    }

    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }
}