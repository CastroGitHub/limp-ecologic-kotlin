
package com.lekotlin.backend.service

import com.lekotlin.backend.dto.request.CadastroRequest
import com.lekotlin.backend.dto.request.LoginRequest
import com.lekotlin.backend.dto.response.AuthResponse
import com.lekotlin.backend.entity.Usuario
import com.lekotlin.backend.repository.UsuarioRepository
import com.lekotlin.backend.security.JwtService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val usuarioRepository: UsuarioRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    fun cadastrar(request: CadastroRequest): AuthResponse {
        if (usuarioRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("Este e-mail já está cadastrado")
        }

        val usuario = Usuario(
            nome = request.nome,
            email = request.email,
            senha = passwordEncoder.encode(request.senha)!!
        )

        val salvo = usuarioRepository.save(usuario)
        val token = jwtService.gerarToken(salvo.email)

        return AuthResponse(
            token = token,
            nome = salvo.nome,
            email = salvo.email
        )
    }

    fun login(request: LoginRequest): AuthResponse {
        val usuario = usuarioRepository.findByEmail(request.email)
            .orElseThrow {
                IllegalArgumentException("E-mail ou senha inválidos")
            }

        if (!passwordEncoder.matches(request.senha, usuario.senha)) {
            throw IllegalArgumentException("E-mail ou senha inválidos")
        }

        val token = jwtService.gerarToken(usuario.email)

        return AuthResponse(
            token = token,
            nome = usuario.nome,
            email = usuario.email
        )
    }
}
