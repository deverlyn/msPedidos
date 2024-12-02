package com.fiap.msPedidos.unit.infra.gateways.pedido;

import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import com.fiap.msPedidos.infra.gateways.pedido.PedidoMapper;
import com.fiap.msPedidos.infra.gateways.pedido.RepositorioDePedidoCloudStream;
import com.fiap.msPedidos.infra.gateways.pedido.RepositorioDePedidoHTTP;
import com.fiap.msPedidos.infra.gateways.pedido.RepositorioDePedidoJPA;
import com.fiap.msPedidos.infra.persistence.pedido.PedidoEntity;
import com.fiap.msPedidos.infra.persistence.pedido.PedidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RepositorioDePedidoJPATest {

    @Test
    @DisplayName("Deve atualizar o status de um pedido com sucesso")
    void deveAtualizarStatusPedidoComSucesso() {
        PedidoRepository repositoryMock = Mockito.mock(PedidoRepository.class);
        PedidoMapper pedidoMapperMock = Mockito.mock(PedidoMapper.class);
        RepositorioDePedidoHTTP repositorioHTTPMock = Mockito.mock(RepositorioDePedidoHTTP.class);
        RepositorioDePedidoCloudStream repositorioCloudStreamMock = Mockito.mock(RepositorioDePedidoCloudStream.class);

        RepositorioDePedidoJPA repositorio = new RepositorioDePedidoJPA(
                repositoryMock, pedidoMapperMock, repositorioHTTPMock, repositorioCloudStreamMock);

        PedidoEntity pedidoEntity = new PedidoEntity(1L, "CRIADO", 101L, null, 202L);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(pedidoEntity));

        repositorio.atualizarPedido(1L, "ENTREGUE");

        assertEquals("ENTREGUE", pedidoEntity.getStatus());
        verify(repositoryMock, times(1)).save(pedidoEntity);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar pedido inexistente")
    void deveLancarExcecaoAoAtualizarPedidoInexistente() {
        PedidoRepository repositoryMock = Mockito.mock(PedidoRepository.class);
        PedidoMapper pedidoMapperMock = Mockito.mock(PedidoMapper.class);
        RepositorioDePedidoHTTP repositorioHTTPMock = Mockito.mock(RepositorioDePedidoHTTP.class);
        RepositorioDePedidoCloudStream repositorioCloudStreamMock = Mockito.mock(RepositorioDePedidoCloudStream.class);

        RepositorioDePedidoJPA repositorio = new RepositorioDePedidoJPA(
                repositoryMock, pedidoMapperMock, repositorioHTTPMock, repositorioCloudStreamMock);

        when(repositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> repositorio.atualizarPedido(1L, "ENTREGUE"));
    }

    @Test
    @DisplayName("Deve consultar pedido com sucesso")
    void deveConsultarPedidoComSucesso() {
        PedidoRepository repositoryMock = Mockito.mock(PedidoRepository.class);
        PedidoMapper pedidoMapperMock = Mockito.mock(PedidoMapper.class);
        RepositorioDePedidoHTTP repositorioHTTPMock = Mockito.mock(RepositorioDePedidoHTTP.class);
        RepositorioDePedidoCloudStream repositorioCloudStreamMock = Mockito.mock(RepositorioDePedidoCloudStream.class);

        RepositorioDePedidoJPA repositorio = new RepositorioDePedidoJPA(
                repositoryMock, pedidoMapperMock, repositorioHTTPMock, repositorioCloudStreamMock);

        PedidoEntity pedidoEntity = new PedidoEntity(1L, "CRIADO", 101L, null, 202L);
        Pedido pedido = new Pedido(1L, "CRIADO", 101L, null, 202L);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        when(pedidoMapperMock.toDomain(pedidoEntity)).thenReturn(pedido);

        Pedido resultado = repositorio.consultarPedido(1L);

        assertEquals(pedido, resultado);
        verify(repositoryMock, times(1)).findById(1L);
    }

//    @Test
//    @DisplayName("Deve criar um pedido com sucesso")
//    void deveCriarPedidoComSucesso() {
//        PedidoRepository repositoryMock = Mockito.mock(PedidoRepository.class);
//        PedidoMapper pedidoMapperMock = Mockito.mock(PedidoMapper.class);
//        RepositorioDePedidoHTTP repositorioHTTPMock = Mockito.mock(RepositorioDePedidoHTTP.class);
//        RepositorioDePedidoCloudStream repositorioCloudStreamMock = Mockito.mock(RepositorioDePedidoCloudStream.class);
//
//        RepositorioDePedidoJPA repositorio = new RepositorioDePedidoJPA(
//                repositoryMock, pedidoMapperMock, repositorioHTTPMock, repositorioCloudStreamMock);
//
//        List<ProdutoPedido> produtos = List.of(new ProdutoPedido(1L,  2));
//        PedidoEntity pedidoEntity = new PedidoEntity(1L, "CRIADO", 101L, null, 202L);
//        Pedido pedido = new Pedido(1L, "CRIADO", 101L, null, 202L);
//
//        when(repositorioHTTPMock.checarProdutos(produtos)).thenReturn(true);
//        when(repositorioHTTPMock.validarCliente(202L)).thenReturn(true);
//        when(repositorioHTTPMock.validarEndereco(101L)).thenReturn(true);
//        when(repositorioHTTPMock.enviarParaEntrega(any())).thenReturn(true);
//        when(pedidoMapperMock.toEntity(any())).thenReturn(pedidoEntity);
//        when(pedidoMapperMock.toDomain(pedidoEntity)).thenReturn(pedido);
//        when(repositoryMock.save(pedidoEntity)).thenReturn(pedidoEntity);
//
//        Pedido resultado = repositorio.fazerPedido(produtos, 202L, 101L);
//
//        assertEquals(pedido, resultado);
//        verify(repositoryMock, times(1)).save(pedidoEntity);
//        verify(repositorioHTTPMock, times(1)).checarProdutos(produtos);
//        verify(repositorioHTTPMock, times(1)).validarCliente(202L);
//        verify(repositorioHTTPMock, times(1)).validarEndereco(101L);
//        verify(repositorioCloudStreamMock, times(1)).venderProdutos(produtos);
//    }
}
