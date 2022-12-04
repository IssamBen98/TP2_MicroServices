package org.sid.ebankservices.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.sid.ebankservices.enums.AccountType;

import javax.persistence.*;
import java.util.*;

@Entity @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount
{
    @Id
    private String id;

    private Date createdAt;
    private double balance;
    private String currency ;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToOne
    private Customer customer;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCreated(Date date) {
        this.createdAt = date;
    }

    public void setBalance(Double b) {
        this.balance = b;
    }

    public void setCurrency(String curr) {
        this.currency = curr ;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
