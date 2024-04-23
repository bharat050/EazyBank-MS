package com.microservice.accountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts MicroService for EazyBank",
				description = "CRUD rest APIs to handle basic operations",
				version = "1.0",
				contact = @Contact(
						name = "Bharat",
						email = "student@eazy.com",
						url = "nananam@jcis.com"),
				license = @License(
						name = "Spring CLoud",
						url = "springCloud.com")
				))
public class AccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}

}
