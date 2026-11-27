package com.lekotlin.frontend

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repositorio: AuthRepository = AuthRepository()
) : ViewModel() {

    var carregando by mutableStateOf(false)
        private set

    var erro by mutableStateOf<String?>(null)
        private set

    var sucesso by mutableStateOf(false)
        private set

    fun login(email: String, senha: String) {
        executarAutenticacao {
            val resposta = repositorio.login(
                email = email.trim(),
                senha = senha
            )

            SessaoUsuario.iniciarSessao(resposta)
        }
    }

    fun cadastrar(
        nome: String,
        email: String,
        senha: String
    ) {
        executarAutenticacao {
            repositorio.cadastrar(
                nome = nome.trim(),
                email = email.trim(),
                senha = senha
            )
        }
    }

    private fun executarAutenticacao(
        operacao: suspend () -> Unit
    ) {
        if (carregando) return

        viewModelScope.launch {
            carregando = true
            erro = null
            sucesso = false

            try {
                operacao()
                sucesso = true

            } catch (e: CancellationException) {
                throw e

            } catch (e: ResponseException) {
                erro = when (e.response.status.value) {
                    400 -> "Dados inválidos. Confira as informações."
                    401 -> "E-mail ou senha incorretos."
                    403 -> "Acesso não autorizado."
                    404 -> "Serviço de autenticação não encontrado."
                    409 -> "Este e-mail já está cadastrado."
                    422 -> "Não foi possível validar os dados informados."
                    in 500..599 -> "Erro no servidor. Tente novamente mais tarde."
                    else -> "Não foi possível concluir a autenticação."
                }

            } catch (e: Exception) {
                erro = "Não foi possível conectar ao servidor. Verifique sua conexão e tente novamente."

            } finally {
                carregando = false
            }
        }
    }

    fun limparMensagens() {
        erro = null
        sucesso = false
    }
}
