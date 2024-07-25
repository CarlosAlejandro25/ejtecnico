package com.ejercicio.tecnico.cuentaMovimientosService;

import com.ejercicio.tecnico.cuentaMovimientosService.entity.Cliente;
import com.ejercicio.tecnico.cuentaMovimientosService.entity.Cuenta;
import com.ejercicio.tecnico.cuentaMovimientosService.repository.CuentaRepository;
import com.ejercicio.tecnico.cuentaMovimientosService.service.CuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CuentasServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CuentaService cuentaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cuentaService = new CuentaService();
        cuentaService.cuentaRepository = cuentaRepository;
        cuentaService.objectMapper = objectMapper;
        cuentaService.clientesValidos = new ConcurrentHashMap<>(); // Iniciar el mapa de clientes válidos
    }

    @Test
    void testGetCuentaById() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));

        Optional<Cuenta> result = cuentaService.getCuentaById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testCreateCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setClienteId("carlosalejo");

        Cliente cliente = new Cliente();
        cliente.setClienteId("carlosalejo");
        cuentaService.clientesValidos.put("carlosalejo", cliente); // Añadir el cliente válido

        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuenta);

        Cuenta result = cuentaService.createCuenta(cuenta, "carlosalejo");

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("carlosalejo", result.getClienteId());
    }

    @Test
    void testCreateCuentaClienteNoValido() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setClienteId("clienteInvalido");

        assertThrows(RuntimeException.class, () -> cuentaService.createCuenta(cuenta, "clienteInvalido"));
    }

    @Test
    void testUpdateCuenta() {
        Cuenta existingCuenta = new Cuenta();
        existingCuenta.setId(1L);
        existingCuenta.setNumeroCuenta("1234567890");

        Cuenta updatedCuenta = new Cuenta();
        updatedCuenta.setNumeroCuenta("4561234567");

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(existingCuenta));
        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(updatedCuenta);

        Cuenta result = cuentaService.updateCuenta(1L, updatedCuenta);

        assertNotNull(result);
        assertEquals("4561234567", result.getNumeroCuenta());
    }

    @Test
    void testDeleteCuenta() {
        doNothing().when(cuentaRepository).deleteById(1L);

        assertDoesNotThrow(() -> cuentaService.deleteCuenta(1L));
        verify(cuentaRepository, times(1)).deleteById(1L);
    }
}