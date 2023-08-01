package com.e_commerce_project.commerce.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public  UUID id;
    @Column(name = "name")
    public  String name;
    @Column(name = "password")
    public  String password;

    public Person(@JsonProperty UUID id,
                  @JsonProperty String name,
                  @JsonProperty String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Person() {

    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword(){return password;}

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

