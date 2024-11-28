package com.fiap.msPedidos.infra.persistence.pedido;

import com.fiap.msPedidos.infra.persistence.produto.ProdutoPedidoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long enderecoEntrega;

    @ElementCollection
    @CollectionTable(name = "produto_pedido", joinColumns = @JoinColumn(name = "pedido_id"))
    private List<ProdutoPedidoEntity> produtos;

    @Column(nullable = false)
    private Long cliente;
}
