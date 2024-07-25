package com.ejercicio.tecnico.clientePersonaService.service;

import com.ejercicio.tecnico.clientePersonaService.entity.Cliente;
import com.ejercicio.tecnico.clientePersonaService.repository.ClienteRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        Cliente savedCliente = clienteRepository.save(cliente);
        enviarMensaje(savedCliente);
        return savedCliente;
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setClienteId(clienteDetails.getClienteId());
        cliente.setContrasena(clienteDetails.getContrasena());
        cliente.setEstado(clienteDetails.isEstado());
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public void enviarMensaje(Cliente cliente) {
        try {
            String clienteJson = objectMapper.writeValueAsString(cliente);
            rabbitTemplate.convertAndSend("clienteQueue", clienteJson);
            System.out.println("Mensaje enviado: " + cliente.getClienteId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}