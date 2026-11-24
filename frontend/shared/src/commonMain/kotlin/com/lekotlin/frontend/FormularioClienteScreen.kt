
package com.lekotlin.frontend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormularioClienteScreen(
    cliente: ClienteResponse? = null,
    aoVoltar: () -> Unit,
    aoSalvar: () -> Unit,
    viewModel: ClienteViewModel = remember { ClienteViewModel() }
) {
    var nome by remember(cliente) { mutableStateOf(cliente?.nome ?: "") }
    var telefone by remember(cliente) { mutableStateOf(cliente?.telefone ?: "") }
    var email by remember(cliente) { mutableStateOf(cliente?.email ?: "") }
    var cidade by remember(cliente) { mutableStateOf(cliente?.cidade ?: "") }
    var bairro by remember(cliente) { mutableStateOf(cliente?.bairro ?: "") }
    var endereco by remember(cliente) { mutableStateOf(cliente?.endereco ?: "") }
    var status by remember(cliente) { mutableStateOf(cliente?.status ?: "PENDENTE") }
    var ativo by remember(cliente) { mutableStateOf(cliente?.ativo ?: true) }

    var erroFormulario by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(viewModel.mensagem) {
        if (viewModel.mensagem != null) {
            aoSalvar()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = if (cliente == null) "Cadastrar cliente" else "Editar cliente",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefone,
            onValueChange = { telefone = it },
            label = { Text("Telefone") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cidade,
            onValueChange = { cidade = it },
            label = { Text("Cidade") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = bairro,
            onValueChange = { bairro = it },
            label = { Text("Bairro") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = endereco,
            onValueChange = { endereco = it },
            label = { Text("Endereço") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Status do cliente")

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("PENDENTE", "AGENDADO", "REALIZADO").forEach {
                FilterChip(
                    selected = status == it,
                    onClick = { status = it },
                    label = { Text(it) }
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Checkbox(
                checked = ativo,
                onCheckedChange = { ativo = it }
            )
            Text(
                text = "Cliente ativo",
                modifier = Modifier.padding(top = 12.dp)
            )
        }

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
            CircularProgressIndicator()
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    if (
                        nome.isBlank() ||
                        telefone.isBlank() ||
                        email.isBlank() ||
                        cidade.isBlank() ||
                        bairro.isBlank() ||
                        endereco.isBlank()
                    ) {
                        erroFormulario = "Preencha todos os campos."
                    } else if (!email.contains("@") || !email.contains(".")) {
                        erroFormulario = "Informe um e-mail válido."
                    } else {
                        erroFormulario = null

                        val dados = ClienteRequest(
                            nome = nome.trim(),
                            telefone = telefone.trim(),
                            email = email.trim(),
                            cidade = cidade.trim(),
                            bairro = bairro.trim(),
                            endereco = endereco.trim(),
                            status = status,
                            ativo = ativo
                        )

                        if (cliente == null) {
                            viewModel.cadastrarCliente(dados)
                        } else {
                            viewModel.atualizarCliente(cliente.id, dados)
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
                    nome = ""
                    telefone = ""
                    email = ""
                    cidade = ""
                    bairro = ""
                    endereco = ""
                    status = "PENDENTE"
                    ativo = true
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
