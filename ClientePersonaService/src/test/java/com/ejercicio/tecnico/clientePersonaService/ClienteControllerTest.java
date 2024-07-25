package com.ejercicio.tecnico.clientePersonaService;

import com.ejercicio.tecnico.clientePersonaService.controller.ClienteController;
import com.ejercicio.tecnico.clientePersonaService.entity.Cliente;
import com.ejercicio.tecnico.clientePersonaService.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    void testCreateCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setClienteId("clienteTest");
        cliente.setContrasena("Passwd12!");
        cliente.setEstado(true);
        cliente.setGenero("Masculino");
        cliente.setIdentificacion("1234567890");
        cliente.setTelefono("1234567890");
        cliente.setEdad(30);
        cliente.setNombre("Nombre Test");

        when(clienteService.createCliente(Mockito.any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"clienteId\": \"clienteTest\", \"contrasena\": \"Passwd12!\", \"estado\": true, \"genero\": \"Masculino\", \"identificacion\": \"1234567890\", \"telefono\": \"1234567890\", \"edad\": 30, \"nombre\": \"Nombre Test\" }"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllClientes() throws Exception {
        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}