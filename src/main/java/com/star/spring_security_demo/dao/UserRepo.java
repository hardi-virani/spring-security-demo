package com.star.spring_security_demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.star.spring_security_demo.model.User;

public interface UserRepo extends JpaRepository <User, Integer> {

    User findByUsername(String username);


}
