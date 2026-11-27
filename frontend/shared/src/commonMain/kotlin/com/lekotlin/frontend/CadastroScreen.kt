package com.lekotlin.frontend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CadastroScreen(
    aoCadastrar: () -> Unit,
    aoVoltar: () -> Unit
) {
    val viewModel = remember { AuthViewModel() }

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }

    val emailValido = email.trim().matches(
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    )

    val formularioValido =
        nome.isNotBlank() &&
                emailValido &&
                senha.length >= 8 &&
                senha == confirmarSenha

    LaunchedEffect(viewModel.sucesso) {
        if (viewModel.sucesso) {
            aoCadastrar()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Limp Ecologic",
            style = MaterialTheme.typography.headlineLarge,
            color = LimpEcologicColors.Azul
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Crie sua conta",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Cadastro",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = nome,
                    onValueChange = {
                        nome = it
                        viewModel.limparMensagens()
                    },
                    label = { Text("Nome completo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = !viewModel.carregando,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        viewModel.limparMensagens()
                    },
                    label = { Text("E-mail") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = !viewModel.carregando,
                    isError = email.isNotBlank() && !emailValido,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                        autoCorrectEnabled = false
                    ),
                    supportingText = {
                        if (email.isNotBlank() && !emailValido) {
                            Text(
                                "Informe um e-mail válido.",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = senha,
                    onValueChange = {
                        senha = it
                        viewModel.limparMensagens()
                    },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = !viewModel.carregando,
                    visualTransformation = if (mostrarSenha) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                        autoCorrectEnabled = false
                    ),
                    supportingText = {
                        Text("A senha deve possuir pelo menos 8 caracteres.")
                    }
                )

                OutlinedTextField(
                    value = confirmarSenha,
                    onValueChange = {
                        confirmarSenha = it
                        viewModel.limparMensagens()
                    },
                    label = { Text("Confirmar senha") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = !viewModel.carregando,
                    isError = confirmarSenha.isNotBlank() &&
                            senha != confirmarSenha,
                    visualTransformation = if (mostrarSenha) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                        autoCorrectEnabled = false
                    ),
                    trailingIcon = {
                        TextButton(
                            onClick = {
                                mostrarSenha = !mostrarSenha
                            }
                        ) {
                            Text(
                                if (mostrarSenha) "Ocultar" else "Mostrar"
                            )
                        }
                    },
                    supportingText = {
                        if (confirmarSenha.isNotBlank() &&
                            senha != confirmarSenha
                        ) {
                            Text(
                                "As senhas não coincidem.",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                viewModel.erro?.let { erro ->
                    Text(
                        text = erro,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Button(
                    onClick = {
                        viewModel.cadastrar(
                            nome = nome,
                            email = email,
                            senha = senha
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = formularioValido && !viewModel.carregando
                ) {
                    if (viewModel.carregando) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Criar conta")
                    }
                }

                HorizontalDivider()

                Text(
                    text = "Já possui uma conta?",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                TextButton(
                    onClick = aoVoltar,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !viewModel.carregando
                ) {
                    Text("Voltar para o login")
                }
            }
        }
    }
}
