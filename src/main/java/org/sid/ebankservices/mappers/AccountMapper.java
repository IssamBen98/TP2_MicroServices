package org.sid.ebankservices.mappers;

import org.sid.ebankservices.entities.BankAccount;
import org.sid.ebankservices.dtos.AccountRequestDTO;
import org.sid.ebankservices.dtos.AccountResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class AccountMapper {
    public AccountResponseDTO fromAccount(BankAccount account)
    {
        AccountResponseDTO responseDTO = new AccountResponseDTO();
        BeanUtils.copyProperties(account, responseDTO);
        return responseDTO;
    }

    public BankAccount fromAccountDTO(AccountRequestDTO accountResponseDTO)
    {
        BankAccount account = new BankAccount();
        account.setId(UUID.randomUUID().toString());
        account.setCreated(new Date());
        BeanUtils.copyProperties(accountResponseDTO, account);
        return account;
    }
}
