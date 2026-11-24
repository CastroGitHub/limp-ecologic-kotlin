
package com.lekotlin.frontend

import androidx.compose.runtime.*
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(
        navigationSavedState,
        LoginKey
    )

    val clienteViewModel = remember {
        ClienteViewModel()
    }

    val agendamentoViewModel = remember {
        AgendamentoViewModel()
    }

    var clienteSelecionado by remember {
        mutableStateOf<ClienteResponse?>(null)
    }

    var agendamentoSelecionado by remember {
        mutableStateOf<AgendamentoResponse?>(null)
    }

    fun voltar() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
    }

    fun irParaInicio() {
        backStack.clear()
        backStack.add(HomeKey)
    }

    NavDisplay(
        backStack = backStack,
        onBack = { voltar() },
        entryProvider = entryProvider {

            entry<LoginKey> {
                LoginScreen(
                    aoEntrar = { irParaInicio() },
                    aoCadastrar = {
                        backStack.add(CadastroKey)
                    }
                )
            }

            entry<CadastroKey> {
                CadastroScreen(
                    aoCadastrar = { irParaInicio() },
                    aoVoltar = { voltar() }
                )
            }

            entry<HomeKey> {
                HomeScreen(
                    aoClientes = {
                        backStack.add(ClientesKey)
                    },
                    aoAgendamentos = {
                        backStack.add(AgendamentosKey)
                    },
                    aoSair = {
                        SessaoUsuario.encerrarSessao()
                        backStack.clear()
                        backStack.add(LoginKey)
                    }
                )
            }

            entry<ClientesKey> {
                ClienteScreen(
                    aoVoltar = { voltar() },
                    aoNovoCliente = {
                        clienteSelecionado = null
                        backStack.add(FormularioClienteKey)
                    },
                    aoEditarCliente = { cliente ->
                        clienteSelecionado = cliente
                        backStack.add(FormularioClienteKey)
                    },
                    viewModel = clienteViewModel
                )
            }

            entry<FormularioClienteKey> {
                FormularioClienteScreen(
                    cliente = clienteSelecionado,
                    aoVoltar = { voltar() },
                    aoSalvar = {
                        clienteSelecionado = null
                        voltar()
                    },
                    viewModel = clienteViewModel
                )
            }

            entry<AgendamentosKey> {
                AgendamentoScreen(
                    aoVoltar = { voltar() },
                    aoNovoAgendamento = {
                        agendamentoSelecionado = null
                        backStack.add(FormularioAgendamentoKey)
                    },
                    aoEditarAgendamento = { agendamento ->
                        agendamentoSelecionado = agendamento
                        backStack.add(FormularioAgendamentoKey)
                    },
                    viewModel = agendamentoViewModel
                )
            }

            entry<FormularioAgendamentoKey> {
                FormularioAgendamentoScreen(
                    agendamento = agendamentoSelecionado,
                    aoVoltar = { voltar() },
                    aoSalvar = {
                        agendamentoSelecionado = null
                        voltar()
                    },
                    viewModel = agendamentoViewModel
                )
            }
        }
    )
}
