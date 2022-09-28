package com.desenvigor.dao;

import com.desenvigor.model.Account;
import com.desenvigor.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AccountDAO {
    private EntityManager em;

    public AccountDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pseudoBank");
        this.em = emf.createEntityManager();
    }

    public void insert(Account account){
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }

    public Account find(Long id){
        return em.find(Account.class, id);
    }

    public void update(Account account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }

    public void delete(Account account){
        em.getTransaction().begin();
        em.remove(account);
        em.getTransaction().commit();
    }

    public List<Client> findAll(){
        String jpql = "SELECT c FROM Client c";
        return em.createQuery(jpql, Client.class).getResultList();
    }

    @Override
    protected void finalize() throws Throwable {
        em.close();
        super.finalize();
    }


}
