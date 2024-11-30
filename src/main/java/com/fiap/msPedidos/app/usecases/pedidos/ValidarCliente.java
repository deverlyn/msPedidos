package com.fiap.msPedidos.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.ValidarClienteInterface;

public class ValidarCliente {

    private final ValidarClienteInterface validarClienteInterface;

    public ValidarCliente(ValidarClienteInterface validarClienteInterface) {
        this.validarClienteInterface = validarClienteInterface;
    }

    public Boolean validarCliente(Long id){
        return validarClienteInterface.validarCliente(id);
    }
}
