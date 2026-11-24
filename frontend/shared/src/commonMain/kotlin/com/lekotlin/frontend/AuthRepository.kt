
package com.lekotlin.frontend

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRepository {

    private val client = ApiClient.client

    suspend fun login(
        email: String,
        senha: String
    ): AuthResponse {
        return client.post("${ApiClient.BASE_URL}/api/auth/signin") {
            contentType(ContentType.Application.Json)
            setBody(
                LoginRequest(
                    email = email,
                    senha = senha
                )
            )
        }.body()
    }

    suspend fun cadastrar(
        nome: String,
        email: String,
        senha: String
    ): AuthResponse {
        return client.post("${ApiClient.BASE_URL}/api/auth/signup") {
            contentType(ContentType.Application.Json)
            setBody(
                CadastroRequest(
                    nome = nome,
                    email = email,
                    senha = senha
                )
            )
        }.body()
    }
}
