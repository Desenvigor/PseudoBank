package com.desenvigor.dao;

import com.desenvigor.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeDAO {
    EntityManager em;

    public EmployeeDAO(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pseudoBank");
        this.em = emf.createEntityManager();
    }

    public Employee findById(Long id){
        return em.find(Employee.class, id);
    }

    public Employee findByCredential(String credential){
        String jpql = "SELECT e FROM Employee e WHERE credential = " + credential;
        List<Employee> emp = em.createQuery(jpql, Employee.class).getResultList();
        return emp.get(0);
    }

    @Override
    protected void finalize() throws Throwable {
        em.close();
        super.finalize();
    }


}
