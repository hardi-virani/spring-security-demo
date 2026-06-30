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

//18. which is an entity which has three columns.
//19. Now, depending upon your number of columns in the table, this will change. So if you have three columns you have three properties. If you have five columns, you will have five properties here which you want to map.
public class User {

    @Id
    private int id ;
    private String username;
    private String password;



}
