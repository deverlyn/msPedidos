package com.fiap.msPedidos.infra.controller.pedido;

public record EntregaDTO(
        Long id,
        Long idEntregador,
        String status,
        Long idPedido,
        Long idCliente,
        Long idEndereco
) {
}
