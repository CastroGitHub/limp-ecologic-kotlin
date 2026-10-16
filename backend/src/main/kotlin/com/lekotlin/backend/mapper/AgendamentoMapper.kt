
package com.lekotlin.backend.mapper

import com.lekotlin.backend.dto.request.AgendamentoRequest
import com.lekotlin.backend.dto.response.AgendamentoResponse
import com.lekotlin.backend.entity.Agendamento

object AgendamentoMapper {

    fun paraEntidade(request: AgendamentoRequest): Agendamento {
        return Agendamento(
            nomeCliente = request.nomeCliente,
            endereco = request.endereco,
            data = request.data,
            hora = request.hora,
            status = request.status,
            descricao = request.descricao,
            valor = request.valor
        )
    }

    fun paraResposta(agendamento: Agendamento): AgendamentoResponse {
        return AgendamentoResponse(
            id = agendamento.id,
            nomeCliente = agendamento.nomeCliente,
            endereco = agendamento.endereco,
            data = agendamento.data,
            hora = agendamento.hora,
            status = agendamento.status,
            descricao = agendamento.descricao,
            valor = agendamento.valor
        )
    }
}
