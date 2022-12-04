package org.sid.ebankservices.service;

import org.sid.ebankservices.entities.BankAccount;
import org.sid.ebankservices.dtos.AccountRequestDTO;
import org.sid.ebankservices.dtos.AccountResponseDTO;
import org.sid.ebankservices.mappers.AccountMapper;
import org.sid.ebankservices.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private BankAccountRepository bar;
    private AccountMapper mapper;
    @Override
    public AccountResponseDTO AddAccount(AccountRequestDTO accountDTO) {
        BankAccount account = mapper.fromAccountDTO(accountDTO);
        BankAccount savedAccount = bar.save(account);
        AccountResponseDTO responseDTO = mapper.fromAccount(savedAccount);
        return responseDTO;
    }

    @Override
    public List<AccountResponseDTO> getAccounts() {
        List<BankAccount> accounts = bar.findAll();
        List<AccountResponseDTO> accountResponseDTOS = accounts.stream()
                .map(a ->
                        mapper.fromAccount(a)).collect(Collectors.toList());
        return accountResponseDTOS;
    }

    @Override
    public AccountResponseDTO getAccount(String id) {
        BankAccount account = bar.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s Not Found", id)));
        AccountResponseDTO responseDTO = mapper.fromAccount(account);
        return responseDTO;
    }

    @Override
    public AccountResponseDTO update(AccountRequestDTO accountRequestDTO, String id) {
        BankAccount acc = bar.findById(id).orElseThrow();
        acc.setId(id);
        if (accountRequestDTO.getBalance() != null) acc.setBalance(accountRequestDTO.getBalance());
        if (accountRequestDTO.getCurrency() != null) acc.setCurrency(accountRequestDTO.getCurrency());
        if (accountRequestDTO.getType() != null) acc.setType(accountRequestDTO.getType());
        BankAccount saved = bar.save(acc);
        return mapper.fromAccount(saved);

    }

    @Override
    public boolean deleteAccount(String id) {
        BankAccount account = bar.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account \' %s \' not found", id)));
        if (account == null)
            return false;
        bar.deleteById(id);
        return true;

    }

}
