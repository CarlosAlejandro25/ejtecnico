package com.ejercicio.tecnico.cuentaMovimientosService.report;

import com.ejercicio.tecnico.cuentaMovimientosService.entity.Cuenta;
import com.ejercicio.tecnico.cuentaMovimientosService.entity.Movimiento;
import com.ejercicio.tecnico.cuentaMovimientosService.repository.CuentaRepository;
import com.ejercicio.tecnico.cuentaMovimientosService.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public ReporteEstadoCuenta generarReporteEstadoCuenta(String clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        // Obtener las cuentas del cliente
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

        // Obtener los movimientos de las cuentas del cliente en el rango de fechas
        List<Movimiento> movimientos = movimientoRepository.findByCuentaIdInAndFechaBetween(
                cuentas.stream().map(Cuenta::getId).collect(Collectors.toList()), fechaInicio, fechaFin);

        // Crear el reporte
        ReporteEstadoCuenta reporte = new ReporteEstadoCuenta();
        reporte.setClienteId(clienteId);
        reporte.setCuentas(cuentas);
        reporte.setMovimientos(movimientos);

        return reporte;
    }
}