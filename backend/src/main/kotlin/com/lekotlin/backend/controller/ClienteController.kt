package com.lekotlin.backend.controller

import com.lekotlin.backend.dto.request.ClienteRequest
import com.lekotlin.backend.dto.response.ClienteResponse
import com.lekotlin.backend.mapper.ClienteMapper
import com.lekotlin.backend.service.ClienteService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clientes")
@Tag(
    name = "Clientes",
    description = "Operações de gerenciamento de clientes"
)
@SecurityRequirement(name = "bearerAuth")
class ClienteController(
    private val clienteService: ClienteService
) {

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    fun listarTodos(): List<ClienteResponse> {
        return clienteService.listarTodos()
            .map { ClienteMapper.paraResposta(it) }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    fun buscarPorId(@PathVariable id: Long): ClienteResponse {
        val cliente = clienteService.buscarPorId(id)
        return ClienteMapper.paraResposta(cliente)
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar clientes pelo nome")
    fun buscarPorNome(
        @RequestParam nome: String
    ): List<ClienteResponse> {
        return clienteService.buscarPorNome(nome)
            .map { ClienteMapper.paraResposta(it) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um novo cliente")
    fun cadastrar(
        @Valid @RequestBody request: ClienteRequest
    ): ClienteResponse {
        val cliente = ClienteMapper.paraEntidade(request)
        val salvo = clienteService.cadastrar(cliente)
        return ClienteMapper.paraResposta(salvo)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um cliente existente")
    fun atualizar(
        @PathVariable id: Long,
        @Valid @RequestBody request: ClienteRequest
    ): ClienteResponse {
        val dados = ClienteMapper.paraEntidade(request)
        val atualizado = clienteService.atualizar(id, dados)
        return ClienteMapper.paraResposta(atualizado)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir um cliente")
    fun excluir(@PathVariable id: Long) {
        clienteService.excluir(id)
    }
}