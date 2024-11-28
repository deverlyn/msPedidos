package com.fiap.msPedidos.app.gateways.pedidos;

import com.fiap.msPedidos.domain.entity.Pedido;

public interface EnviarParaEntregaInterface {
    Boolean enviarParaEntrega(Pedido pedido);
}
