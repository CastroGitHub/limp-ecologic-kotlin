
package com.lekotlin.backend.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Column
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate

@Entity
@Table(name = "clientes")
class Cliente(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank(message = "O nome é obrigatório")
    @field:Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    var nome: String,

    @field:NotBlank(message = "O telefone é obrigatório")
    @field:Size(min = 8, max = 20, message = "O telefone deve ter entre 8 e 20 caracteres")
    @Column(nullable = false, length = 20)
    var telefone: String,

    @field:NotBlank(message = "O e-mail é obrigatório")
    @field:Email(message = "Informe um e-mail válido")
    @field:Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    var email: String,

    @field:NotBlank(message = "A cidade é obrigatória")
    @field:Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    var cidade: String,

    @field:NotBlank(message = "O bairro é obrigatório")
    @field:Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    var bairro: String,

    @field:NotBlank(message = "O endereço é obrigatório")
    @field:Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres")
    @Column(nullable = false, length = 200)
    var endereco: String,

    @Column(nullable = false)
    var ativo: Boolean = true,

    @field:NotBlank(message = "O status é obrigatório")
    @field:Pattern(
        regexp = "PENDENTE|AGENDADO|REALIZADO",
        message = "O status deve ser PENDENTE, AGENDADO ou REALIZADO"
    )
    @Column(nullable = false, length = 20)
    var status: String = "PENDENTE",

    @Column(nullable = false)
    var dataCadastro: LocalDate = LocalDate.now()
)
