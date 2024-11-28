package com.fiap.msPedidos.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.ConsultarPedidoInterface;
import com.fiap.msPedidos.domain.entity.Pedido;

public class ConsultarPedido {

    private final ConsultarPedidoInterface consultarPedidoInterface;

    public ConsultarPedido(ConsultarPedidoInterface consultarPedidoInterface) {
        this.consultarPedidoInterface = consultarPedidoInterface;
    }

    public Pedido consultarPedido(Long id) {
        return consultarPedidoInterface.consultarPedido(id);
    }
}
