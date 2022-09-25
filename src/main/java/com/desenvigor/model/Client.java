package com.desenvigor.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Clients")
@MappedSuperclass
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

    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

}
