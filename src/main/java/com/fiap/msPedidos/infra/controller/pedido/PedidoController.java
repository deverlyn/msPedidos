package com.fiap.msPedidos.infra.controller.pedido;

import com.fiap.msPedidos.app.usecases.pedidos.ConsultarPedido;
import com.fiap.msPedidos.app.usecases.pedidos.EnviarParaEntrega;
import com.fiap.msPedidos.app.usecases.pedidos.FazerPedido;
import com.fiap.msPedidos.domain.entity.Pedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Endpoints para gerenciamento de pedidos")
public class PedidoController {

    private final ConsultarPedido consultarPedido;
    private final FazerPedido fazerPedido;
    private final EnviarParaEntrega enviarParaEntrega;

    public PedidoController(ConsultarPedido consultarPedido, FazerPedido fazerPedido, EnviarParaEntrega enviarParaEntrega) {
        this.consultarPedido = consultarPedido;
        this.fazerPedido = fazerPedido;
        this.enviarParaEntrega = enviarParaEntrega;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar um pedido", description = "Consulta um pedido.")
    public PedidoDTO consultarPedido(@PathVariable Long id) {
        Pedido pedido = consultarPedido.consultarPedido(id);
        if(pedido == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
        return new PedidoDTO(pedido.getId(),
                pedido.getStatus(),
                pedido.getEnderecoEntrega(),
                pedido.getProdutos(),
                pedido.getCliente());
    }

    @PostMapping
    @Operation(summary = "Fazer um pedido", description = "Realiza um pedido, validando a disponibilidade dos produtos e envia para o centro de distribuíção.")
    public PedidoDTO fazerPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = fazerPedido.fazerPedido(pedidoDTO.produtos(), pedidoDTO.cliente(), pedidoDTO.enderecoEntrega());
        return new PedidoDTO(pedido.getId(),
                pedido.getStatus(),
                pedido.getEnderecoEntrega(),
                pedido.getProdutos(),
                pedido.getCliente());
    }

    @PostMapping("/enviar")
    public EntregaDTO enviarParaEntrega(@RequestParam Long idPedido){
        return enviarParaEntrega.enviarParaEntrega(idPedido);
    }
}
