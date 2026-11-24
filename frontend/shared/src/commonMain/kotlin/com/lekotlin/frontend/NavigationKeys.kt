
package com.lekotlin.frontend

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable
sealed interface NavigationKey : NavKey

@Serializable
data object LoginKey : NavigationKey

@Serializable
data object CadastroKey : NavigationKey

@Serializable
data object HomeKey : NavigationKey

@Serializable
data object ClientesKey : NavigationKey

@Serializable
data object FormularioClienteKey : NavigationKey

@Serializable
data object AgendamentosKey : NavigationKey

@Serializable
data object FormularioAgendamentoKey : NavigationKey

val navigationSavedState = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(LoginKey::class, LoginKey.serializer())
            subclass(CadastroKey::class, CadastroKey.serializer())
            subclass(HomeKey::class, HomeKey.serializer())
            subclass(ClientesKey::class, ClientesKey.serializer())
            subclass(FormularioClienteKey::class, FormularioClienteKey.serializer())
            subclass(AgendamentosKey::class, AgendamentosKey.serializer())
            subclass(
                FormularioAgendamentoKey::class,
                FormularioAgendamentoKey.serializer()
            )
        }
    }
}
