package com.fiap.msPedidos.infra.controller.pedido;

import com.fiap.msPedidos.domain.entity.ProdutoPedido;

import java.util.List;

public record PedidoDTO(
        Long id,
        String status,
        Long enderecoEntrega,
        List<ProdutoPedido>produtos,
        Long cliente
){
}
