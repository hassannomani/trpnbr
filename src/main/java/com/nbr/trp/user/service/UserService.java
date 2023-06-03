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

    public List<User> getAllPendingUsers();

    public User approveRepuser(String uuid);

    public User approveRepUserByTin(String tin);

    public User rejectRepuser(String tin);

    public User blockRepuser(String tin);

    public User suspendRepuser(String tin);

    public List<User> getAllBlockedUsers();

    public List<User> getAllDeniedUsers();

//    public User rejectRepUserByTin(String tin);

}
