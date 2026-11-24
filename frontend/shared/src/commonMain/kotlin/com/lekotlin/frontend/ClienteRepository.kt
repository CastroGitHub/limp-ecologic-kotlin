
package com.lekotlin.frontend

import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ClienteRepository {

    private val client = ClienteHttp.client

    private val baseUrl = "${ApiClient.BASE_URL}/api/clientes"

    suspend fun listar(): List<ClienteResponse> {
        return client.get(baseUrl).body()
    }

    suspend fun buscarPorNome(nome: String): List<ClienteResponse> {
        return client.get("$baseUrl/buscar") {
            url {
                parameters.append("nome", nome)
            }
        }.body()
    }

    suspend fun buscarPorId(id: Long): ClienteResponse {
        return client.get("$baseUrl/$id").body()
    }

    suspend fun cadastrar(
        cliente: ClienteRequest
    ): ClienteResponse {
        return client.post(baseUrl) {
            contentType(ContentType.Application.Json)
            setBody(cliente)
        }.body()
    }

    suspend fun atualizar(
        id: Long,
        cliente: ClienteRequest
    ): ClienteResponse {
        return client.put("$baseUrl/$id") {
            contentType(ContentType.Application.Json)
            setBody(cliente)
        }.body()
    }

    suspend fun excluir(id: Long) {
        client.delete("$baseUrl/$id")
    }
}
