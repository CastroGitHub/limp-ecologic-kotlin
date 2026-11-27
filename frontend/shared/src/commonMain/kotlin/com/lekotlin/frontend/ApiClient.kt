package com.lekotlin.frontend

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiClient {

    const val BASE_URL = "http://localhost:8080"

    val client = HttpClient {

        expectSuccess = true

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }

        defaultRequest {
            SessaoUsuario.token
                ?.takeIf { it.isNotBlank() }
                ?.let { token ->
                    header(
                        HttpHeaders.Authorization,
                        "Bearer $token"
                    )
                }
        }
    }
}
