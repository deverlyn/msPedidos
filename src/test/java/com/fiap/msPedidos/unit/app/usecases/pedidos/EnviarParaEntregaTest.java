package com.fiap.msPedidos.unit.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.EnviarParaEntregaInterface;
import com.fiap.msPedidos.app.usecases.pedidos.EnviarParaEntrega;
import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class EnviarParaEntregaTest {

//    @Test
//    @DisplayName("Deve enviar pedido para entrega com sucesso")
//    void deveEnviarPedidoParaEntregaComSucesso() {
//        EnviarParaEntregaInterface enviarParaEntregaInterfaceMock = Mockito.mock(EnviarParaEntregaInterface.class);
//        EnviarParaEntrega enviarParaEntrega = new EnviarParaEntrega(enviarParaEntregaInterfaceMock);
//
//        Pedido pedido = new Pedido(1L, "PRONTO_PARA_ENTREGA", 101L,
//                List.of(new ProdutoPedido(1L,  2)), 202L);
//
//        when(enviarParaEntregaInterfaceMock.enviarParaEntrega(pedido)).thenReturn(true);
//
//        Boolean resultado = enviarParaEntrega.enviarParaEntrega(pedido);
//
//        assertTrue(resultado);
//        verify(enviarParaEntregaInterfaceMock, times(1)).enviarParaEntrega(pedido);
//    }

//    @Test
//    @DisplayName("Deve retornar falso se falhar ao enviar pedido para entrega")
//    void deveRetornarFalsoSeFalharAoEnviarPedidoParaEntrega() {
//        EnviarParaEntregaInterface enviarParaEntregaInterfaceMock = Mockito.mock(EnviarParaEntregaInterface.class);
//        EnviarParaEntrega enviarParaEntrega = new EnviarParaEntrega(enviarParaEntregaInterfaceMock);
//
//        Pedido pedido = new Pedido(2L, "PRONTO_PARA_ENTREGA", 102L,
//                List.of(new ProdutoPedido(2L,  1)), 203L);
//
//        when(enviarParaEntregaInterfaceMock.enviarParaEntrega(pedido)).thenReturn(false);
//
//        Boolean resultado = enviarParaEntrega.enviarParaEntrega(pedido);
//
//        assertFalse(resultado);
//        verify(enviarParaEntregaInterfaceMock, times(1)).enviarParaEntrega(pedido);
//    }

//    @Test
//    @DisplayName("Não deve chamar enviarParaEntrega se o pedido for nulo")
//    void naoDeveChamarEnviarParaEntregaSePedidoForNulo() {
//        EnviarParaEntregaInterface enviarParaEntregaInterfaceMock = Mockito.mock(EnviarParaEntregaInterface.class);
//        EnviarParaEntrega enviarParaEntrega = new EnviarParaEntrega(enviarParaEntregaInterfaceMock);
//
//        Boolean resultado = enviarParaEntrega.enviarParaEntrega(null);
//
//        assertFalse(resultado);
//        verify(enviarParaEntregaInterfaceMock, never()).enviarParaEntrega(any());
//    }
}