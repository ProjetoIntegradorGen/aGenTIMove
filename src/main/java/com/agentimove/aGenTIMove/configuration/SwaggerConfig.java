package com.agentimove.aGenTIMove.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
    @Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Projeto Integrador aGenTIMove")
						.description("Projeto aGenTIMove - Generation Brasil")
						.version("v0.0.1")
						.license(new License()
								.name("Generation Brasil")
								.url("https://brazil.generation.org/"))
						.contact(new Contact()
								.name("GitHub aGenTIMove")
								.url("https://github.com/ProjetoIntegradorGen/aGenTIMove")
								.email("igorlimagn@gmail.com")))
				.externalDocs(new ExternalDocumentation()
						.description("Projeto Github")
						.url("https://github.com/ProjetoIntegradorGen/aGenTIMove"));
	}
}
