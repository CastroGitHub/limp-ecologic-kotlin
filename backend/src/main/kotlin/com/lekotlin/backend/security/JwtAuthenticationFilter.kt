
package com.lekotlin.backend.security

import com.lekotlin.backend.repository.UsuarioRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val usuarioRepository: UsuarioRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val cabecalho = request.getHeader("Authorization")

        if (cabecalho == null || !cabecalho.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = cabecalho.substring(7)

        try {
            if (jwtService.tokenValido(token)) {
                val email = jwtService.extrairEmail(token)
                val usuario = usuarioRepository.findByEmail(email).orElse(null)

                if (usuario != null &&
                    SecurityContextHolder.getContext().authentication == null
                ) {
                    val autenticacao = UsernamePasswordAuthenticationToken(
                        usuario.email,
                        null,
                        emptyList()
                    )

                    autenticacao.details =
                        WebAuthenticationDetailsSource().buildDetails(request)

                    SecurityContextHolder.getContext().authentication = autenticacao
                }
            }
        } catch (e: Exception) {
            SecurityContextHolder.clearContext()
        }

        filterChain.doFilter(request, response)
    }
}
