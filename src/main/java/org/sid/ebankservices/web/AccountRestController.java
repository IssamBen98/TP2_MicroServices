package org.sid.ebankservices.web;


import org.sid.ebankservices.*;
import org.sid.ebankservices.dtos.AccountRequestDTO;
import org.sid.ebankservices.dtos.AccountResponseDTO;
import org.sid.ebankservices.entities.BankAccount;
import org.sid.ebankservices.repositories.BankAccountRepository;
import org.sid.ebankservices.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountRestController {
   private BankAccountRepository bankAccountRepository ;
   private AccountService accountService;

   public AccountRestController(BankAccountRepository bankAccountRep)
   {
      this.bankAccountRepository = bankAccountRep;
   }

   @GetMapping("/bankAccounts")
   public List<BankAccount> bankAccounts() {
      return bankAccountRepository.findAll();
   }

   @GetMapping("/bankAccounts/{id}")
   public BankAccount bankAccounts(@PathVariable String id) {
      return bankAccountRepository.findById(id)
              .orElseThrow(()-> new RuntimeException(String.format("Account % not found !",id)));
   }
   @PostMapping("/accounts")
   public AccountResponseDTO save(@RequestBody AccountRequestDTO accountSTO)
   {
      return accountService.AddAccount(accountSTO);
   }
   @PutMapping("/accounts/{id}")
   public AccountResponseDTO update(@RequestBody AccountRequestDTO accountRequestDTO, @PathVariable String id)
   {
      return accountService.update(accountRequestDTO, id);
   }

   @DeleteMapping("/accounts/{id}")
   public boolean delete(@PathVariable String id)
   {
      bankAccountRepository.deleteById(id);
      BankAccount account = bankAccountRepository.findById(id).orElse(null);
      return account == null;
   }
}
