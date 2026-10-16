
package com.lekotlin.backend.controller

import com.lekotlin.backend.dto.request.AgendamentoRequest
import com.lekotlin.backend.dto.response.AgendamentoResponse
import com.lekotlin.backend.mapper.AgendamentoMapper
import com.lekotlin.backend.service.AgendamentoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/agendamentos")
class AgendamentoController(
    private val agendamentoService: AgendamentoService
) {

    @GetMapping
    fun listarTodos(): List<AgendamentoResponse> {
        return agendamentoService.listarTodos()
            .map { AgendamentoMapper.paraResposta(it) }
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): AgendamentoResponse {
        val agendamento = agendamentoService.buscarPorId(id)
        return AgendamentoMapper.paraResposta(agendamento)
    }

    @GetMapping("/buscar")
    fun buscarPorNomeCliente(
        @RequestParam nomeCliente: String
    ): List<AgendamentoResponse> {
        return agendamentoService.buscarPorNomeCliente(nomeCliente)
            .map { AgendamentoMapper.paraResposta(it) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun cadastrar(
        @Valid @RequestBody request: AgendamentoRequest
    ): AgendamentoResponse {
        val agendamento = AgendamentoMapper.paraEntidade(request)
        val salvo = agendamentoService.cadastrar(agendamento)
        return AgendamentoMapper.paraResposta(salvo)
    }

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: Long,
        @Valid @RequestBody request: AgendamentoRequest
    ): AgendamentoResponse {
        val dados = AgendamentoMapper.paraEntidade(request)
        val atualizado = agendamentoService.atualizar(id, dados)
        return AgendamentoMapper.paraResposta(atualizado)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun excluir(@PathVariable id: Long) {
        agendamentoService.excluir(id)
    }
}
