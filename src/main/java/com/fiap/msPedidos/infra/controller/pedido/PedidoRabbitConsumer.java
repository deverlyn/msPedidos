package com.fiap.msPedidos.infra.controller.pedido;

import com.fiap.msPedidos.app.usecases.pedidos.AtualizarPedido;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoRabbitConsumer {

    @Autowired
    private AtualizarPedido atualizarPedido;

    @RabbitListener(queues = "entregasQueue")
    public void processarMensagem(EntregaDTO entrega) {
        try {
            atualizarPedido.atualizarPedido(entrega.idPedido(), entrega.status());
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }
}
