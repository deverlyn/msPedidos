package com.fiap.msPedidos.unit.app.usecases.pedidos;


import com.fiap.msPedidos.app.gateways.pedidos.VenderProdutosInterface;
import com.fiap.msPedidos.app.usecases.pedidos.VenderProdutos;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

class VenderProdutosTest {

    @Test
    @DisplayName("Deve chamar venderProdutos com a lista de produtos fornecida")
    void deveChamarVenderProdutosComListaDeProdutos() {
        VenderProdutosInterface venderProdutosInterfaceMock = Mockito.mock(VenderProdutosInterface.class);
        VenderProdutos venderProdutos = new VenderProdutos(venderProdutosInterfaceMock);

        List<ProdutoPedido> produtos = List.of(
                new ProdutoPedido(1L, 5),
                new ProdutoPedido(2L,  3)
        );

        venderProdutos.venderProdutos(produtos);

        verify(venderProdutosInterfaceMock, times(1)).venderProdutos(produtos);
    }

    @Test
    @DisplayName("Não deve chamar venderProdutos se a lista de produtos for nula")
    void naoDeveChamarVenderProdutosSeListaForNula() {
        VenderProdutosInterface venderProdutosInterfaceMock = Mockito.mock(VenderProdutosInterface.class);
        VenderProdutos venderProdutos = new VenderProdutos(venderProdutosInterfaceMock);

        venderProdutos.venderProdutos(null);

        verify(venderProdutosInterfaceMock, never()).venderProdutos(any());
    }

    @Test
    @DisplayName("Não deve chamar venderProdutos se a lista de produtos estiver vazia")
    void naoDeveChamarVenderProdutosSeListaForVazia() {
        VenderProdutosInterface venderProdutosInterfaceMock = Mockito.mock(VenderProdutosInterface.class);
        VenderProdutos venderProdutos = new VenderProdutos(venderProdutosInterfaceMock);

        List<ProdutoPedido> produtosVazios = List.of();

        venderProdutos.venderProdutos(produtosVazios);

        verify(venderProdutosInterfaceMock, never()).venderProdutos(any());
    }
}