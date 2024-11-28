package com.fiap.msPedidos.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.ChecarProdutosInterface;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;

import java.util.List;

public class ChecarProdutos {

    private final ChecarProdutosInterface checarProdutosInterface;

    public ChecarProdutos(ChecarProdutosInterface checarProdutosInterface) {
        this.checarProdutosInterface = checarProdutosInterface;
    }

    public boolean checarProdutos(List<ProdutoPedido> produtos){
        return checarProdutosInterface.checarProdutos(produtos);
    }
}
