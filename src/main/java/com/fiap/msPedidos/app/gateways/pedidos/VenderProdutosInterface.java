package com.fiap.msPedidos.app.gateways.pedidos;

import com.fiap.msPedidos.domain.entity.ProdutoPedido;

import java.util.List;

public interface VenderProdutosInterface {
    void venderProdutos(List<ProdutoPedido> produtos);
}
