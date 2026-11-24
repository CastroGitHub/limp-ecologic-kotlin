
package com.lekotlin.frontend

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val senha: String
)

@Serializable
data class CadastroRequest(
    val nome: String,
    val email: String,
    val senha: String
)

@Serializable
data class AuthResponse(
    val token: String,
    val tipo: String = "Bearer",
    val nome: String,
    val email: String
)
