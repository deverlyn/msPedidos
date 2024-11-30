package com.fiap.msPedidos.infra.gateways.pedido;

import com.fiap.msPedidos.app.gateways.pedidos.AtualizarPedidoInterface;
import com.fiap.msPedidos.app.gateways.pedidos.ConsultarPedidoInterface;
import com.fiap.msPedidos.app.gateways.pedidos.FazerPedidoInterface;
import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import com.fiap.msPedidos.infra.persistence.pedido.PedidoEntity;
import com.fiap.msPedidos.infra.persistence.pedido.PedidoRepository;

import java.util.List;

public class RepositorioDePedidoJPA implements
        AtualizarPedidoInterface,
        ConsultarPedidoInterface,
        FazerPedidoInterface {

    private final PedidoRepository repository;
    private final PedidoMapper pedidoMapper;
    private final RepositorioDePedidoHTTP repositorioDePedidoHTTP;
    private final RepositorioDePedidoCloudStream repositorioDePedidoCloudStream;

    public RepositorioDePedidoJPA(PedidoRepository repository, PedidoMapper pedidoMapper, RepositorioDePedidoHTTP repositorioDePedidoHTTP, RepositorioDePedidoCloudStream repositorioDePedidoCloudStream) {
        this.repository = repository;
        this.pedidoMapper = pedidoMapper;
        this.repositorioDePedidoHTTP = repositorioDePedidoHTTP;
        this.repositorioDePedidoCloudStream = repositorioDePedidoCloudStream;
    }

    @Override
    public void atualizarPedido(Long idPedido, String status) {
        PedidoEntity pedido = repository.findById(idPedido).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        pedido.setStatus(status);
        repository.save(pedido);
    }

    @Override
    public Pedido consultarPedido(Long id) {
        PedidoEntity pedido = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        return  pedidoMapper.toDomain(pedido);
    }

    @Override
    public Pedido fazerPedido(List<ProdutoPedido> idProdutos, Long idCliente, Long idEndereco) {
        if (repositorioDePedidoHTTP.checarProdutos(idProdutos)
        && repositorioDePedidoHTTP.validarEndereco(idEndereco)
        && repositorioDePedidoHTTP.validarCliente(idCliente)){
            Pedido pedido = new Pedido("Criado", idCliente, idProdutos, idEndereco);
            repositorioDePedidoCloudStream.venderProdutos(idProdutos);
            if(repositorioDePedidoHTTP.enviarParaEntrega(pedido)) {
                return pedidoMapper.toDomain(repository.save(pedidoMapper.toEntity(pedido)));
            }else {
            throw new IllegalArgumentException("Não pudemos fazer a conexão com o microsserviço de Entrega");
        }
        } else {
            throw new IllegalArgumentException("Algum produto não existe ou pode ter excedido a quantidade em estoque");
        }

    }
}
