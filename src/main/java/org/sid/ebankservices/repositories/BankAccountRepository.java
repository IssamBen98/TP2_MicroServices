package org.sid.ebankservices.repositories;

import org.sid.ebankservices.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
