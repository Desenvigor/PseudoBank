package com.desenvigor.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class  Account {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @OneToOne
    @JoinColumn(name = "client_id")
    Client client;
    String number;
    String agency;
    BigDecimal balance;

    public Account() {
    }

    public Account(Client client, String number, String agency, BigDecimal balance) {
        this.client = client;
        this.number = number;
        this.agency = agency;
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public abstract void withdraw();

    public abstract void deposit();

    @Override
    public String toString() {
        return client + "Normal Account";
    }
}
