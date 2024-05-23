package com.example.backend_uppgift.security;

import com.example.backend_uppgift.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDataSeeder {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    public void seed(){
        if(roleRepo.findByName("Admin") == null){
            addRole("Admin");
        }
        if(roleRepo.findByName("Receptionist") == null){
            addRole("Receptionist");
        }
        if (userRepo.getUserByUsername("jakob.engwall@gmail.com") == null){
            addUser("jakob.engwall@gmail.com","Admin");
        }
        if (userRepo.getUserByUsername("karl.engwall@gmail.com") == null){
            addUser("karl.engwall@gmail.com","Receptionist");
        }

    }

    private void addUser(String mail, String group) {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleRepo.findByName(group));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("R*59a5yA");
        User user = User.builder().enabled(true).password(hash).username(mail).accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).roles(roles).build();
        userRepo.save(user);
    }

    private void addRole(String name) {
        roleRepo.save(Role.builder().name(name).build());
    }

}
