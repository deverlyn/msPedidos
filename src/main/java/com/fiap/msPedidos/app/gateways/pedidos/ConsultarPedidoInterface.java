package com.fiap.msPedidos.app.gateways.pedidos;

import com.fiap.msPedidos.domain.entity.Pedido;

public interface ConsultarPedidoInterface {
    Pedido consultarPedido(Long id);
}
