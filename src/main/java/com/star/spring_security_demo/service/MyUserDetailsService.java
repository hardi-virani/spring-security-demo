package com.star.spring_security_demo.service;

import com.star.spring_security_demo.dao.UserRepo;
import com.star.spring_security_demo.model.User;
import com.star.spring_security_demo.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//6. MyUserDetailsService which implements UserDetailsService, in which we have only one method which is LoadUserByUsername
public class MyUserDetailsService  implements UserDetailsService {

    @Autowired
    //7. But since the data is coming frm DB, we also need a repo layer. (go to UserRepo)

    private UserRepo repo;


    @Override
//    14. And to achieve that we have to impleent the userDetails interface. And we have done that in UserPrincipal class (go to UserPrincipal)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //10. What we are doing is we are finding a method, or we are calling a method which returns you the object of User.
        User user = repo.findByUsername(username);

        //11. If user is not there, we have to throw an exception.
        if(user == null) {
            System.out.println("user 404");
            throw new UsernameNotFoundException("user 404");
        }

        //12. But question is, how will we return this User?
        //13. So we have to basically wrap this user object into a UserPrincipal for extra things like expiry and all those stuff.

        return new UserPrincipal(user);

    }
}
