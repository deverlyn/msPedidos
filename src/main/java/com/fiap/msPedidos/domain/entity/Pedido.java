package com.fiap.msPedidos.domain.entity;

import java.util.List;

public class Pedido {

    private Long id;
    private String status;
    private Long enderecoEntrega;
    private List<ProdutoPedido> produtos;
    private Long cliente;

    public Pedido() {
    }

    public Pedido(String status, Long enderecoEntrega, List<ProdutoPedido> produtos, Long cliente) {
        this.status = status;
        this.enderecoEntrega = enderecoEntrega;
        this.produtos = produtos;
        this.cliente = cliente;
    }

    public Pedido(Long id, String status, Long enderecoEntrega, List<ProdutoPedido> produtos, Long cliente) {
        this.id = id;
        this.status = status;
        this.enderecoEntrega = enderecoEntrega;
        this.produtos = produtos;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Long enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<ProdutoPedido> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoPedido> produtos) {
        this.produtos = produtos;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }
}