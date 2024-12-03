package com.fiap.msPedidos.infra.persistence.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPedidoMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private int quantidade;
}
