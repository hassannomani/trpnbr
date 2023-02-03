package com.nbr.trp.common.service;

import com.nbr.trp.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    public User saveUser(User user);

    public List<User> getAllUsers();

    public Optional<User> getUserByUsername(String username);
}
