package com.fiap.msPedidos.unit.app.usecases.pedidos;


import com.fiap.msPedidos.app.gateways.pedidos.AtualizarPedidoInterface;
import com.fiap.msPedidos.app.usecases.pedidos.AtualizarPedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class AtualizarPedidoTest {


        @Test
        @DisplayName("Deve atualizar o pedido com sucesso")
        void deveAtualizarPedidoComSucesso() {
            AtualizarPedidoInterface atualizarPedidoInterfaceMock = Mockito.mock(AtualizarPedidoInterface.class);
            AtualizarPedido atualizarPedido = new AtualizarPedido(atualizarPedidoInterfaceMock);

            Long idPedido = 1L;
            String novoStatus = "EM_PROCESSAMENTO";

            atualizarPedido.atualizarPedido(idPedido, novoStatus);

            verify(atualizarPedidoInterfaceMock, times(1)).atualizarPedido(idPedido, novoStatus);
        }

        @Test
        @DisplayName("Não deve chamar atualizarPedido se o ID for nulo")
        void naoDeveChamarAtualizarPedidoSeIdForNulo() {
            AtualizarPedidoInterface atualizarPedidoInterfaceMock = Mockito.mock(AtualizarPedidoInterface.class);
            AtualizarPedido atualizarPedido = new AtualizarPedido(atualizarPedidoInterfaceMock);

            String novoStatus = "CANCELADO";

            atualizarPedido.atualizarPedido(null, novoStatus);

            verify(atualizarPedidoInterfaceMock, never()).atualizarPedido(any(), eq(novoStatus));
        }

        @Test
        @DisplayName("Não deve chamar atualizarPedido se o status for nulo")
        void naoDeveChamarAtualizarPedidoSeStatusForNulo() {

            AtualizarPedidoInterface atualizarPedidoInterfaceMock = Mockito.mock(AtualizarPedidoInterface.class);
            AtualizarPedido atualizarPedido = new AtualizarPedido(atualizarPedidoInterfaceMock);

            Long idPedido = 1L;
            atualizarPedido.atualizarPedido(idPedido, null);

            verify(atualizarPedidoInterfaceMock, never()).atualizarPedido(eq(idPedido), any());
        }

}