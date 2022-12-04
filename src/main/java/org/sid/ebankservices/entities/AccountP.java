package org.sid.ebankservices.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class, name = "p1")
public interface AccountP {
    public String getId();
    public String getCurrency();
    public String getType();
}
