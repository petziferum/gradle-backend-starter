package com.petziferum.gradlebackend.configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    // ...

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bussler's Pro Shop")
                        .version("V0.0.01")
                        .description("Give me your Money and take my Shit!")
                        .contact(new Contact().name("Petzi").email("daboarderpjb@gmail.com"))
                );
    }
}
