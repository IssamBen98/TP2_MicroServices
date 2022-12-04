package org.sid.ebankservices;

import org.sid.ebankservices.entities.BankAccount;
import org.sid.ebankservices.entities.Customer;
import org.sid.ebankservices.enums.AccountType;
import org.sid.ebankservices.repositories.BankAccountRepository;
import org.sid.ebankservices.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankServicesApplication {

    public static void main(String[] args) {

        SpringApplication.run(EbankServicesApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        return args -> {
            Stream.of("Mohamed", "Amina", "Fatima")
                    .forEach(c -> {
                        Customer customer = new Customer();
                        customer.setName(c);
                        customerRepository.save(customer);
                    });
            customerRepository.findAll().forEach(c -> {
                for (int i = 0; i < 10; i++) {
                    // 3 methodes for creating an account (including builder)
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                            .createdAt(new Date())
                            .balance(12500 + Math.random() * 111198)
                            .currency("USD")
                            .build();
                    bankAccountRepository.save(bankAccount);
                }
            });
        };

    }
}
