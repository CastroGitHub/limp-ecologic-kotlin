
package com.lekotlin.frontend

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class ClienteViewModel(
    private val repositorio: ClienteRepository = ClienteRepository()
) : ViewModel() {

    var clientes by mutableStateOf<List<ClienteResponse>>(emptyList())
        private set

    var carregando by mutableStateOf(false)
        private set

    var erro by mutableStateOf<String?>(null)
        private set

    var mensagem by mutableStateOf<String?>(null)
        private set

    fun listarClientes() {
        executarConsulta {
            clientes = repositorio.listar()
        }
    }

    fun buscarClientes(nome: String) {
        if (nome.isBlank()) {
            listarClientes()
            return
        }

        executarConsulta {
            clientes = repositorio.buscarPorNome(nome.trim())
        }
    }

    fun cadastrarCliente(cliente: ClienteRequest) {
        executarOperacao("Cliente cadastrado com sucesso!") {
            repositorio.cadastrar(cliente)
        }
    }

    fun atualizarCliente(id: Long, cliente: ClienteRequest) {
        executarOperacao("Cliente atualizado com sucesso!") {
            repositorio.atualizar(id, cliente)
        }
    }

    fun excluirCliente(id: Long) {
        executarOperacao("Cliente excluído com sucesso!") {
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
                erro = e.message ?: "Erro ao consultar clientes."
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
                clientes = repositorio.listar()
                mensagem = mensagemSucesso
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                erro = e.message ?: "Não foi possível concluir a operação."
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
