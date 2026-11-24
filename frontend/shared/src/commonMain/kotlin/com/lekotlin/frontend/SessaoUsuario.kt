package com.lekotlin.frontend

object SessaoUsuario {

    var token: String? = null
        private set

    var nome: String? = null
        private set

    var email: String? = null
        private set

    val autenticado: Boolean
        get() = !token.isNullOrBlank()

    fun iniciarSessao(resposta: AuthResponse) {
        token = resposta.token
        nome = resposta.nome
        email = resposta.email
    }

    fun encerrarSessao() {
        token = null
        nome = null
        email = null
    }
}