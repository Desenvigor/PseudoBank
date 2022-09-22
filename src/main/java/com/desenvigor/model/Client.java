package com.desenvigor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String ssn;
    Date birthdate;

    public Client() {
    }

    public Client(String name, String ssn, Date birthdate) {
        this.name = name;
        this.ssn = ssn;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return this.name + " - " + sdf.format(this.birthdate);
    }
}
