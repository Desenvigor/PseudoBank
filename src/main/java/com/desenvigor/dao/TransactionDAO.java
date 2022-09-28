package com.desenvigor.dao;

import com.desenvigor.model.Account;
import com.desenvigor.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TransactionDAO {

    EntityManager em;

    TransactionDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pseudoBank");
        this.em = emf.createEntityManager();
    }

    public List<Transaction> findAll(){
        String jpql = "SELECT t FROM Transaction";
        return em.createQuery(jpql, Transaction.class).getResultList();
    }

    public List<Transaction> findAllAccountTransaction(Account account){
        String jpql = "SELECT t FROM Transaction where account_id = " + account.getId();
        return em.createQuery(jpql, Transaction.class).getResultList();
    }

    @Override
    protected void finalize() throws Throwable {
        em.close();
        super.finalize();
    }
}
