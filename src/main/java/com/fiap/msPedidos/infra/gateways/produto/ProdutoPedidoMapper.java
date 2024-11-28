package com.fiap.msPedidos.infra.gateways.produto;

import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import com.fiap.msPedidos.infra.persistence.produto.ProdutoPedidoEntity;

public class ProdutoPedidoMapper {

    public ProdutoPedido toDomain(ProdutoPedidoEntity input){
        return new ProdutoPedido(input.getId(),
                input.getQuantidade());
    }

    public ProdutoPedidoEntity toEntity(ProdutoPedido input){
        return new ProdutoPedidoEntity(input.getId(),
                input.getQuantidade());
    }
}
