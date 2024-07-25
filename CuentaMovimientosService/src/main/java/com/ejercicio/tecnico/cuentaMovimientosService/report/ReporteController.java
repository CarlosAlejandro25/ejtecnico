package com.ejercicio.tecnico.cuentaMovimientosService.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ReporteEstadoCuenta generarReporte(@RequestParam String clienteId, @RequestParam String fechaInicio, @RequestParam String fechaFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio, formatter);
        LocalDateTime fin = LocalDateTime.parse(fechaFin, formatter);

        return reporteService.generarReporteEstadoCuenta(clienteId, inicio, fin);
    }
}