package com.star.spring_security_demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users") //Map the original "users" table, we created manually in pgAdmin.
public class User {

    @Id
    private int id ;
    private String username;
    private String password;



}
