package com.example.backend_uppgift.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new ConcreteUserDetails(user);
    }
}
