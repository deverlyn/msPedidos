package com.fiap.msPedidos.infra.persistence.produto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPedidoEntity {

    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private int quantidade;

}
