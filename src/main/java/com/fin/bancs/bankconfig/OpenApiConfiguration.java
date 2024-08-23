package com.fin.bancs.bankconfig;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class OpenApiConfiguration {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("OMEGA Bank Microservices Rest API Documentation")
						.description("OMEGA Bank microservices REST API Documentation")
						.version("v1.0.0")
						.contact(new Contact()
								.name("Aashutosh kumar")
								.email("aashutoshkumar6729@gmail.com")
								.url("http://aashutosh.kumar.com"))
						.license(new License()
								.name("myLicence")
								.url("http://lic-company.com"))
					)
				.externalDocs(new ExternalDocumentation()
						.description("Find more About API documentatin")
						.url("https://springdoc.org/"));
						
	}
	
	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("api")
				.pathsToExclude("/transactions/**")
				.build();
	}
}
