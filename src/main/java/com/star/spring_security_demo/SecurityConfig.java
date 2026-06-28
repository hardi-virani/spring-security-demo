package com.star.spring_security_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        just because we have written this securityfilterchai method the spring security defualt setting (which is asking username, password) goes away, because we are doing configuration by ourselves.

        http.csrf(customizer -> customizer.disable()); // this disables the csrf

        http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); //this means we are enableing askin username password for any request.
//        http.formLogin(Customizer.withDefaults()); //we dont need when you have stateless
        http.httpBasic(Customizer.withDefaults());

        //making stateless, because it is making stateful by default, basically we want to get new session
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }

}
