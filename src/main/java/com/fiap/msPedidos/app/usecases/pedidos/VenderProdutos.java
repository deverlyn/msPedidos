package com.fiap.msPedidos.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.VenderProdutosInterface;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;

import java.util.List;

public class VenderProdutos {

    private final VenderProdutosInterface venderProdutosInterface;

    public VenderProdutos(VenderProdutosInterface venderProdutosInterface) {
        this.venderProdutosInterface = venderProdutosInterface;
    }

    public void venderProdutos(List<ProdutoPedido> produtos) {
        venderProdutosInterface.venderProdutos(produtos);
    }
}
