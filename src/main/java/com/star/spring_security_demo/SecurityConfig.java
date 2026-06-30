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


//
//    @Autowired
//
//    private PasswordEncoder passwordEncoder;

//    @Bean
//    public AuthenticationProvider authProvider() {
//
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService); //This will basically connect to our DB.
//
//        provider.setuserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder);
//
//        return provider;
//
//    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

//        provider.setUserDetailsPasswordService((UserDetailsPasswordService) userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;

    }


}
