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



    public int getQuantidade() {
        return quantidade;
    }


}