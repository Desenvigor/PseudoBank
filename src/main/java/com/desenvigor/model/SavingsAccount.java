package com.desenvigor.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("Savings")
public class SavingsAccount extends Account{

    private final Double revenue = 0.2;

    public SavingsAccount() {
    }

    public SavingsAccount(Client client, String number, String agency, BigDecimal balance, Double tax) {
        super(client, number, agency, balance);
        super.setTax(tax);
    }


    @Override
    public void deposit(String value) {
        BigDecimal newBalanceAfterDeposit = super.getBalance().add(new BigDecimal(value));
        BigDecimal balanceWithRevenue = newBalanceAfterDeposit.multiply(new BigDecimal(revenue));
        super.setBalance(balanceWithRevenue);
        Transaction trans = new Transaction(this, Operation.WITHDRAW, balanceWithRevenue);
        super.setTransactions(trans);
    }

    @Override
    public String toString() {
        return super.getClient() + "Savings Account";
    }
}
