package com.fiap.msPedidos.unit.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.ValidarClienteInterface;
import com.fiap.msPedidos.app.usecases.pedidos.ValidarCliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ValidarClienteTest {

    @Test
    @DisplayName("Deve retornar verdadeiro para cliente válido")
    void deveRetornarVerdadeiroParaClienteValido() {
        ValidarClienteInterface validarClienteInterfaceMock = Mockito.mock(ValidarClienteInterface.class);
        ValidarCliente validarCliente = new ValidarCliente(validarClienteInterfaceMock);

        Long clienteId = 1L;

        when(validarClienteInterfaceMock.validarCliente(clienteId)).thenReturn(true);

        Boolean resultado = validarCliente.validarCliente(clienteId);

        assertTrue(resultado);
        verify(validarClienteInterfaceMock, times(1)).validarCliente(clienteId);
    }

    @Test
    @DisplayName("Deve retornar falso para cliente inválido")
    void deveRetornarFalsoParaClienteInvalido() {
        ValidarClienteInterface validarClienteInterfaceMock = Mockito.mock(ValidarClienteInterface.class);
        ValidarCliente validarCliente = new ValidarCliente(validarClienteInterfaceMock);

        Long clienteId = 2L;

        when(validarClienteInterfaceMock.validarCliente(clienteId)).thenReturn(false);

        Boolean resultado = validarCliente.validarCliente(clienteId);

        assertFalse(resultado);
        verify(validarClienteInterfaceMock, times(1)).validarCliente(clienteId);
    }

    @Test
    @DisplayName("Não deve chamar validarCliente se o ID for nulo")
    void naoDeveChamarValidarClienteSeIdForNulo() {
        ValidarClienteInterface validarClienteInterfaceMock = Mockito.mock(ValidarClienteInterface.class);
        ValidarCliente validarCliente = new ValidarCliente(validarClienteInterfaceMock);

        Boolean resultado = validarCliente.validarCliente(null);

        assertFalse(resultado);
        verify(validarClienteInterfaceMock, never()).validarCliente(any());
    }
}
