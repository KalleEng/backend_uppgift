package com.example.backend_uppgift.security;

import com.example.backend_uppgift.repositories.RoleRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDataSeeder {
    UserRepo userRepo;
    RoleRepo roleRepo;

    public void seed(){
        if(roleRepo.findByName("Admin") == null){
            addRole("Admin");
        }
        if(roleRepo.findByName("Customer") == null){
            addRole("Customer");
        }
        if (userRepo.getUserByUsername("jakob.engwall@gmail.com") == null){
            addUser("jakob.engwall@gmail.com","Admin");
        }
        if (userRepo.getUserByUsername("karl.engwall@gmail.com") == null){
            addUser("karl.engwall@gmail.com","Customer");
        }

    }

    private void addUser(String mail, String group) {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleRepo.findByName(group));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("R*59a5yA");
        User user = User.builder().enabled(true).password(hash).username(mail).roles(roles).build();
        userRepo.save(user);
    }

    private void addRole(String name) {
        Role role = new Role();
        roleRepo.save(Role.builder().name(name).build());
    }


}
