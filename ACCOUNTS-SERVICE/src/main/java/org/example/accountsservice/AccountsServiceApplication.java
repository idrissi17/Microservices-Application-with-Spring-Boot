package org.example.accountsservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.example.accountsservice.dto.AccountsContactInfoDto;
import org.example.accountsservice.dto.CustomerDto;
import org.example.accountsservice.service.IAccountsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@EnableJdbcAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice REST API Documentation",
                description = "Accounts Microservice REST API Documentation for Accounts Microservice",
                version = "1.0.0"

        )
)
public class AccountsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IAccountsService accountsService) {
        return args -> {

            CustomerDto customerDto1 = new CustomerDto();
            customerDto1.setFullName("John Doe");
            customerDto1.setPhoneNumber("1234767890");
            customerDto1.setAddress("123 Main Street");
            customerDto1.setEmail("john.doe@company.com");
            accountsService.createAccount(customerDto1);

            CustomerDto customerDto2 = new CustomerDto();
            customerDto2.setFullName("Jane Doe");
            customerDto2.setPhoneNumber("9876543210");
            customerDto2.setAddress("456 Elm Street");
            customerDto2.setEmail("jane.doe@company.com");
            accountsService.createAccount(customerDto2);

            CustomerDto customerDto3 = new CustomerDto();
            customerDto3.setFullName("Bob Smith");
            customerDto3.setPhoneNumber("1112223333");
            customerDto3.setAddress("789 Oak Street");
            customerDto3.setEmail("bob.smith@company.com");
            accountsService.createAccount(customerDto3);

            CustomerDto customerDto4 = new CustomerDto();
            customerDto4.setFullName("Alice Johnson");
            customerDto4.setPhoneNumber("5556667777");
            customerDto4.setAddress("901 Pine Street");
            customerDto4.setEmail("alice.johnson@company.com");
            accountsService.createAccount(customerDto4);

            CustomerDto customerDto5 = new CustomerDto();
            customerDto5.setFullName("Michael Lee");
            customerDto5.setPhoneNumber("7778889999");
            customerDto5.setAddress("1234 Maple Street");
            customerDto5.setEmail("michael.lee@company.com");
            accountsService.createAccount(customerDto5);

            List<CustomerDto> customers = List.of(customerDto1, customerDto2,
                    customerDto3, customerDto4, customerDto5);


        };
    }

}
