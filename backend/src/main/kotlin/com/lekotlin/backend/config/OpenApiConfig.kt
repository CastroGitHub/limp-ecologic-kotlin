package com.lekotlin.backend.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun limpEcologicOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Limp Ecologic API")
                    .description(
                        "API REST para gerenciamento de clientes e " +
                                "agendamentos da empresa Limp Ecologic."
                    )
                    .version("1.0.0")
                    .contact(
                        Contact()
                            .name("Equipe Limp Ecologic")
                    )
            )
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearerAuth",
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )
    }
}