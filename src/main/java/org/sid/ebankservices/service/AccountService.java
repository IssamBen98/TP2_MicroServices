package org.sid.ebankservices.service;

import org.sid.ebankservices.dtos.AccountRequestDTO;
import org.sid.ebankservices.dtos.AccountResponseDTO;

import java.util.List;


public interface AccountService {
    public AccountResponseDTO AddAccount(AccountRequestDTO accountDTO);
    public List<AccountResponseDTO> getAccounts();
    public AccountResponseDTO getAccount(String id);
    public AccountResponseDTO update(AccountRequestDTO accountRequestDTO, String id);

    boolean deleteAccount(String id);
}
