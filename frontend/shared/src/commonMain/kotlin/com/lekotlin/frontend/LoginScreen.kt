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
fun LoginScreen(
    aoEntrar: () -> Unit,
    aoCadastrar: () -> Unit
) {
    val viewModel = remember { AuthViewModel() }

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.sucesso) {
        if (viewModel.sucesso) {
            aoEntrar()
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
            text = "Sistema de gerenciamento",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(40.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Entrar na conta",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                        autoCorrectEnabled = false
                    )
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
                        viewModel.login(email, senha)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = email.isNotBlank() &&
                            senha.isNotBlank() &&
                            !viewModel.carregando
                ) {
                    if (viewModel.carregando) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Entrar")
                    }
                }

                HorizontalDivider()

                Text(
                    text = "Ainda não tem uma conta?",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                TextButton(
                    onClick = aoCadastrar,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !viewModel.carregando
                ) {
                    Text("Criar uma conta")
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Limp Ecologic",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
