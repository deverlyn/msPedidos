package com.fiap.msPedidos.app.gateways.pedidos;

import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;

import java.util.List;

public interface FazerPedidoInterface {
    Pedido fazerPedido(List<ProdutoPedido> idProdutos, Long idCliente, Long idEndereco);
}
