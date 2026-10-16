
package com.lekotlin.backend.service

import com.lekotlin.backend.entity.Cliente
import com.lekotlin.backend.repository.ClienteRepository
import org.springframework.stereotype.Service

@Service
class ClienteService(
    private val clienteRepository: ClienteRepository
) {

    fun listarTodos(): List<Cliente> {
        return clienteRepository.findAll()
    }

    fun buscarPorId(id: Long): Cliente {
        return clienteRepository.findById(id)
            .orElseThrow { NoSuchElementException("Cliente não encontrado") }
    }

    fun cadastrar(cliente: Cliente): Cliente {
        return clienteRepository.save(cliente)
    }

    fun atualizar(id: Long, dados: Cliente): Cliente {
        val cliente = buscarPorId(id)

        cliente.nome = dados.nome
        cliente.telefone = dados.telefone
        cliente.email = dados.email
        cliente.cidade = dados.cidade
        cliente.bairro = dados.bairro
        cliente.endereco = dados.endereco
        cliente.ativo = dados.ativo
        cliente.status = dados.status

        return clienteRepository.save(cliente)
    }

    fun excluir(id: Long) {
        val cliente = buscarPorId(id)
        clienteRepository.delete(cliente)
    }

    fun buscarPorNome(nome: String): List<Cliente> {
        return clienteRepository.buscarPorNome(nome)
    }
}
