package com.fiap.msPedidos.infra.gateways.pedido;

import com.fiap.msPedidos.app.gateways.pedidos.VenderProdutosInterface;
import com.fiap.msPedidos.config.RabbitConfig;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import com.fiap.msPedidos.infra.gateways.produto.ProdutoPedidoMapper;
import com.fiap.msPedidos.infra.persistence.produto.ProdutoPedidoMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepositorioDePedidoCloudStream implements
        VenderProdutosInterface {

    private final RabbitTemplate rabbitTemplate;
    private final ProdutoPedidoMapper mapper;

    @Autowired
    public RepositorioDePedidoCloudStream(RabbitTemplate rabbitTemplate, ProdutoPedidoMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    @Override
    public void venderProdutos(List<ProdutoPedido> produtos) {
        List<ProdutoPedidoMessage> convertedMessages = produtos.stream()
                .map(mapper::toMessage)
                .collect(Collectors.toList());
        try {
            convertedMessages.forEach(message -> {
                rabbitTemplate.convertAndSend(RabbitConfig.FILA_PEDIDOS, message);
                System.out.println("Produto enviado para a fila: " + message);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
