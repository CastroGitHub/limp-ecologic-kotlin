
package com.lekotlin.backend.service

import com.lekotlin.backend.entity.Agendamento
import com.lekotlin.backend.repository.AgendamentoRepository
import org.springframework.stereotype.Service

@Service
class AgendamentoService(
    private val agendamentoRepository: AgendamentoRepository
) {

    fun listarTodos(): List<Agendamento> {
        return agendamentoRepository.findAll()
    }

    fun buscarPorId(id: Long): Agendamento {
        return agendamentoRepository.findById(id)
            .orElseThrow {
                NoSuchElementException("Agendamento não encontrado")
            }
    }

    fun cadastrar(agendamento: Agendamento): Agendamento {
        return agendamentoRepository.save(agendamento)
    }

    fun atualizar(id: Long, dados: Agendamento): Agendamento {
        val agendamento = buscarPorId(id)

        agendamento.nomeCliente = dados.nomeCliente
        agendamento.endereco = dados.endereco
        agendamento.data = dados.data
        agendamento.hora = dados.hora
        agendamento.status = dados.status
        agendamento.descricao = dados.descricao
        agendamento.valor = dados.valor

        return agendamentoRepository.save(agendamento)
    }

    fun excluir(id: Long) {
        val agendamento = buscarPorId(id)
        agendamentoRepository.delete(agendamento)
    }

    fun buscarPorNomeCliente(nomeCliente: String): List<Agendamento> {
        return agendamentoRepository.buscarPorNomeCliente(nomeCliente)
    }
}
