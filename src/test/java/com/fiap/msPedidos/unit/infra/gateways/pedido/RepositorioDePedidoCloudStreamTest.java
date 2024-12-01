package com.fiap.msPedidos.unit.infra.gateways.pedido;

import com.fiap.msPedidos.config.RabbitConfig;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import com.fiap.msPedidos.infra.gateways.pedido.RepositorioDePedidoCloudStream;
import com.fiap.msPedidos.infra.gateways.produto.ProdutoPedidoMapper;
import com.fiap.msPedidos.infra.persistence.produto.ProdutoPedidoMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

import static org.mockito.Mockito.*;

class RepositorioDePedidoCloudStreamTest {

    @Test
    @DisplayName("Deve enviar produtos para a fila com sucesso")
    void deveEnviarProdutosParaAFilaComSucesso() {
        RabbitTemplate rabbitTemplateMock = Mockito.mock(RabbitTemplate.class);
        ProdutoPedidoMapper produtoPedidoMapperMock = Mockito.mock(ProdutoPedidoMapper.class);

        RepositorioDePedidoCloudStream repositorio = new RepositorioDePedidoCloudStream(rabbitTemplateMock, produtoPedidoMapperMock);

        ProdutoPedido produtoPedido = new ProdutoPedido(1L,  2);
        ProdutoPedidoMessage produtoPedidoMessage = new ProdutoPedidoMessage(1L,  2);

        when(produtoPedidoMapperMock.toMessage(produtoPedido)).thenReturn(produtoPedidoMessage);

        List<ProdutoPedido> produtos = List.of(produtoPedido);

        repositorio.venderProdutos(produtos);

        verify(produtoPedidoMapperMock, times(1)).toMessage(produtoPedido);
        verify(rabbitTemplateMock, times(1)).convertAndSend(RabbitConfig.FILA_PEDIDOS, produtoPedidoMessage);
    }

    @Test
    @DisplayName("Deve lançar RuntimeException ao falhar ao enviar produto para a fila")
    void deveLancarRuntimeExceptionAoFalharEnviarParaFila() {
        RabbitTemplate rabbitTemplateMock = Mockito.mock(RabbitTemplate.class);
        ProdutoPedidoMapper produtoPedidoMapperMock = Mockito.mock(ProdutoPedidoMapper.class);

        RepositorioDePedidoCloudStream repositorio = new RepositorioDePedidoCloudStream(rabbitTemplateMock, produtoPedidoMapperMock);

        ProdutoPedido produtoPedido = new ProdutoPedido(1L, 2);
        ProdutoPedidoMessage produtoPedidoMessage = new ProdutoPedidoMessage(1L,  2);

        when(produtoPedidoMapperMock.toMessage(produtoPedido)).thenReturn(produtoPedidoMessage);

        doThrow(new RuntimeException("Erro ao enviar para a fila"))
                .when(rabbitTemplateMock).convertAndSend(RabbitConfig.FILA_PEDIDOS, produtoPedidoMessage);

        List<ProdutoPedido> produtos = List.of(produtoPedido);

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            repositorio.venderProdutos(produtos);
        });

        org.junit.jupiter.api.Assertions.assertEquals("Erro ao enviar para a fila", exception.getCause().getMessage());
        verify(rabbitTemplateMock, times(1)).convertAndSend(RabbitConfig.FILA_PEDIDOS, produtoPedidoMessage);
    }

    @Test
    @DisplayName("Não deve enviar produtos para a fila se a lista de produtos estiver vazia")
    void naoDeveEnviarProdutosSeListaVazia() {
        RabbitTemplate rabbitTemplateMock = Mockito.mock(RabbitTemplate.class);
        ProdutoPedidoMapper produtoPedidoMapperMock = Mockito.mock(ProdutoPedidoMapper.class);

        RepositorioDePedidoCloudStream repositorio = new RepositorioDePedidoCloudStream(rabbitTemplateMock, produtoPedidoMapperMock);

        List<ProdutoPedido> produtosVazios = List.of();

        repositorio.venderProdutos(produtosVazios);

        verifyNoInteractions(rabbitTemplateMock);
        verifyNoInteractions(produtoPedidoMapperMock);
    }
}