
package com.lekotlin.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "agendamentos")
class Agendamento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank(message = "O nome do cliente é obrigatório")
    @field:Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    var nomeCliente: String,

    @field:NotBlank(message = "O endereço é obrigatório")
    @field:Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres")
    @Column(nullable = false, length = 200)
    var endereco: String,

    @field:NotNull(message = "A data é obrigatória")
    @Column(nullable = false)
    var data: LocalDate,

    @field:NotNull(message = "A hora é obrigatória")
    @Column(nullable = false)
    var hora: LocalTime,

    @field:NotBlank(message = "O status é obrigatório")
    @field:Pattern(
        regexp = "PENDENTE|AGENDADO|REALIZADO",
        message = "O status deve ser PENDENTE, AGENDADO ou REALIZADO"
    )
    @Column(nullable = false, length = 30)
    var status: String = "PENDENTE",

    @field:NotBlank(message = "A descrição é obrigatória")
    @field:Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    @Column(nullable = false, length = 500)
    var descricao: String,

    @field:DecimalMin(value = "0.0", message = "O valor não pode ser negativo")
    @Column(nullable = false)
    var valor: Double = 0.0
)
