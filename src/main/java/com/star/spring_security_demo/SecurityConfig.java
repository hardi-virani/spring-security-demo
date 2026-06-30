package com.star.spring_security_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private UserDetails User;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        just because we have written this securityfilterchain method the spring security defualt setting (which is asking username, password) goes away, because we are doing configuration by ourselves.

        http.csrf(customizer -> customizer.disable()); // this disables the csrf

        http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); //this means we are enableing askin username password for any request.
//        http.formLogin(Customizer.withDefaults()); //we dont need when you have stateless
        http.httpBasic(Customizer.withDefaults()); // This will give the popup instead of the sign in page.

        //making stateless, because it is making stateful by default, basically we want to get new session
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }

    //These is something i got from gpt, because one method called withdefaultPasswordEncoder is not working because that is removed in the new spring version.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user = User.builder()
//                .username("star")
//                .password(passwordEncoder().encode("123@123"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("1234@"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin  );
//
//    }



    @Autowired
    //5. the userdetailservie is an interface. and we have to implement it, and the way we have implemented it by creating, MyUserDetailService.java (to go MyUserDetailsService.java)
    private UserDetailsService userDetailsService;

    //1. what we were trying to achieve is, we were trying to authenticate users from the DB and the way to achieve that is, we need to change our authenticate provider.
    //2.  And we want to say that we want to connect to DB.


    @Bean
    public AuthenticationProvider authProvider() {

        //3. to connect with DB, we use DAO authentication provider, because DAO is your DB access
        //4. And then to achieve that we have to specify the userdetailservice.

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

//        provider.setUserDetailsPasswordService((UserDetailsPasswordService) userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;

    }


}
