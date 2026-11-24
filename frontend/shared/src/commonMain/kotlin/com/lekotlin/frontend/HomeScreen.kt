
package com.lekotlin.frontend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    aoClientes: () -> Unit,
    aoAgendamentos: () -> Unit,
    aoSair: () -> Unit
) {
    val nomeUsuario = SessaoUsuario.nome
        ?.takeIf { it.isNotBlank() }
        ?: "usuário"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Cabeçalho
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Limp Ecologic",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = LimpEcologicColors.Azul
            )

            TextButton(onClick = aoSair) {
                Text(
                    text = "Sair da conta",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        // Boas-vindas
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = LimpEcologicColors.Azul
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Bem-vindo(a)!",
                    style = MaterialTheme.typography.headlineSmall,
                    color = LimpEcologicColors.Branco
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = nomeUsuario,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = LimpEcologicColors.Branco
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Gerencie seus clientes e serviços em um só lugar.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LimpEcologicColors.Branco
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        // Menu principal
        Text(
            text = "Menu principal",
            style = MaterialTheme.typography.headlineSmall,
            color = LimpEcologicColors.Azul,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // Clientes
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Clientes",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = LimpEcologicColors.Azul
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Cadastre, consulte, pesquise, edite e exclua clientes."
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = aoClientes,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LimpEcologicColors.Azul,
                        contentColor = LimpEcologicColors.Branco
                    )
                ) {
                    Text("Gerenciar clientes")
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Agendamentos
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Agendamentos",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = LimpEcologicColors.Azul
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Organize os serviços, datas, horários, valores e clientes."
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = aoAgendamentos,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LimpEcologicColors.Verde,
                        contentColor = LimpEcologicColors.Texto
                    )
                ) {
                    Text("Gerenciar agendamentos")
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        HorizontalDivider()

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Limp Ecologic",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = LimpEcologicColors.Azul
        )

        Text(
            text = "Sistema de gerenciamento de serviços",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
