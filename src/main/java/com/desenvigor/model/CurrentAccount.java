package com.desenvigor.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("Current")
public class CurrentAccount extends Account{
    private Double tax;
    private final Double revenue = 0.5;


    public CurrentAccount() {
    }

    public CurrentAccount(Client client, String number, String agency, BigDecimal balance, Double tax) {
        super(client, number, agency, balance);
        this.tax = tax;
    }

    @Override
    public void withdraw() {
        BigDecimal tax1 = super.getBalance().subtract(new BigDecimal(tax));
        super.setBalance(tax1);
    }

    @Override
    public void deposit() {
        BigDecimal balanceWithRevenue = super.getBalance().multiply(new BigDecimal(revenue*2));
        super.setBalance(balanceWithRevenue);
    }

    @Override
    public String toString() {
        return client + "Current Account";
    }
}
