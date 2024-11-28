package com.fiap.msPedidos.domain.entity;

public class ProdutoPedido {
    private Long id;
    private int quantidade;

    public ProdutoPedido() {
    }

    public ProdutoPedido(Long id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}