package com.ejercicio.tecnico.cuentaMovimientosService.service;

import com.ejercicio.tecnico.cuentaMovimientosService.entity.Cliente;
import com.ejercicio.tecnico.cuentaMovimientosService.entity.Cuenta;
import com.ejercicio.tecnico.cuentaMovimientosService.repository.CuentaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CuentaService {

    @Autowired
    public CuentaRepository cuentaRepository; // Cambiar a public

    @Autowired
    public ObjectMapper objectMapper; // Cambiar a public

    // Mapa para almacenar clientes válidos
    public ConcurrentHashMap<String, Cliente> clientesValidos = new ConcurrentHashMap<>(); // Cambiar a public

    // Método para recibir y procesar mensajes desde RabbitMQ
    @RabbitListener(queues = "clienteQueue")
    public void recibirMensaje(String clienteJson) {
        try {
            Cliente cliente = objectMapper.readValue(clienteJson, Cliente.class);
            // Almacenar el cliente en el mapa de clientes válidos
            clientesValidos.put(cliente.getClienteId(), cliente);
            System.out.println("Cliente recibido y almacenado: " + cliente.getClienteId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> getCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    public Cuenta createCuenta(Cuenta cuenta, String clienteId) {
        // Validar si el cliente existe antes de crear la cuenta
        if (esClienteValido(clienteId)) {
            cuenta.setClienteId(clienteId);
            return cuentaRepository.save(cuenta);
        } else {
            throw new RuntimeException("Cliente no válido");
        }
    }

    public Cuenta updateCuenta(Long id, Cuenta cuentaDetails) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
        cuenta.setEstado(cuentaDetails.isEstado());
        return cuentaRepository.save(cuenta);
    }

    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    private boolean esClienteValido(String clienteId) {
        // Validar si el cliente existe en el mapa de clientes válidos
        return clientesValidos.containsKey(clienteId);
    }
}