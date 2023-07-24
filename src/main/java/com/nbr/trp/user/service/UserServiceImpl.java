package com.nbr.trp.user.service;

import com.nbr.trp.user.entity.Role;
import com.nbr.trp.user.entity.User;
import com.nbr.trp.user.repository.RoleRepository;
import com.nbr.trp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public void UserServiceImpl( UserRepository userRepository){
        this.userRepository = userRepository;
    }

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
        return userRepository.findAllByOrderByAddedDateDesc();
    }

    @Override
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Autowired
    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    @Override
    public List<User> getAllPendingUsers() {
        return userRepository.findAllPending();
    }

    @Override
    public User approveRepuser(String id){
        User u = userRepository.findByUuid(id);
        u.setStatus("1");
        User user = userRepository.save(u);
        return user;
    }

    @Override
    public User rejectRepuser(String uname) {
        System.out.println("reched here");
        User u = userRepository.getByTin(uname);
        u.setStatus("-3");
        User user = userRepository.save(u);
        return user;
    }

    @Override
    public User blockRepuser(String uname) {
        User u = userRepository.getByTin(uname);
        u.setStatus("-2");
        User user = userRepository.save(u);
        return user;
    }

    @Override
    public User suspendRepuser(String uname) {
        User u = userRepository.getByTin(uname);
        u.setStatus("-1");
        User user = userRepository.save(u);
        return user;
    }

//    @Override
////    public User rejectRepUserByTin(String tin) {
////        User u = Optional.ofNullable(userRepository.findByUsername(tin)).orElse(null);
////        if(u!=null){
////            u.setStatus("-1");
////            User user = userRepository.save(u);
////            return user;
////        }
////
////    }

    @Override
    public User approveRepUserByTin(String tin){
        User u = userRepository.getByTin(tin);
        u.setStatus("1");
        User user = userRepository.save(u);
        return user;
    }

    public List<User> getAllBlockedUsers(){
        List<User> ls = userRepository.findByStatus("-2");
        return ls;
    }

    public List<User> getAllDeniedUsers(){
        List<User> ls = userRepository.findByStatus("-3");
        return ls;
    }



}
