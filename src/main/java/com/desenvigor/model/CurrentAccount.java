package com.desenvigor.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("Current")
public class CurrentAccount extends Account{
    private final Double revenue = 0.5;


    public CurrentAccount() {
    }

    public CurrentAccount(Client client, String number, String agency, BigDecimal balance, Double tax) {
        super(client, number, agency, balance);
        super.setTax(tax);
    }

    @Override
    public void deposit(String value) {
        BigDecimal newBalanceAfterDeposit = super.getBalance().add(new BigDecimal(value));
        BigDecimal balanceWithRevenue = super.getBalance().multiply(new BigDecimal(revenue*2));
        super.setBalance(balanceWithRevenue);
        Transaction trans = new Transaction(this, Operation.WITHDRAW, balanceWithRevenue);
        super.setTransactions(trans);
    }

    @Override
    public String toString() {
        return super.getClient() + " Current Account";
    }
}
