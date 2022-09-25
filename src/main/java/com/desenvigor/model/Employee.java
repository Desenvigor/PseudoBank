package com.desenvigor.model;

import com.desenvigor.dao.ClientDAO;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Employees")
public class Employee extends Client{
    String credential;

    public Employee() {
    }

    public Employee(String name, String ssn, Date birthdate, String credential) {
        super(name, ssn, birthdate);
        this.credential = credential;
    }

    public void registerNewClient(Client client){
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insert(client);
    }

    public boolean authenticate(String credential){
        return this.credential.equals(credential);
    }

    @Override
    public String toString() {
        return name + " - " + credential;
    }
}
