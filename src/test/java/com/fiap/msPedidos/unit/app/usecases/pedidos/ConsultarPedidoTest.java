package com.fiap.msPedidos.unit.app.usecases.pedidos;


import com.fiap.msPedidos.app.gateways.pedidos.ConsultarPedidoInterface;
import com.fiap.msPedidos.app.usecases.pedidos.ConsultarPedido;
import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ConsultarPedidoTest {

    @Test
    @DisplayName("Deve retornar um pedido válido ao consultar pelo ID")
    void deveRetornarPedidoValidoAoConsultarPorId() {
        ConsultarPedidoInterface consultarPedidoInterfaceMock = Mockito.mock(ConsultarPedidoInterface.class);
        ConsultarPedido consultarPedido = new ConsultarPedido(consultarPedidoInterfaceMock);

        Long pedidoId = 1L;
        String status = "EM PROCESSAMENTO";
        Long enderecoEntrega = 101L;
        List<ProdutoPedido> produtos = List.of(new ProdutoPedido(1L, 2));
        Long cliente = 202L;
        Pedido pedidoMock = new Pedido(status, enderecoEntrega, produtos,  cliente);
        when(consultarPedidoInterfaceMock.consultarPedido(pedidoId)).thenReturn(pedidoMock);

        Pedido resultado = consultarPedido.consultarPedido(pedidoId);

        assertEquals(pedidoMock, resultado);
        verify(consultarPedidoInterfaceMock, times(1)).consultarPedido(pedidoId);
    }

    @Test
    @DisplayName("Deve retornar null se o ID do pedido não existir")
    void deveRetornarNullSeIdNaoExistir() {
        ConsultarPedidoInterface consultarPedidoInterfaceMock = Mockito.mock(ConsultarPedidoInterface.class);
        ConsultarPedido consultarPedido = new ConsultarPedido(consultarPedidoInterfaceMock);

        Long pedidoId = 999L;
        when(consultarPedidoInterfaceMock.consultarPedido(pedidoId)).thenReturn(null);

        Pedido resultado = consultarPedido.consultarPedido(pedidoId);

        assertNull(resultado);
        verify(consultarPedidoInterfaceMock, times(1)).consultarPedido(pedidoId);
    }

    @Test
    @DisplayName("Não deve chamar consultarPedido se o ID for nulo")
    void naoDeveChamarConsultarPedidoSeIdForNulo() {
        ConsultarPedidoInterface consultarPedidoInterfaceMock = Mockito.mock(ConsultarPedidoInterface.class);
        ConsultarPedido consultarPedido = new ConsultarPedido(consultarPedidoInterfaceMock);

        Pedido resultado = consultarPedido.consultarPedido(null);

        assertNull(resultado);
        verify(consultarPedidoInterfaceMock, never()).consultarPedido(any());
    }
}