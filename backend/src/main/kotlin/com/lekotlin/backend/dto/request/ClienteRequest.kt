
package com.lekotlin.backend.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class ClienteRequest(

    @field:NotBlank(message = "O nome é obrigatório")
    @field:Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    val nome: String,

    @field:NotBlank(message = "O telefone é obrigatório")
    @field:Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    val telefone: String,

    @field:NotBlank(message = "O e-mail é obrigatório")
    @field:Email(message = "Informe um e-mail válido")
    @field:Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    val email: String,

    @field:NotBlank(message = "A cidade é obrigatória")
    @field:Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
    val cidade: String,

    @field:NotBlank(message = "O bairro é obrigatório")
    @field:Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    val bairro: String,

    @field:NotBlank(message = "O endereço é obrigatório")
    @field:Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres")
    val endereco: String,

    @field:NotBlank(message = "O status é obrigatório")
    @field:Pattern(
        regexp = "PENDENTE|AGENDADO|REALIZADO",
        message = "O status deve ser PENDENTE, AGENDADO ou REALIZADO"
    )
    val status: String = "PENDENTE",

    val ativo: Boolean = true
)
