package com.example.demoJWT.service;

import com.example.demoJWT.entity.User;
import com.example.demoJWT.entity.UserPrincipal;
import com.example.demoJWT.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = repo.findByName(name);

        if (user == null) {
            System.out.println("User Not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }
}
