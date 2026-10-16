
package com.lekotlin.backend.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Column
import java.time.LocalDate

@Entity
@Table(name = "clientes")
class Cliente(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    var nome: String,

    @Column(nullable = false, length = 20)
    var telefone: String,

    @Column(nullable = false, length = 100)
    var email: String,

    @Column(nullable = false, length = 100)
    var cidade: String,

    @Column(nullable = false, length = 100)
    var bairro: String,

    @Column(nullable = false, length = 200)
    var endereco: String,

    @Column(nullable = false)
    var ativo: Boolean = true,

    @Column(nullable = false, length = 20)
    var status: String = "PENDENTE",

    @Column(nullable = false)
    var dataCadastro: LocalDate = LocalDate.now()
)
