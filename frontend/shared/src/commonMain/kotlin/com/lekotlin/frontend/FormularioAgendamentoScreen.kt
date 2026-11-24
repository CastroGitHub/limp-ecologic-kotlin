package com.lekotlin.frontend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormularioAgendamentoScreen(
    agendamento: AgendamentoResponse? = null,
    aoVoltar: () -> Unit,
    aoSalvar: () -> Unit,
    viewModel: AgendamentoViewModel = remember { AgendamentoViewModel() }
) {
    var nomeCliente by remember(agendamento) {
        mutableStateOf(agendamento?.nomeCliente ?: "")
    }

    var endereco by remember(agendamento) {
        mutableStateOf(agendamento?.endereco ?: "")
    }

    var data by remember(agendamento) {
        mutableStateOf(agendamento?.data ?: "")
    }

    var hora by remember(agendamento) {
        mutableStateOf(agendamento?.hora?.take(5) ?: "")
    }

    var status by remember(agendamento) {
        mutableStateOf(agendamento?.status ?: "PENDENTE")
    }

    var descricao by remember(agendamento) {
        mutableStateOf(agendamento?.descricao ?: "")
    }

    var valor by remember(agendamento) {
        mutableStateOf(
            agendamento?.valor?.toString()?.replace(".", ",") ?: ""
        )
    }

    var erroFormulario by remember {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(viewModel.mensagem) {
        if (viewModel.mensagem != null) {
            aoSalvar()
        }
    }

    val valorConvertido = valor.trim()
        .replace(",", ".")
        .toDoubleOrNull()

    val dataValida = runCatching {
        kotlinx.datetime.LocalDate.parse(data.trim())
    }.isSuccess

    val horaValida = hora.trim().matches(
        Regex("""([01]\d|2[0-3]):[0-5]\d""")
    )

    val statusValido = status.trim().uppercase() in
            listOf("PENDENTE", "AGENDADO", "REALIZADO")

    val formularioValido =
        nomeCliente.isNotBlank() &&
                endereco.isNotBlank() &&
                dataValida &&
                horaValida &&
                statusValido &&
                descricao.isNotBlank() &&
                valorConvertido != null &&
                valorConvertido >= 0.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = if (agendamento == null) {
                "Novo agendamento"
            } else {
                "Editar agendamento"
            },
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            value = nomeCliente,
            onValueChange = { nomeCliente = it },
            label = { Text("Nome do cliente") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = endereco,
            onValueChange = { endereco = it },
            label = { Text("Endereço") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = data,
            onValueChange = { data = it },
            label = { Text("Data") },
            placeholder = { Text("AAAA-MM-DD") },
            supportingText = {
                Text("Exemplo: 2026-11-24")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = hora,
            onValueChange = { hora = it },
            label = { Text("Horário") },
            placeholder = { Text("HH:MM") },
            supportingText = {
                Text("Exemplo: 14:30")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text(
            text = "Status",
            style = MaterialTheme.typography.titleMedium
        )

        Column {
            listOf("PENDENTE", "AGENDADO", "REALIZADO").forEach {
                Row {
                    RadioButton(
                        selected = status == it,
                        onClick = { status = it }
                    )
                    Text(
                        text = it,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
        }

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição do serviço") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = valor,
            onValueChange = { valor = it },
            label = { Text("Valor (R$)") },
            placeholder = { Text("Exemplo: 150,00") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        erroFormulario?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        viewModel.erro?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        if (viewModel.carregando) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    if (!formularioValido) {
                        erroFormulario =
                            "Confira os campos. Preencha todos corretamente."
                    } else {
                        erroFormulario = null

                        val dados = AgendamentoRequest(
                            nomeCliente = nomeCliente.trim(),
                            endereco = endereco.trim(),
                            data = data.trim(),
                            hora = "${hora.trim()}:00",
                            status = status.trim().uppercase(),
                            descricao = descricao.trim(),
                            valor = valorConvertido!!
                        )

                        if (agendamento == null) {
                            viewModel.cadastrarAgendamento(dados)
                        } else {
                            viewModel.atualizarAgendamento(
                                agendamento.id,
                                dados
                            )
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                enabled = !viewModel.carregando
            ) {
                Text("Gravar")
            }

            OutlinedButton(
                onClick = {
                    nomeCliente = ""
                    endereco = ""
                    data = ""
                    hora = ""
                    status = "PENDENTE"
                    descricao = ""
                    valor = ""
                    erroFormulario = null
                    viewModel.limparMensagens()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Limpar Campos")
            }
        }

        OutlinedButton(
            onClick = aoVoltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
