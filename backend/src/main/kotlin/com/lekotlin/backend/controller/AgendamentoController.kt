package com.lekotlin.backend.controller

import com.lekotlin.backend.dto.request.AgendamentoRequest
import com.lekotlin.backend.dto.response.AgendamentoResponse
import com.lekotlin.backend.mapper.AgendamentoMapper
import com.lekotlin.backend.service.AgendamentoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/agendamentos")
@Tag(
    name = "Agendamentos",
    description = "Operações de gerenciamento de agendamentos"
)
@SecurityRequirement(name = "bearerAuth")
class AgendamentoController(
    private val agendamentoService: AgendamentoService
) {

    @GetMapping
    @Operation(summary = "Listar todos os agendamentos")
    fun listarTodos(): List<AgendamentoResponse> {
        return agendamentoService.listarTodos()
            .map { AgendamentoMapper.paraResposta(it) }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar agendamento por ID")
    fun buscarPorId(@PathVariable id: Long): AgendamentoResponse {
        val agendamento = agendamentoService.buscarPorId(id)
        return AgendamentoMapper.paraResposta(agendamento)
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar agendamentos pelo nome do cliente")
    fun buscarPorNomeCliente(
        @RequestParam nomeCliente: String
    ): List<AgendamentoResponse> {
        return agendamentoService.buscarPorNomeCliente(nomeCliente)
            .map { AgendamentoMapper.paraResposta(it) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um novo agendamento")
    fun cadastrar(
        @Valid @RequestBody request: AgendamentoRequest
    ): AgendamentoResponse {
        val agendamento = AgendamentoMapper.paraEntidade(request)
        val salvo = agendamentoService.cadastrar(agendamento)
        return AgendamentoMapper.paraResposta(salvo)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um agendamento existente")
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
    @Operation(summary = "Excluir um agendamento")
    fun excluir(@PathVariable id: Long) {
        agendamentoService.excluir(id)
    }
}