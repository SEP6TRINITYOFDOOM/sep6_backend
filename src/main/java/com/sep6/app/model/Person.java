package com.sep6.app.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity(name = "people")
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Nullable
    private Integer birth;

    public Person(){

    }

    public Person(String name, int birth){
        this.name = name;
        this.birth = birth;
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Integer getBirth() {
        return birth;
    }
}
