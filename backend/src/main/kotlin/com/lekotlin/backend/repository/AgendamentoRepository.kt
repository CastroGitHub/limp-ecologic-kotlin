
package com.lekotlin.backend.repository

import com.lekotlin.backend.entity.Agendamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AgendamentoRepository : JpaRepository<Agendamento, Long> {

    @Query("SELECT a FROM Agendamento a WHERE LOWER(a.nomeCliente) LIKE LOWER(CONCAT('%', :nomeCliente, '%'))")
    fun buscarPorNomeCliente(
        @Param("nomeCliente") nomeCliente: String
    ): List<Agendamento>
}
