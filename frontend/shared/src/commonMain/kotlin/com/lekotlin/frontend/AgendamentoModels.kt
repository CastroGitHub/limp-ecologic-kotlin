
package com.lekotlin.frontend

import kotlinx.serialization.Serializable

@Serializable
data class AgendamentoRequest(
    val nomeCliente: String,
    val endereco: String,
    val data: String,
    val hora: String,
    val status: String = "PENDENTE",
    val descricao: String,
    val valor: Double
)

@Serializable
data class AgendamentoResponse(
    val id: Long,
    val nomeCliente: String,
    val endereco: String,
    val data: String,
    val hora: String,
    val status: String,
    val descricao: String,
    val valor: Double
)
