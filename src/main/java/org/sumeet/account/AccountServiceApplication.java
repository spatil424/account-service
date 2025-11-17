package org.sumeet.account;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservices REST API Documentation",
                description = "Demo accounts microservices REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Sumeet Patil",
                        email = "xyz@gmail.com",
                        url = "https://github.com/spatil424"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Demo accounts microservices REST API Documentation",
                url = "https://github.com/spatil424"
        )
)
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

}
