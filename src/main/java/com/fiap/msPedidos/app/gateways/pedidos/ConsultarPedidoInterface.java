package com.fiap.msPedidos.app.gateways.pedidos;

import com.fiap.msPedidos.domain.entity.Pedido;

import java.util.List;

public interface ConsultarPedidoInterface {
    Pedido consultarPedido(Long id);

    List<Pedido> consultarTodosPedidos();
}
