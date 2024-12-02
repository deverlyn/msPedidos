package com.fiap.msPedidos.app.gateways.pedidos;

import com.fiap.msPedidos.infra.controller.pedido.EntregaDTO;

public interface EnviarParaEntregaInterface {
    EntregaDTO enviarParaEntrega(Long idPedido);
}
