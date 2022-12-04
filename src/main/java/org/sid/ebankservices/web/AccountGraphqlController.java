package org.sid.ebankservices.web;

import org.sid.ebankservices.entities.BankAccount;
import org.sid.ebankservices.dtos.AccountRequestDTO;
import org.sid.ebankservices.dtos.AccountResponseDTO;
import org.sid.ebankservices.repositories.BankAccountRepository;
import org.sid.ebankservices.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
@AllArgsConstructor
// 3rd part : 43 : 00
public class AccountGraphqlController {
    @Autowired
    private BankAccountRepository bar;
    private AccountService as;


    @QueryMapping
    public List<BankAccount> accountsList()
    {
        return bar.findAll();
    }

    @QueryMapping
    public BankAccount accountsById(@Argument String id)
    {
        return bar.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account \' %s \' not Found", id)));
    }
    @MutationMapping
    public AccountResponseDTO addAccount(@Argument AccountRequestDTO account)
    {
        return as.AddAccount(account);
    }

    @MutationMapping
    public AccountResponseDTO updateAccount(@Argument String id, @Argument AccountRequestDTO account)
    {
        return as.update(account, id);
    }

    @MutationMapping
    public boolean deleteAcoount( @Argument String id)
    {
        return as.deleteAccount(id);
    }
}
