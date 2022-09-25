package com.desenvigor.dao;

import com.desenvigor.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeDAO {
    EntityManager em;

    public EmployeeDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pseudoBank");
        this.em = emf.createEntityManager();
    }

    public Employee findById(Long id){
        return em.find(Employee.class, id);
    }




}
