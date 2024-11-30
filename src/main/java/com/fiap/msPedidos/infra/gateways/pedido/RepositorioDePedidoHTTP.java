package com.fiap.msPedidos.infra.gateways.pedido;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.msPedidos.app.gateways.pedidos.ChecarProdutosInterface;
import com.fiap.msPedidos.app.gateways.pedidos.EnviarParaEntregaInterface;
import com.fiap.msPedidos.app.gateways.pedidos.ValidarClienteInterface;
import com.fiap.msPedidos.app.gateways.pedidos.ValidarEnderecoInterface;
import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

public class RepositorioDePedidoHTTP implements
        ChecarProdutosInterface,
        EnviarParaEntregaInterface,
        ValidarClienteInterface,
        ValidarEnderecoInterface {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Value("${produtos.URI}")
    private String produtosURI;

    @Value("${produtos.PORT}")
    private String produtosPort;

    @Value("${produtos.consultar.ENDPOINT}")
    private String consultarProdutosEndpoint;

    @Value("${produtos.vender.ENDPOINT}")
    private String venderProdutosEndpoint;

    @Value("${entrega.URI}")
    private String entregaURI;

    @Value("${entrega.PORT}")
    private String entregaPORT;

    @Value("${entrega.receber.ENDPOINT}")
    private String entregaEndpoint;

    @Value("${endereco.URI}")
    private String enderecoURI;

    @Value("${endereco.PORT}")
    private String enderecoPORT;

    @Value("${endereco.consultar.ENDPOINT}")
    private String enderecoENDPOINT;

    @Value("${cliente.URI}")
    private String clienteURI;

    @Value("${cliente.PORT}")
    private String clientePORT;

    @Value("${cliente.consultar.ENDPOINT}")
    private String clienteENDPOINT;

    public RepositorioDePedidoHTTP(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean checarProdutos(List<ProdutoPedido> produtos) {
        for (ProdutoPedido produto : produtos) {
            Long id = produto.getId();
            int quantidade = produto.getQuantidade();

            try {
                ResponseEntity<Boolean> response = restTemplate.getForEntity(
                        produtosURI +  produtosPort + consultarProdutosEndpoint,
                        Boolean.class,
                        id,
                        quantidade
                );
                if (Boolean.FALSE.equals(response.getBody())) {
                    return false;
                }
            } catch (HttpClientErrorException.NotFound e) {
                throw new NoSuchElementException("Produto não encontrado no microsserviço");
            } catch (Exception e) {
                throw new RuntimeException("Erro na comunicação entre microsserviços", e);
            }
        }
        return true;
    }

    @Override
    public Boolean enviarParaEntrega(Pedido pedido) {

        //TODO: not yet implemented
//        try {
//            ResponseEntity<Boolean> response = restTemplate.postForEntity(
//                    produtosURI + produtosPort + consultarProdutosEndpoint,
//                    pedido,
//                    Boolean.class
//            );
//
//            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
//                return response.getBody();
//            } else {
//                throw new RuntimeException("Resposta inválida do microsserviço");
//            }
//        } catch (
//                HttpClientErrorException.NotFound e) {
//            throw new NoSuchElementException("Endpoint não encontrado no microsserviço", e);
//        } catch (
//                Exception e) {
//            throw new RuntimeException("Erro na comunicação entre microsserviços", e);
//        }
        return true;
    }

    @Override
    public Boolean validarCliente(Long id) {
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(
                    clienteURI +  clientePORT + clienteENDPOINT,
                    Boolean.class,
                    id
            );
            if (Boolean.FALSE.equals(response.getBody())) {
                return false;
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new NoSuchElementException("Cliente não encontrado");
        } catch (Exception e) {
            throw new RuntimeException("Erro na comunicação entre microsserviços", e);
        }
        return true;
    }

    @Override
    public Boolean validarEndereco(Long id) {
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(
                    enderecoURI +  enderecoPORT + enderecoENDPOINT,
                    Boolean.class,
                    id
            );
            if (Boolean.FALSE.equals(response.getBody())) {
                return false;
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new NoSuchElementException("Produto não encontrado no microsserviço");
        } catch (Exception e) {
            throw new RuntimeException("Erro na comunicação entre microsserviços", e);
        }
        return true;
    }


}
