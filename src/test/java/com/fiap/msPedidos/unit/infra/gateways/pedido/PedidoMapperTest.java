package com.fiap.msPedidos.unit.infra.gateways.pedido;


import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import com.fiap.msPedidos.infra.gateways.pedido.PedidoMapper;
import com.fiap.msPedidos.infra.gateways.produto.ProdutoPedidoMapper;
import com.fiap.msPedidos.infra.persistence.pedido.PedidoEntity;
import com.fiap.msPedidos.infra.persistence.produto.ProdutoPedidoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PedidoMapperTest {

    @Test
    @DisplayName("Deve mapear PedidoEntity para Pedido")
    void deveMapearPedidoEntityParaPedido() {
        ProdutoPedidoMapper produtoPedidoMapperMock = Mockito.mock(ProdutoPedidoMapper.class);
        PedidoMapper pedidoMapper = new PedidoMapper(produtoPedidoMapperMock);

        ProdutoPedidoEntity produtoPedidoEntity = new ProdutoPedidoEntity(1L, 2);
        ProdutoPedido produtoPedido = new ProdutoPedido(1L,  2);

        PedidoEntity pedidoEntity = new PedidoEntity(
                1L,
                "CRIADO",
                101L,
                List.of(produtoPedidoEntity),
                202L
        );

        Pedido pedidoEsperado = new Pedido(
                1L,
                "CRIADO",
                101L,
                List.of(produtoPedido),
                202L
        );

        when(produtoPedidoMapperMock.toDomain(produtoPedidoEntity)).thenReturn(produtoPedido);

        Pedido resultado = pedidoMapper.toDomain(pedidoEntity);

        assertEquals(pedidoEsperado.getId(), resultado.getId());
        assertEquals(pedidoEsperado.getStatus(), resultado.getStatus());
        assertEquals(pedidoEsperado.getEnderecoEntrega(), resultado.getEnderecoEntrega());
        assertEquals(pedidoEsperado.getProdutos().size(), resultado.getProdutos().size());
        assertEquals(pedidoEsperado.getCliente(), resultado.getCliente());

        verify(produtoPedidoMapperMock, times(1)).toDomain(produtoPedidoEntity);
    }

    @Test
    @DisplayName("Deve mapear Pedido para PedidoEntity")
    void deveMapearPedidoParaPedidoEntity() {
        ProdutoPedidoMapper produtoPedidoMapperMock = Mockito.mock(ProdutoPedidoMapper.class);
        PedidoMapper pedidoMapper = new PedidoMapper(produtoPedidoMapperMock);

        ProdutoPedido produtoPedido = new ProdutoPedido(1L,  2);
        ProdutoPedidoEntity produtoPedidoEntity = new ProdutoPedidoEntity(1L, 2);

        Pedido pedido = new Pedido(
                1L,
                "CRIADO",
                101L,
                List.of(produtoPedido),
                202L
        );

        PedidoEntity pedidoEntityEsperado = new PedidoEntity(
                1L,
                "CRIADO",
                101L,
                List.of(produtoPedidoEntity),
                202L
        );

        when(produtoPedidoMapperMock.toEntity(produtoPedido)).thenReturn(produtoPedidoEntity);

        PedidoEntity resultado = pedidoMapper.toEntity(pedido);

        assertEquals(pedidoEntityEsperado.getId(), resultado.getId());
        assertEquals(pedidoEntityEsperado.getStatus(), resultado.getStatus());
        assertEquals(pedidoEntityEsperado.getEnderecoEntrega(), resultado.getEnderecoEntrega());
        assertEquals(pedidoEntityEsperado.getProdutos().size(), resultado.getProdutos().size());
        assertEquals(pedidoEntityEsperado.getCliente(), resultado.getCliente());

        verify(produtoPedidoMapperMock, times(1)).toEntity(produtoPedido);
    }
}