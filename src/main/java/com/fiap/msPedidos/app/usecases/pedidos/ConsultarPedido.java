package com.fiap.msPedidos.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.ConsultarPedidoInterface;
import com.fiap.msPedidos.domain.entity.Pedido;

import java.util.List;

public class ConsultarPedido {

    private final ConsultarPedidoInterface consultarPedidoInterface;

    public ConsultarPedido(ConsultarPedidoInterface consultarPedidoInterface) {
        this.consultarPedidoInterface = consultarPedidoInterface;
    }

    public Pedido consultarPedido(Long id) {
        if(id == null){
            return null;
        }
        return consultarPedidoInterface.consultarPedido(id);
    }

    public List<Pedido> consultarTodosPedidos() {
        return consultarPedidoInterface.consultarTodosPedidos();
    }
}
