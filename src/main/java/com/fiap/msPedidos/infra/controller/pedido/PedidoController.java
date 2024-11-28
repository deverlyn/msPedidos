package com.fiap.msPedidos.infra.controller.pedido;

import com.fiap.msPedidos.app.usecases.pedidos.ConsultarPedido;
import com.fiap.msPedidos.app.usecases.pedidos.FazerPedido;
import com.fiap.msPedidos.domain.entity.Pedido;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final ConsultarPedido consultarPedido;
    private final FazerPedido fazerPedido;

    public PedidoController(ConsultarPedido consultarPedido, FazerPedido fazerPedido) {
        this.consultarPedido = consultarPedido;
        this.fazerPedido = fazerPedido;
    }

    @GetMapping("/{id}")
    public PedidoDTO consultarPedido(@PathVariable Long id) {
        Pedido pedido = consultarPedido.consultarPedido(id);
        return new PedidoDTO(pedido.getId(),
                pedido.getStatus(),
                pedido.getEnderecoEntrega(),
                pedido.getProdutos(),
                pedido.getCliente());
    }

    @PostMapping
    public PedidoDTO fazerPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = fazerPedido.fazerPedido(pedidoDTO.produtos(), pedidoDTO.cliente(), pedidoDTO.enderecoEntrega());
        return new PedidoDTO(pedido.getId(),
                pedido.getStatus(),
                pedido.getEnderecoEntrega(),
                pedido.getProdutos(),
                pedido.getCliente());
    }
}
