package com.fiap.msPedidos.componente.infra.controller.pedido;


import com.fiap.msPedidos.app.usecases.pedidos.ConsultarPedido;
import com.fiap.msPedidos.app.usecases.pedidos.FazerPedido;
import com.fiap.msPedidos.domain.entity.Pedido;
import com.fiap.msPedidos.domain.entity.ProdutoPedido;
import com.fiap.msPedidos.infra.controller.pedido.PedidoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PedidoControllerTest {

    private MockMvc mockMvc;

    private ConsultarPedido consultarPedido;
    private FazerPedido fazerPedido;

    @BeforeEach
    void setup() {
        consultarPedido = mock(ConsultarPedido.class);
        fazerPedido = mock(FazerPedido.class);

        PedidoController pedidoController = new PedidoController(consultarPedido, fazerPedido);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    @DisplayName("Deve consultar um pedido com sucesso")
    void deveConsultarPedidoComSucesso() throws Exception {
        Pedido pedido = new Pedido(1L, "CRIADO", 101L, List.of(new ProdutoPedido(1L, 2)), 202L);

        when(consultarPedido.consultarPedido(1L)).thenReturn(pedido);

        mockMvc.perform(get("/pedidos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("CRIADO"))
                .andExpect(jsonPath("$.enderecoEntrega").value(101))
                .andExpect(jsonPath("$.cliente").value(202))
                .andExpect(jsonPath("$.produtos[0].id").value(1))
                .andExpect(jsonPath("$.produtos[0].quantidade").value(2));

        verify(consultarPedido, times(1)).consultarPedido(1L);
    }

    @Test
    @DisplayName("Deve retornar 404 ao consultar pedido inexistente")
    void deveRetornar404AoConsultarPedidoInexistente() throws Exception {
        when(consultarPedido.consultarPedido(999L)).thenReturn(null);

        mockMvc.perform(get("/pedidos/{id}", 999L))
                .andExpect(status().isNotFound());

        verify(consultarPedido, times(1)).consultarPedido(999L);
    }
}