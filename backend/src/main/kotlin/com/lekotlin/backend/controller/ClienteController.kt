
package com.lekotlin.backend.controller

import com.lekotlin.backend.dto.request.ClienteRequest
import com.lekotlin.backend.dto.response.ClienteResponse
import com.lekotlin.backend.mapper.ClienteMapper
import com.lekotlin.backend.service.ClienteService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clientes")
class ClienteController(
    private val clienteService: ClienteService
) {

    @GetMapping
    fun listarTodos(): List<ClienteResponse> {
        return clienteService.listarTodos()
            .map { ClienteMapper.paraResposta(it) }
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ClienteResponse {
        val cliente = clienteService.buscarPorId(id)
        return ClienteMapper.paraResposta(cliente)
    }

    @GetMapping("/buscar")
    fun buscarPorNome(@RequestParam nome: String): List<ClienteResponse> {
        return clienteService.buscarPorNome(nome)
            .map { ClienteMapper.paraResposta(it) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun cadastrar(@Valid @RequestBody request: ClienteRequest): ClienteResponse {
        val cliente = ClienteMapper.paraEntidade(request)
        val salvo = clienteService.cadastrar(cliente)
        return ClienteMapper.paraResposta(salvo)
    }

    @PutMapping("/{id}")
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
    fun excluir(@PathVariable id: Long) {
        clienteService.excluir(id)
    }
}
