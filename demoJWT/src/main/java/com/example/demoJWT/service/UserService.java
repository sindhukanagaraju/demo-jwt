package com.example.demoJWT.service;

import com.example.demoJWT.entity.User;
import com.example.demoJWT.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(user.getName());

        return "fail";
    }
}
