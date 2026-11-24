package com.lekotlin.backend.controller

import com.lekotlin.backend.dto.request.CadastroRequest
import com.lekotlin.backend.dto.request.LoginRequest
import com.lekotlin.backend.dto.response.AuthResponse
import com.lekotlin.backend.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@Tag(
    name = "Autenticação",
    description = "Cadastro de usuários e autenticação no sistema"
)
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Cadastrar usuário",
        description = "Cria uma nova conta e retorna o token JWT."
    )
    fun cadastrar(
        @Valid @RequestBody request: CadastroRequest
    ): AuthResponse {
        return authService.cadastrar(request)
    }

    @PostMapping("/signin")
    @Operation(
        summary = "Realizar login",
        description = "Autentica o usuário e retorna o token JWT."
    )
    fun login(
        @Valid @RequestBody request: LoginRequest
    ): AuthResponse {
        return authService.login(request)
    }
}