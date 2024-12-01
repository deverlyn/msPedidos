package com.fiap.msPedidos.unit.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.ChecarProdutosInterface;
import com.fiap.msPedidos.app.usecases.pedidos.ChecarProdutos;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ChecarProdutosTest {

    @Test
    @DisplayName("Deve retornar verdadeiro se os produtos forem válidos")
    void deveRetornarVerdadeiroSeProdutosForemValidos() {
        ChecarProdutosInterface checarProdutosInterfaceMock = Mockito.mock(ChecarProdutosInterface.class);
        ChecarProdutos checarProdutos = new ChecarProdutos(checarProdutosInterfaceMock);

        List<ProdutoPedido> produtos = List.of(new ProdutoPedido(1L,  10));

        when(checarProdutosInterfaceMock.checarProdutos(produtos)).thenReturn(true);

        boolean resultado = checarProdutos.checarProdutos(produtos);

        assertTrue(resultado);
        verify(checarProdutosInterfaceMock, times(1)).checarProdutos(produtos);
    }

    @Test
    @DisplayName("Deve retornar falso se os produtos forem inválidos")
    void deveRetornarFalsoSeProdutosForemInvalidos() {
        ChecarProdutosInterface checarProdutosInterfaceMock = Mockito.mock(ChecarProdutosInterface.class);
        ChecarProdutos checarProdutos = new ChecarProdutos(checarProdutosInterfaceMock);

        List<ProdutoPedido> produtos = List.of(new ProdutoPedido(2L,  5));

        when(checarProdutosInterfaceMock.checarProdutos(produtos)).thenReturn(false);

        boolean resultado = checarProdutos.checarProdutos(produtos);

        assertFalse(resultado);
        verify(checarProdutosInterfaceMock, times(1)).checarProdutos(produtos);
    }

    @Test
    @DisplayName("Não deve chamar checarProdutos se a lista de produtos for nula")
    void naoDeveChamarChecarProdutosSeListaForNula() {
        ChecarProdutosInterface checarProdutosInterfaceMock = Mockito.mock(ChecarProdutosInterface.class);
        ChecarProdutos checarProdutos = new ChecarProdutos(checarProdutosInterfaceMock);

        boolean resultado = checarProdutos.checarProdutos(null);

        assertFalse(resultado);
        verify(checarProdutosInterfaceMock, never()).checarProdutos(any());
    }

    @Test
    @DisplayName("Não deve chamar checarProdutos se a lista de produtos estiver vazia")
    void naoDeveChamarChecarProdutosSeListaForVazia() {
        ChecarProdutosInterface checarProdutosInterfaceMock = Mockito.mock(ChecarProdutosInterface.class);
        ChecarProdutos checarProdutos = new ChecarProdutos(checarProdutosInterfaceMock);

        List<ProdutoPedido> produtosVazios = List.of();

        boolean resultado = checarProdutos.checarProdutos(produtosVazios);

        assertFalse(resultado);
        verify(checarProdutosInterfaceMock, never()).checarProdutos(any());
    }
}
