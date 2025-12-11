package com.spring.labs.lab6;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab6Application {
    static void main(String[] args) {
        SpringApplication.run(Lab6Application.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cinema API")
                        .version("1.0")
                        .description("API for managing cinema tickets and screenings")
                );
    }
}
