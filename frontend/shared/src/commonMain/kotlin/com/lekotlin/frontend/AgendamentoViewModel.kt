
package com.lekotlin.frontend

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class AgendamentoViewModel(
    private val repositorio: AgendamentoRepository = AgendamentoRepository()
) : ViewModel() {

    var agendamentos by mutableStateOf<List<AgendamentoResponse>>(emptyList())
        private set

    var carregando by mutableStateOf(false)
        private set

    var erro by mutableStateOf<String?>(null)
        private set

    var mensagem by mutableStateOf<String?>(null)
        private set

    fun listarAgendamentos() {
        executarConsulta {
            agendamentos = repositorio.listar()
        }
    }

    fun buscarAgendamentos(nomeCliente: String) {
        if (nomeCliente.isBlank()) {
            listarAgendamentos()
            return
        }

        executarConsulta {
            agendamentos = repositorio.buscarPorNome(nomeCliente.trim())
        }
    }

    fun cadastrarAgendamento(agendamento: AgendamentoRequest) {
        executarOperacao("Agendamento cadastrado com sucesso!") {
            repositorio.cadastrar(agendamento)
        }
    }

    fun atualizarAgendamento(
        id: Long,
        agendamento: AgendamentoRequest
    ) {
        executarOperacao("Agendamento atualizado com sucesso!") {
            repositorio.atualizar(id, agendamento)
        }
    }

    fun excluirAgendamento(id: Long) {
        executarOperacao("Agendamento excluído com sucesso!") {
            repositorio.excluir(id)
        }
    }

    private fun executarConsulta(
        consulta: suspend () -> Unit
    ) {
        viewModelScope.launch {
            carregando = true
            erro = null

            try {
                consulta()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                erro = e.message ?: "Erro ao consultar agendamentos."
            } finally {
                carregando = false
            }
        }
    }

    private fun executarOperacao(
        mensagemSucesso: String,
        operacao: suspend () -> Unit
    ) {
        viewModelScope.launch {
            carregando = true
            erro = null
            mensagem = null

            try {
                operacao()
                agendamentos = repositorio.listar()
                mensagem = mensagemSucesso
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                erro = e.message
                    ?: "Não foi possível concluir a operação."
            } finally {
                carregando = false
            }
        }
    }

    fun limparMensagens() {
        erro = null
        mensagem = null
    }
}
