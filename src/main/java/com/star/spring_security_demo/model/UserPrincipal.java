package com.star.spring_security_demo.model;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// 15 UserPrincipal is the class which implements the UserDetails and principal mean current user
// 16 And in this particular class we have multiple methods three methods for one for the authority, one for username, one for password.
// 17. Apart from this, others are for your expiry, for your account and for your credentials.
// 18. Basically you have your table and your class which represents that, which is your user class(go to User.java) continues...
public class UserPrincipal implements UserDetails {

    //Whatever data we are getting from userdetails in MyUserDetailsService, we are assigning that here, so we can use that in other methods.
    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
