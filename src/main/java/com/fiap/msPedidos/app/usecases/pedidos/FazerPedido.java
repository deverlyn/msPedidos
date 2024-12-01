package com.fiap.msPedidos.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.FazerPedidoInterface;
import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;

import java.util.List;

public class FazerPedido {

    private final FazerPedidoInterface fazerPedidoInterface;

    public FazerPedido(FazerPedidoInterface fazerPedidoInterface) {
        this.fazerPedidoInterface = fazerPedidoInterface;
    }

    public Pedido fazerPedido(List<ProdutoPedido> produtoPedido, Long idCliente, Long idEndereco) {
       if(produtoPedido == null){
           return null;
       }
        return fazerPedidoInterface.fazerPedido(produtoPedido, idCliente, idEndereco);
    }
}
