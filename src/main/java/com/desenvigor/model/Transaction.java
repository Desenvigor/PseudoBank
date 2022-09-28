package com.desenvigor.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private LocalDate time;
    private BigDecimal value;
    private Operation operation;

    public Account getAccount() {
        return account;
    }

    public Transaction() {
    }

    public Transaction(Account account, Operation operation, BigDecimal value){
        this.account = account;
        this.operation = operation;
        this.value = value;
        time = LocalDate.now();
    }

    @Override
    public String toString() {
        return operation.toString() + ": $" + value.toString() + " at " + time.toString();
    }
}
