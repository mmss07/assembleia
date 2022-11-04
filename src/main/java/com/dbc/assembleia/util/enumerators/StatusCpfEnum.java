package com.dbc.assembleia.util.enumerators;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusCpfEnum {
    ABLE_TO_VOTE("Habilitado para votar"),
    UNABLE_TO_VOTE("Sem permissão para votar"),
    CPF_INVALIDO("Cpf inválido");
    public final String id;
    public String getid() {
        return this.id;
    }
}
