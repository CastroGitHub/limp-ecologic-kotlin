
package com.lekotlin.frontend

import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AgendamentoRepository {

    private val client = ClienteHttp.client

    private val baseUrl = "${ApiClient.BASE_URL}/api/agendamentos"

    suspend fun listar(): List<AgendamentoResponse> {
        return client.get(baseUrl).body()
    }

    suspend fun buscarPorNome(nomeCliente: String): List<AgendamentoResponse> {
        return client.get("$baseUrl/buscar") {
            url {
                parameters.append("nomeCliente", nomeCliente)
            }
        }.body()
    }

    suspend fun buscarPorId(id: Long): AgendamentoResponse {
        return client.get("$baseUrl/$id").body()
    }

    suspend fun cadastrar(
        agendamento: AgendamentoRequest
    ): AgendamentoResponse {
        return client.post(baseUrl) {
            contentType(ContentType.Application.Json)
            setBody(agendamento)
        }.body()
    }

    suspend fun atualizar(
        id: Long,
        agendamento: AgendamentoRequest
    ): AgendamentoResponse {
        return client.put("$baseUrl/$id") {
            contentType(ContentType.Application.Json)
            setBody(agendamento)
        }.body()
    }

    suspend fun excluir(id: Long) {
        client.delete("$baseUrl/$id")
    }
}
