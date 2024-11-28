package com.fiap.msPedidos.infra.gateways.pedido;

import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.infra.gateways.produto.ProdutoPedidoMapper;
import com.fiap.msPedidos.infra.persistence.pedido.PedidoEntity;

import java.util.stream.Collectors;

public class PedidoMapper {

    private final ProdutoPedidoMapper produtoPedidoMapper;

    public PedidoMapper(ProdutoPedidoMapper produtoPedidoMapper) {
        this.produtoPedidoMapper = produtoPedidoMapper;
    }

    public Pedido toDomain(PedidoEntity input) {
        return new Pedido(input.getId(),
                input.getStatus(),
                input.getEnderecoEntrega(),
                input.getProdutos().stream().map(produtoPedidoMapper::toDomain).collect(Collectors.toList()),
                input.getCliente());
    }

    public PedidoEntity toEntity(Pedido input) {
        return new PedidoEntity(input.getId(),
                input.getStatus(),
                input.getEnderecoEntrega(),
                input.getProdutos().stream().map(produtoPedidoMapper::toEntity).collect(Collectors.toList()),
                input.getCliente());
    }
}
