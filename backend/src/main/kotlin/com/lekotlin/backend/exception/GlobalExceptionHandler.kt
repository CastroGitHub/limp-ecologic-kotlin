
package com.lekotlin.backend.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.NoSuchElementException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun tratarNaoEncontrado(
        ex: NoSuchElementException
    ): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(mapOf("erro" to (ex.message ?: "Registro não encontrado")))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun tratarArgumentoInvalido(
        ex: IllegalArgumentException
    ): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .badRequest()
            .body(mapOf("erro" to (ex.message ?: "Dados inválidos")))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun tratarValidacao(
        ex: MethodArgumentNotValidException
    ): ResponseEntity<Map<String, String>> {
        val erros = ex.bindingResult.fieldErrors.map {
            "${it.field}: ${it.defaultMessage ?: "Valor inválido"}"
        }

        return ResponseEntity
            .badRequest()
            .body(mapOf("erro" to erros.joinToString("; ")))
    }
}
