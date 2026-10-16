
package com.lekotlin.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "agendamentos")
class Agendamento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    var nomeCliente: String,

    @Column(nullable = false, length = 200)
    var endereco: String,

    @Column(nullable = false)
    var data: LocalDate,

    @Column(nullable = false)
    var hora: LocalTime,

    @Column(nullable = false, length = 30)
    var status: String = "PENDENTE",

    @Column(nullable = false, length = 500)
    var descricao: String,

    @Column(nullable = false)
    var valor: Double = 0.0
)
