package com.fiap.msPedidos.unit.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.ValidarEnderecoInterface;
import com.fiap.msPedidos.app.usecases.pedidos.ValidarEndereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ValidarEnderecoTest {

    @Test
    @DisplayName("Deve retornar verdadeiro para endereço válido")
    void deveRetornarVerdadeiroParaEnderecoValido() {
        ValidarEnderecoInterface validarEnderecoInterfaceMock = Mockito.mock(ValidarEnderecoInterface.class);
        ValidarEndereco validarEndereco = new ValidarEndereco(validarEnderecoInterfaceMock);

        Long enderecoId = 1L;

        when(validarEnderecoInterfaceMock.validarEndereco(enderecoId)).thenReturn(true);

        Boolean resultado = validarEndereco.validarEndereco(enderecoId);

        assertTrue(resultado);
        verify(validarEnderecoInterfaceMock, times(1)).validarEndereco(enderecoId);
    }

    @Test
    @DisplayName("Deve retornar falso para endereço inválido")
    void deveRetornarFalsoParaEnderecoInvalido() {
        ValidarEnderecoInterface validarEnderecoInterfaceMock = Mockito.mock(ValidarEnderecoInterface.class);
        ValidarEndereco validarEndereco = new ValidarEndereco(validarEnderecoInterfaceMock);

        Long enderecoId = 2L;

        when(validarEnderecoInterfaceMock.validarEndereco(enderecoId)).thenReturn(false);

        Boolean resultado = validarEndereco.validarEndereco(enderecoId);

        assertFalse(resultado);
        verify(validarEnderecoInterfaceMock, times(1)).validarEndereco(enderecoId);
    }

    @Test
    @DisplayName("Deve retornar falso e não chamar validarEndereco se o ID for nulo")
    void deveRetornarFalsoENaoChamarValidarEnderecoSeIdForNulo() {
        ValidarEnderecoInterface validarEnderecoInterfaceMock = Mockito.mock(ValidarEnderecoInterface.class);
        ValidarEndereco validarEndereco = new ValidarEndereco(validarEnderecoInterfaceMock);

        Boolean resultado = validarEndereco.validarEndereco(null);

        assertFalse(resultado);
        verify(validarEnderecoInterfaceMock, never()).validarEndereco(any());
    }
}