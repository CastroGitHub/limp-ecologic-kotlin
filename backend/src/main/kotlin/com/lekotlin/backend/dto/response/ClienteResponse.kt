
package com.lekotlin.backend.dto.response

import java.time.LocalDate

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
    val dataCadastro: LocalDate
)
