package com.fiap.msPedidos.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.EnviarParaEntregaInterface;
import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.infra.controller.pedido.EntregaDTO;

public class EnviarParaEntrega {

    private final EnviarParaEntregaInterface enviarParaEntregaInterface;

    public EnviarParaEntrega(EnviarParaEntregaInterface enviarParaEntregaInterface) {
        this.enviarParaEntregaInterface = enviarParaEntregaInterface;
    }

    public EntregaDTO enviarParaEntrega(Long idPedido) {

       if(idPedido == null){
           return null;
       }
        return enviarParaEntregaInterface.enviarParaEntrega(idPedido);
    }
}
