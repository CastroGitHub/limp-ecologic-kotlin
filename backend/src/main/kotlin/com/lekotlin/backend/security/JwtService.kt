
package com.lekotlin.backend.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService(
    @Value("\${jwt.secret}") private val secret: String
) {

    private val chave: SecretKey by lazy {
        Keys.hmacShaKeyFor(
            java.util.Base64.getDecoder().decode(secret)
        )
    }

    fun gerarToken(email: String): String {
        val agora = Date()
        val expiracao = Date(agora.time + 86400000)

        return Jwts.builder()
            .subject(email)
            .issuedAt(agora)
            .expiration(expiracao)
            .signWith(chave)
            .compact()
    }

    fun extrairEmail(token: String): String {
        return Jwts.parser()
            .verifyWith(chave)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
    }

    fun tokenValido(token: String): Boolean {
        return try {
            extrairEmail(token).isNotBlank()
        } catch (e: Exception) {
            false
        }
    }
}
