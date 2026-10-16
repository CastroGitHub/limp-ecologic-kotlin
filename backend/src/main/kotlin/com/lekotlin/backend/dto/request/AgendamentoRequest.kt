
package com.lekotlin.backend.dto.request

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.time.LocalTime

data class AgendamentoRequest(

    @field:NotBlank(message = "O nome do cliente é obrigatório")
    @field:Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    val nomeCliente: String,

    @field:NotBlank(message = "O endereço é obrigatório")
    @field:Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres")
    val endereco: String,

    @field:NotNull(message = "A data é obrigatória")
    val data: LocalDate,

    @field:NotNull(message = "A hora é obrigatória")
    val hora: LocalTime,

    @field:NotBlank(message = "O status é obrigatório")
    @field:Pattern(
        regexp = "PENDENTE|AGENDADO|REALIZADO",
        message = "O status deve ser PENDENTE, AGENDADO ou REALIZADO"
    )
    val status: String = "PENDENTE",

    @field:NotBlank(message = "A descrição é obrigatória")
    @field:Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    val descricao: String,

    @field:NotNull(message = "O valor é obrigatório")
    @field:DecimalMin(
        value = "0.0",
        inclusive = true,
        message = "O valor não pode ser negativo"
    )
    val valor: Double
)
