package com.easybit.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@OpenAPIDefinition(
        info =
          @Info(
             title = "Accounts MicroService Rest APi",
             description = "Eazy Bank Microservices Document",
             version = "V1",
             contact =
             @Contact(
                name = "Afroz Khan",
                email = "pathanafrozkhan786@gmail.com",
                url = "www.afrozkhan.com"),
                license = @License(name = "Apache3.0",
                url = "www.afrozkhan.com")
        ),
        externalDocs =
              @ExternalDocumentation(
                   description = "Eazy Bank accounts Microservices",
                   url = "www.afrozkhan.com"
              )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
