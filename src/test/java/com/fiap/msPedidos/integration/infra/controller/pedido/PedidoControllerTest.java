package com.fiap.msPedidos.integration.infra.controller.pedido;

import com.fiap.msPedidos.app.usecases.pedidos.ConsultarPedido;
import com.fiap.msPedidos.app.usecases.pedidos.FazerPedido;
import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PedidoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConsultarPedido consultarPedido;

    @Autowired
    private FazerPedido fazerPedido;

    @Test
    @DisplayName("Deve consultar um pedido com sucesso")
    void deveConsultarPedidoComSucesso() throws Exception {
        Pedido pedido = new Pedido(1L, "CRIADO", 101L, List.of(new ProdutoPedido(1L,  2)), 202L);
        fazerPedido.fazerPedido(pedido.getProdutos(), pedido.getCliente(), pedido.getEnderecoEntrega());

        mockMvc.perform(get("/pedidos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.status", is("CRIADO")))
                .andExpect(jsonPath("$.enderecoEntrega", is(101)))
                .andExpect(jsonPath("$.cliente", is(202)))
                .andExpect(jsonPath("$.produtos", hasSize(1)));
    }

    @Test
    @DisplayName("Deve criar um pedido com sucesso")
    void deveCriarPedidoComSucesso() throws Exception {
        String pedidoJson = """
                {
                    "id": null,
                    "status": "CRIADO",
                    "enderecoEntrega": 101,
                    "produtos": [
                        { "id": 1, "nome": "Produto A", "quantidade": 2 }
                    ],
                    "cliente": 202
                }
                """;

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pedidoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.status", is("CRIADO")))
                .andExpect(jsonPath("$.enderecoEntrega", is(101)))
                .andExpect(jsonPath("$.cliente", is(202)))
                .andExpect(jsonPath("$.produtos", hasSize(1)));
    }

    @Test
    @DisplayName("Deve retornar 404 ao consultar pedido inexistente")
    void deveRetornar404AoConsultarPedidoInexistente() throws Exception {
        mockMvc.perform(get("/pedidos/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}
