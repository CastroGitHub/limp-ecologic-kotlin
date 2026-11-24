
package com.lekotlin.frontend

import kotlinx.serialization.Serializable

@Serializable
data class ClienteRequest(
    val nome: String,
    val telefone: String,
    val email: String,
    val cidade: String,
    val bairro: String,
    val endereco: String,
    val status: String = "PENDENTE",
    val ativo: Boolean = true
)

@Serializable
data class ClienteResponse(
    val id: Long,
    val nome: String,
    val telefone: String,
    val email: String,
    val cidade: String,
    val bairro: String,
    val endereco: String,
    val status: String,
    val ativo: Boolean,
    val dataCadastro: String
)
