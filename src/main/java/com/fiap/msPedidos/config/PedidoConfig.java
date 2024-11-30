package com.fiap.msPedidos.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.msPedidos.app.gateways.pedidos.*;
import com.fiap.msPedidos.app.usecases.pedidos.*;
import com.fiap.msPedidos.infra.gateways.pedido.PedidoMapper;
import com.fiap.msPedidos.infra.gateways.pedido.RepositorioDePedidoCloudStream;
import com.fiap.msPedidos.infra.gateways.pedido.RepositorioDePedidoHTTP;
import com.fiap.msPedidos.infra.gateways.pedido.RepositorioDePedidoJPA;
import com.fiap.msPedidos.infra.gateways.produto.ProdutoPedidoMapper;
import com.fiap.msPedidos.infra.persistence.pedido.PedidoRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PedidoConfig {

    @Bean
    AtualizarPedido atualizarPedido(AtualizarPedidoInterface atualizarPedidoInterface){
        return new AtualizarPedido(atualizarPedidoInterface);
    }

    @Bean
    ChecarProdutos checarProdutos(ChecarProdutosInterface checarProdutosInterface){
        return new ChecarProdutos(checarProdutosInterface);
    }

    @Bean
    EnviarParaEntrega enviarParaEntrega(EnviarParaEntregaInterface enviarParaEntregaInterface){
        return new EnviarParaEntrega(enviarParaEntregaInterface);
    }

    @Bean
    ConsultarPedido consultarPedido(ConsultarPedidoInterface consultarPedidoInterface){
        return new ConsultarPedido(consultarPedidoInterface);
    }

    @Bean
    FazerPedido fazerPedido(FazerPedidoInterface fazerPedidoInterface){
        return new FazerPedido(fazerPedidoInterface);
    }

    @Bean
    ValidarCliente validarCliente(ValidarClienteInterface validarClienteInterface){
        return new ValidarCliente(validarClienteInterface);
    }

    @Bean
    ValidarEndereco validarEndereco(ValidarEnderecoInterface validarEnderecoInterface){
        return new ValidarEndereco(validarEnderecoInterface);
    }

    @Bean
    VenderProdutos venderProdutos(VenderProdutosInterface venderProdutosInterface){
        return new VenderProdutos(venderProdutosInterface);
    }

    @Bean
    RepositorioDePedidoJPA repositorioDePedidoJPA(PedidoRepository repositorio, PedidoMapper pedidoMapper, RepositorioDePedidoHTTP http, RepositorioDePedidoCloudStream repositorioDePedidoCloudStream){
        return new RepositorioDePedidoJPA(repositorio, pedidoMapper, http, repositorioDePedidoCloudStream);
    }

    @Bean
    PedidoMapper pedidoMapper(ProdutoPedidoMapper produtoPedidoMapper){
        return new PedidoMapper(produtoPedidoMapper);
    }

    @Bean
    ProdutoPedidoMapper produtoPedidoMapper(){
        return new ProdutoPedidoMapper();
    }

    @Bean
    RepositorioDePedidoHTTP repositorioDePedidoHTTP(RestTemplate restTemplate, ObjectMapper objectMapper){
        return new RepositorioDePedidoHTTP(restTemplate, objectMapper);
    }


    @Bean
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
