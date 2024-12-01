package com.fiap.msPedidos.unit.infra.gateways.pedido;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import com.fiap.msPedidos.infra.gateways.pedido.RepositorioDePedidoHTTP;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RepositorioDePedidoHTTPTest {

    @Test
    @DisplayName("Deve validar cliente com sucesso")
    void deveValidarClienteComSucesso() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();
        RepositorioDePedidoHTTP repositorio = new RepositorioDePedidoHTTP(restTemplateMock, objectMapper);

        when(restTemplateMock.getForEntity(anyString(), eq(Boolean.class), anyLong()))
                .thenReturn(ResponseEntity.ok(true));

        Boolean resultado = repositorio.validarCliente(1L);

        assertTrue(resultado);
        verify(restTemplateMock, times(1)).getForEntity(anyString(), eq(Boolean.class), anyLong());
    }


    @Test
    @DisplayName("Deve lançar NoSuchElementException ao cliente não ser encontrado")
    void deveLancarExcecaoSeClienteNaoForEncontrado() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();
        RepositorioDePedidoHTTP repositorio = new RepositorioDePedidoHTTP(restTemplateMock, objectMapper);

        HttpClientErrorException notFoundException = HttpClientErrorException.create(
                HttpStatus.NOT_FOUND,        // Status HTTP
                "Cliente não encontrado",   // Texto do status
                null,                        // Headers (null para simplificar)
                null,                        // Body (null porque não temos um corpo)
                null                         // Charset (null para simplificar)
        );

        when(restTemplateMock.getForEntity(anyString(), eq(Boolean.class), anyLong()))
                .thenThrow(notFoundException);

        assertThrows(NoSuchElementException.class, () -> repositorio.validarCliente(1L));
    }

    @Test
    @DisplayName("Deve validar endereço com sucesso")
    void deveValidarEnderecoComSucesso() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();
        RepositorioDePedidoHTTP repositorio = new RepositorioDePedidoHTTP(restTemplateMock, objectMapper);

        when(restTemplateMock.getForEntity(anyString(), eq(Boolean.class), anyLong()))
                .thenReturn(ResponseEntity.ok(true));

        Boolean resultado = repositorio.validarEndereco(1L);

        assertTrue(resultado);
        verify(restTemplateMock, times(1)).getForEntity(anyString(), eq(Boolean.class), anyLong());
    }

    @Test
    @DisplayName("Deve verificar produtos com sucesso")
    void deveVerificarProdutosComSucesso() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();
        RepositorioDePedidoHTTP repositorio = new RepositorioDePedidoHTTP(restTemplateMock, objectMapper);

        ProdutoPedido produtoPedido = new ProdutoPedido(1L, 10);

        when(restTemplateMock.getForEntity(anyString(), eq(Boolean.class), anyLong(), anyInt()))
                .thenReturn(ResponseEntity.ok(true));

        Boolean resultado = repositorio.checarProdutos(List.of(produtoPedido));

        assertTrue(resultado);
        verify(restTemplateMock, times(1)).getForEntity(anyString(), eq(Boolean.class), anyLong(), anyInt());
    }

    @Test
    @DisplayName("Deve lançar RuntimeException se houver erro na comunicação")
    void deveLancarRuntimeExceptionAoFalharComunicacao() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();
        RepositorioDePedidoHTTP repositorio = new RepositorioDePedidoHTTP(restTemplateMock, objectMapper);

        ProdutoPedido produtoPedido = new ProdutoPedido(1L, 10);

        when(restTemplateMock.getForEntity(anyString(), eq(Boolean.class), anyLong(), anyInt()))
                .thenThrow(new RuntimeException("Erro na comunicação"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> repositorio.checarProdutos(List.of(produtoPedido)));
        assertEquals("Erro na comunicação entre microsserviços", exception.getMessage());
    }
}