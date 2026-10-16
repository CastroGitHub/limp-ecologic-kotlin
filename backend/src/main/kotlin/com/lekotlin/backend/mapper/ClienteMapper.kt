
package com.lekotlin.backend.mapper

import com.lekotlin.backend.dto.request.ClienteRequest
import com.lekotlin.backend.dto.response.ClienteResponse
import com.lekotlin.backend.entity.Cliente

object ClienteMapper {

    fun paraEntidade(request: ClienteRequest): Cliente {
        return Cliente(
            nome = request.nome,
            telefone = request.telefone,
            email = request.email,
            cidade = request.cidade,
            bairro = request.bairro,
            endereco = request.endereco,
            status = request.status,
            ativo = request.ativo
        )
    }

    fun paraResposta(cliente: Cliente): ClienteResponse {
        return ClienteResponse(
            id = cliente.id,
            nome = cliente.nome,
            telefone = cliente.telefone,
            email = cliente.email,
            cidade = cliente.cidade,
            bairro = cliente.bairro,
            endereco = cliente.endereco,
            status = cliente.status,
            ativo = cliente.ativo,
            dataCadastro = cliente.dataCadastro
        )
    }
}
