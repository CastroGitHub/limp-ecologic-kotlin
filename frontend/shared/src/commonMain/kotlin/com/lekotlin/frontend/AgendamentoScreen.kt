
package com.lekotlin.frontend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AgendamentoScreen(
    aoVoltar: () -> Unit,
    aoNovoAgendamento: () -> Unit = {},
    aoEditarAgendamento: (AgendamentoResponse) -> Unit = {},
    viewModel: AgendamentoViewModel = remember { AgendamentoViewModel() }
) {
    var busca by remember { mutableStateOf("") }
    var agendamentoExcluir by remember {
        mutableStateOf<AgendamentoResponse?>(null)
    }

    LaunchedEffect(Unit) {
        viewModel.listarAgendamentos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Agendamentos",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            TextButton(onClick = aoVoltar) {
                Text("Voltar")
            }
        }

        Button(
            onClick = aoNovoAgendamento,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = LimpEcologicColors.Verde,
                contentColor = LimpEcologicColors.Texto
            )
        ) {
            Text("Novo agendamento")
        }

        OutlinedTextField(
            value = busca,
            onValueChange = { busca = it },
            label = { Text("Pesquisar pelo nome do cliente") },
            placeholder = { Text("Digite o nome do cliente") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    viewModel.buscarAgendamentos(busca)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Buscar")
            }

            OutlinedButton(
                onClick = {
                    busca = ""
                    viewModel.listarAgendamentos()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Limpar pesquisa")
            }

            OutlinedButton(
                onClick = {
                    viewModel.listarAgendamentos()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Atualizar")
            }
        }

        if (viewModel.carregando) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        viewModel.erro?.let { erro ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = erro,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )

                    TextButton(
                        onClick = { viewModel.limparMensagens() }
                    ) {
                        Text("Fechar")
                    }
                }
            }
        }

        viewModel.mensagem?.let { mensagem ->
            Text(
                text = mensagem,
                color = LimpEcologicColors.Verde
            )
        }

        if (
            !viewModel.carregando &&
            viewModel.agendamentos.isEmpty() &&
            viewModel.erro == null
        ) {
            Text(
                text = if (busca.isNotBlank()) {
                    "Nenhum agendamento encontrado para \"$busca\"."
                } else {
                    "Nenhum agendamento cadastrado."
                },
                style = MaterialTheme.typography.bodyLarge
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = viewModel.agendamentos,
                key = { it.id }
            ) { agendamento ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = agendamento.nomeCliente,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text("Endereço: ${agendamento.endereco}")
                        Text("Data: ${agendamento.data}")
                        Text("Horário: ${agendamento.hora.take(5)}")
                        Text("Status: ${agendamento.status}")
                        Text("Descrição: ${agendamento.descricao}")

                        Text(
                            "Valor: R$ ${
                                agendamento.valor.toString().replace(".", ",")
                            }"
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = {
                                    aoEditarAgendamento(agendamento)
                                }
                            ) {
                                Text("Editar")
                            }

                            TextButton(
                                onClick = {
                                    agendamentoExcluir = agendamento
                                },
                                colors = ButtonDefaults.textButtonColors(
                                    contentColor = MaterialTheme.colorScheme.error
                                )
                            ) {
                                Text("Excluir")
                            }
                        }
                    }
                }
            }
        }
    }

    agendamentoExcluir?.let { agendamento ->
        AlertDialog(
            onDismissRequest = {
                agendamentoExcluir = null
            },
            title = {
                Text("Excluir agendamento")
            },
            text = {
                Text(
                    "Deseja realmente excluir o agendamento de ${agendamento.nomeCliente}?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.excluirAgendamento(agendamento.id)
                        agendamentoExcluir = null
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        agendamentoExcluir = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}
