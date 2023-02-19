package com.nbr.trp.user.service;

import com.nbr.trp.user.entity.Role;
import com.nbr.trp.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public User saveUser(User user);

    public List<User> getAllUsers();

    public Optional<User> getUserByUsername(String username);

    public List<Role> getRoles();
}
