package com.fiap.msPedidos.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.AtualizarPedidoInterface;

public class AtualizarPedido {

    private final AtualizarPedidoInterface atualizarPedidoInterface;

    public AtualizarPedido(AtualizarPedidoInterface atualizarPedidoInterface) {
        this.atualizarPedidoInterface = atualizarPedidoInterface;
    }

    public void atualizarPedido(Long idPedido, String status) {
        if (idPedido == null || status == null) {
            return;
        }
        atualizarPedidoInterface.atualizarPedido(idPedido, status);
    }
}
