package org.example.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "USER_NAME")
    private String name;

    public User(){
        System.out.println("sonarqube legacy test");
    }

    public User(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
