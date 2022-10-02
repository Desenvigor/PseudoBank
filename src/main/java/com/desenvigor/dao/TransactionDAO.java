package com.desenvigor.dao;

import com.desenvigor.model.Account;
import com.desenvigor.model.Transaction;
import com.desenvigor.vo.TransactionsReportVO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TransactionDAO {

    EntityManager em;

    public TransactionDAO(){
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

    public List<TransactionsReportVO> reportTransfer(){
        String jpql = "SELECT new com.desenvigor.vo.TransactionsReportVO(" +
                "transaction.time, " +
                "transaction.value, " +
                "client.name, " +
                "account.number) " +
                "FROM Transaction transaction " +
                "JOIN transaction.account account " +
                "JOIN account.client client";
        return em.createQuery(jpql, TransactionsReportVO.class).getResultList();
    }

    @Override
    protected void finalize() throws Throwable {
        em.close();
        super.finalize();
    }
}
