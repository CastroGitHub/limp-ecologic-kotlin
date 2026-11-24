
package com.lekotlin.frontend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClienteScreen(
    aoVoltar: () -> Unit,
    aoNovoCliente: () -> Unit = {},
    aoEditarCliente: (ClienteResponse) -> Unit = {},
    viewModel: ClienteViewModel = remember { ClienteViewModel() }
) {
    var busca by remember { mutableStateOf("") }
    var clienteExcluir by remember { mutableStateOf<ClienteResponse?>(null) }

    LaunchedEffect(Unit) {
        viewModel.listarClientes()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = aoVoltar) {
                Text("Voltar")
            }

            Text(
                text = "Clientes",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Button(
            onClick = aoNovoCliente,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Novo cliente")
        }

        OutlinedTextField(
            value = busca,
            onValueChange = {
                busca = it
                viewModel.buscarClientes(it)
            },
            label = { Text("Buscar cliente pelo nome") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = {
                    busca = ""
                    viewModel.listarClientes()
                }
            ) {
                Text("Limpar busca")
            }

            TextButton(
                onClick = { viewModel.listarClientes() }
            ) {
                Text("Atualizar lista")
            }
        }

        if (viewModel.carregando) {
            CircularProgressIndicator()
        }

        viewModel.erro?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        viewModel.mensagem?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (!viewModel.carregando && viewModel.clientes.isEmpty()) {
            Text("Nenhum cliente encontrado.")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = viewModel.clientes,
                key = { it.id }
            ) { cliente ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = cliente.nome,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text("Telefone: ${cliente.telefone}")
                        Text("E-mail: ${cliente.email}")
                        Text("Cidade: ${cliente.cidade}")
                        Text("Bairro: ${cliente.bairro}")
                        Text("Endereço: ${cliente.endereco}")
                        Text("Status: ${cliente.status}")
                        Text(
                            "Ativo: ${if (cliente.ativo) "Sim" else "Não"}"
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = {
                                    aoEditarCliente(cliente)
                                }
                            ) {
                                Text("Editar")
                            }

                            TextButton(
                                onClick = {
                                    clienteExcluir = cliente
                                }
                            ) {
                                Text(
                                    "Excluir",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    clienteExcluir?.let { cliente ->
        AlertDialog(
            onDismissRequest = {
                clienteExcluir = null
            },
            title = {
                Text("Excluir cliente")
            },
            text = {
                Text("Deseja realmente excluir ${cliente.nome}?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.excluirCliente(cliente.id)
                        clienteExcluir = null
                    }
                ) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        clienteExcluir = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}
