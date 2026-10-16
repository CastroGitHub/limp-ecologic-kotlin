
package com.lekotlin.frontend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

private val Verde = Color(0xFF237A57)
private val VerdeClaro = Color(0xFFE8F5EE)
private val Fundo = Color(0xFFF5F7F5)

@Composable
@Preview
fun App() {
    MaterialTheme {
        var tela by remember { mutableStateOf("menu") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Fundo)
                .safeContentPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Verde)
                    .padding(20.dp)
            ) {
                Text(
                    text = "Limp Ecologic",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Gestão de higienização de estofados",
                    color = Color.White
                )
            }

            when (tela) {
                "clientes" -> FormularioClientes(
                    voltar = { tela = "menu" }
                )
                "agendamentos" -> FormularioAgendamentos(
                    voltar = { tela = "menu" }
                )
                else -> MenuPrincipal(
                    abrirClientes = { tela = "clientes" },
                    abrirAgendamentos = { tela = "agendamentos" }
                )
            }
        }
    }
}

@Composable
private fun MenuPrincipal(
    abrirClientes: () -> Unit,
    abrirAgendamentos: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "Bem-vindo!",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Verde
        )

        Text("Selecione o formulário que deseja abrir.")

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Cadastro de clientes",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text("Dados de contato e endereço dos clientes.")
                Button(
                    onClick = abrirClientes,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Verde
                    )
                ) {
                    Text("Abrir formulário")
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Cadastro de agendamentos",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text("Data, horário e detalhes do serviço.")
                Button(
                    onClick = abrirAgendamentos,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Verde
                    )
                ) {
                    Text("Abrir formulário")
                }
            }
        }
    }
}

@Composable
private fun FormularioClientes(voltar: () -> Unit) {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("PENDENTE") }
    var ativo by remember { mutableStateOf(true) }
    var mensagem by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextButton(onClick = voltar) {
            Text("← Voltar ao menu")
        }

        Text(
            "Cadastro de clientes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Verde
        )

        Campo("Nome completo", nome) { nome = it }
        Campo("Telefone", telefone) { telefone = it }
        Campo("E-mail", email) { email = it }
        Campo("Cidade", cidade) { cidade = it }
        Campo("Bairro", bairro) { bairro = it }
        Campo("Endereço completo", endereco) { endereco = it }

        SeletorStatus(
            status = status,
            alterarStatus = { status = it }
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = ativo,
                onCheckedChange = { ativo = it }
            )
            Text("Cliente ativo")
        }

        Button(
            onClick = {
                mensagem = if (
                    nome.isNotBlank() &&
                    telefone.isNotBlank() &&
                    email.isNotBlank() &&
                    cidade.isNotBlank() &&
                    bairro.isNotBlank() &&
                    endereco.isNotBlank()
                ) {
                    "Formulário preenchido com sucesso!"
                } else {
                    "Preencha todos os campos obrigatórios."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Verde
            )
        ) {
            Text("Cadastrar cliente")
        }

        if (mensagem.isNotBlank()) {
            Text(mensagem, color = Verde)
        }
    }
}

@Composable
private fun FormularioAgendamentos(voltar: () -> Unit) {
    var nomeCliente by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("PENDENTE") }
    var descricao by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextButton(onClick = voltar) {
            Text("← Voltar ao menu")
        }

        Text(
            "Cadastro de agendamentos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Verde
        )

        Campo("Nome do cliente", nomeCliente) { nomeCliente = it }
        Campo("Endereço do serviço", endereco) { endereco = it }
        Campo("Data (AAAA-MM-DD)", data) { data = it }
        Campo("Horário (HH:MM)", hora) { hora = it }

        SeletorStatus(
            status = status,
            alterarStatus = { status = it }
        )

        Campo("Descrição do serviço", descricao) { descricao = it }
        Campo("Valor (R$)", valor) { valor = it }

        Button(
            onClick = {
                mensagem = if (
                    nomeCliente.isNotBlank() &&
                    endereco.isNotBlank() &&
                    data.isNotBlank() &&
                    hora.isNotBlank() &&
                    descricao.isNotBlank() &&
                    valor.toDoubleOrNull() != null &&
                    valor.toDoubleOrNull()!! >= 0
                ) {
                    "Formulário preenchido com sucesso!"
                } else {
                    "Confira os campos e o valor informado."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Verde
            )
        ) {
            Text("Cadastrar agendamento")
        }

        if (mensagem.isNotBlank()) {
            Text(mensagem, color = Verde)
        }
    }
}

@Composable
private fun Campo(
    rotulo: String,
    valor: String,
    aoAlterar: (String) -> Unit
) {
    OutlinedTextField(
        value = valor,
        onValueChange = aoAlterar,
        label = { Text(rotulo) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
private fun SeletorStatus(
    status: String,
    alterarStatus: (String) -> Unit
) {
    var expandido by remember { mutableStateOf(false) }
    val opcoes = listOf("PENDENTE", "AGENDADO", "REALIZADO")

    Column {
        Text("Status", color = Verde)
        Box {
            OutlinedButton(
                onClick = { expandido = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(status)
            }

            DropdownMenu(
                expanded = expandido,
                onDismissRequest = { expandido = false }
            ) {
                opcoes.forEach { opcao ->
                    DropdownMenuItem(
                        text = { Text(opcao) },
                        onClick = {
                            alterarStatus(opcao)
                            expandido = false
                        }
                    )
                }
            }
        }
    }
}
