
package com.lekotlin.backend.dto.response

import java.time.LocalDate
import java.time.LocalTime

data class AgendamentoResponse(
    val id: Long,
    val nomeCliente: String,
    val endereco: String,
    val data: LocalDate,
    val hora: LocalTime,
    val status: String,
    val descricao: String,
    val valor: Double
)
