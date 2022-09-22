package com.desenvigor.dao;

import com.desenvigor.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientDAO {
    EntityManager em;

    public ClientDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pseudobank");
        this.em = emf.createEntityManager();
    }

    public void insert(Client client){
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }

    public Client find(Long id){
        return em.find(Client.class, id);
    }

    public List<Client> findAll(){
        String jpql = "SELECT c FROM Client c";
        return em.createQuery(jpql, Client.class).getResultList();
    }
}
