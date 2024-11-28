package com.fiap.msPedidos.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.EnviarParaEntregaInterface;
import com.fiap.msPedidos.domain.entity.Pedido;

public class EnviarParaEntrega {

    private final EnviarParaEntregaInterface enviarParaEntregaInterface;

    public EnviarParaEntrega(EnviarParaEntregaInterface enviarParaEntregaInterface) {
        this.enviarParaEntregaInterface = enviarParaEntregaInterface;
    }

    public Boolean enviarParaEntrega(Pedido pedido) {
        return enviarParaEntregaInterface.enviarParaEntrega(pedido);
    }
}
