package com.ejercicio.tecnico.cuentaMovimientosService;

import com.ejercicio.tecnico.cuentaMovimientosService.controller.CuentaController;
import com.ejercicio.tecnico.cuentaMovimientosService.entity.Cuenta;
import com.ejercicio.tecnico.cuentaMovimientosService.service.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    @InjectMocks
    private CuentaController cuentaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(cuentaController).build();
    }

    @Test
    public void testGetAllCuentas() throws Exception {
        List<Cuenta> cuentas = new ArrayList<>();
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuentas.add(cuenta);

        when(cuentaService.getAllCuentas()).thenReturn(cuentas);

        mockMvc.perform(get("/cuentas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(1L));

        verify(cuentaService, times(1)).getAllCuentas();
    }

    @Test
    public void testGetCuentaById() throws Exception {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);

        when(cuentaService.getCuentaById(1L)).thenReturn(Optional.of(cuenta));

        mockMvc.perform(get("/cuentas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1L));

        verify(cuentaService, times(1)).getCuentaById(1L);
    }

    @Test
    public void testCreateCuenta() throws Exception {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setClienteId("carlosalejo");

        when(cuentaService.createCuenta(any(Cuenta.class), eq("carlosalejo"))).thenReturn(cuenta);

        mockMvc.perform(post("/cuentas")
                        .param("clienteId", "carlosalejo")
                        .contentType("application/json")
                        .content("{\"numeroCuenta\":\"1234567890\", \"tipoCuenta\":\"Ahorros\", \"saldoInicial\":1000.0}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.clienteId").value("carlosalejo"));

        verify(cuentaService, times(1)).createCuenta(any(Cuenta.class), eq("carlosalejo"));
    }
}