
package com.lekotlin.backend.dto.response

data class AuthResponse(
    val token: String,
    val tipo: String = "Bearer",
    val nome: String,
    val email: String
)
