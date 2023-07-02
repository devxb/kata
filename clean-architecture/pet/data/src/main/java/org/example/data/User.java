package org.example.data;

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
    @Column(name = "USER_ID")
    private Integer id;

    @Column(name = "USER_NAME")
    private String name;

    User(){}

    public User(String name){
        this.name = name;
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
