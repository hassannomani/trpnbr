package com.nbr.trp.user.service;

import com.nbr.trp.user.entity.User;
import com.nbr.trp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        //String password = user.getPassword();
        this.passwordEncoder = new BCryptPasswordEncoder();
        String pass = this.passwordEncoder.encode(user.getPassword());
        //System.out.println("The pass is "+pass);
        user.setPassword(pass);
        User u = userRepository.save(user);
        return u;
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
