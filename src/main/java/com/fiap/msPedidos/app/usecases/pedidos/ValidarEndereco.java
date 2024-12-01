package com.fiap.msPedidos.app.usecases.pedidos;

import com.fiap.msPedidos.app.gateways.pedidos.ValidarEnderecoInterface;

public class ValidarEndereco {

    private final ValidarEnderecoInterface validarEndereco;

    public ValidarEndereco(ValidarEnderecoInterface validarEndereco) {
        this.validarEndereco = validarEndereco;
    }

    public Boolean validarEndereco(Long id){
        if(id == null){
            return false;
        }
        return validarEndereco.validarEndereco(id);
    }
}
