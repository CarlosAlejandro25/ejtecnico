//Prueba Unitaria para ClienteService

package com.ejercicio.tecnico.clientePersonaService;

import com.ejercicio.tecnico.clientePersonaService.entity.Cliente;
import com.ejercicio.tecnico.clientePersonaService.repository.ClienteRepository;
import com.ejercicio.tecnico.clientePersonaService.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCliente() throws Exception {
        // Datos de prueba
        Cliente cliente = new Cliente();
        cliente.setClienteId("cliente4");
        cliente.setContrasena("password");
        cliente.setEstado(true);
        cliente.setNombre("John Doe");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setIdentificacion("12345678");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setTelefono("123456789");

        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(objectMapper.writeValueAsString(cliente)).thenReturn("{\"clienteId\":\"cliente4\"}"); //Ajustar según el cliente que se desea utilizar para la prueba.

        // Ejecutar el método que se está probando
        Cliente createdCliente = clienteService.createCliente(cliente);

        // Verificar los resultados
        assertEquals(cliente.getClienteId(), createdCliente.getClienteId());
        assertEquals(cliente.getNombre(), createdCliente.getNombre());
        verify(clienteRepository, times(1)).save(cliente);
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString());
    }
}