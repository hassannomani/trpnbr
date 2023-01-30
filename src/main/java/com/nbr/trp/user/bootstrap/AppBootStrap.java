package com.nbr.trp.user.bootstrap;

import com.nbr.trp.user.entity.User;
import com.nbr.trp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

public class AppBootStrap implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserService userService;


    @Autowired
    public AppBootStrap(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> user1 = userRepository.findByUsername("admin");
        if (user1.isEmpty()){
            User userX = new User(UUID.randomUUID().toString(), "user", "1234", "ROLE_USER", "111111111111" ,"user@user.com","1","1", Date.valueOf("1970-01-01"));
            User usern = userService.saveUser(userX);
            activationService.saveActivationBootstrap(usern);

        }

//        Optional<User> user2 = Optional.ofNullable(userRepository.findByUsername("admin"));
//        if (user2.isEmpty()){
//            User userY = new User(UUID.randomUUID().toString(), "admin", "admin", "ROLE_ADMIN", "000000000000" ,"admin@nbr.gov.bd","1","1", Date.valueOf("1970-01-01"));
//            User usera = userService.saveUser(userY);
//            activationService.saveActivationBootstrap(usera);
//
//        }
    }
}
