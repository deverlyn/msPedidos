package com.fiap.msPedidos.unit.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.FazerPedidoInterface;
import com.fiap.msPedidos.app.usecases.pedidos.FazerPedido;
import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FazerPedidoTest {

    @Test
    @DisplayName("Deve criar um pedido com sucesso")
    void deveCriarPedidoComSucesso() {
        FazerPedidoInterface fazerPedidoInterfaceMock = Mockito.mock(FazerPedidoInterface.class);
        FazerPedido fazerPedido = new FazerPedido(fazerPedidoInterfaceMock);

        List<ProdutoPedido> produtos = List.of(new ProdutoPedido(1L, 2));
        Long idCliente = 202L;
        Long idEndereco = 101L;

        Pedido pedidoMock = new Pedido(1L, "CRIADO", idEndereco, produtos, idCliente);

        when(fazerPedidoInterfaceMock.fazerPedido(produtos, idCliente, idEndereco)).thenReturn(pedidoMock);

        Pedido resultado = fazerPedido.fazerPedido(produtos, idCliente, idEndereco);

        assertEquals(pedidoMock, resultado);
        verify(fazerPedidoInterfaceMock, times(1)).fazerPedido(produtos, idCliente, idEndereco);
    }

    @Test
    @DisplayName("Deve retornar null se não for possível criar o pedido")
    void deveRetornarNullSeNaoForPossivelCriarPedido() {
        FazerPedidoInterface fazerPedidoInterfaceMock = Mockito.mock(FazerPedidoInterface.class);
        FazerPedido fazerPedido = new FazerPedido(fazerPedidoInterfaceMock);

        List<ProdutoPedido> produtos = List.of(new ProdutoPedido(2L, 1));
        Long idCliente = 203L;
        Long idEndereco = 102L;

        when(fazerPedidoInterfaceMock.fazerPedido(produtos, idCliente, idEndereco)).thenReturn(null);

        Pedido resultado = fazerPedido.fazerPedido(produtos, idCliente, idEndereco);

        assertEquals(null, resultado);
        verify(fazerPedidoInterfaceMock, times(1)).fazerPedido(produtos, idCliente, idEndereco);
    }

    @Test
    @DisplayName("Não deve chamar fazerPedido se a lista de produtos for nula")
    void naoDeveChamarFazerPedidoSeListaProdutosForNula() {
        FazerPedidoInterface fazerPedidoInterfaceMock = Mockito.mock(FazerPedidoInterface.class);
        FazerPedido fazerPedido = new FazerPedido(fazerPedidoInterfaceMock);

        Long idCliente = 203L;
        Long idEndereco = 102L;

        Pedido resultado = fazerPedido.fazerPedido(null, idCliente, idEndereco);

        assertEquals(null, resultado);
        verify(fazerPedidoInterfaceMock, never()).fazerPedido(any(), eq(idCliente), eq(idEndereco));
    }
}