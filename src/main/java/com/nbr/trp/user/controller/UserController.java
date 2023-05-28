package com.nbr.trp.user.controller;

import com.nbr.trp.user.entity.Role;
import com.nbr.trp.user.entity.User;
import com.nbr.trp.user.repository.RoleRepository;
import com.nbr.trp.user.response.MessageResponse;
import com.nbr.trp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/users")
//@RequestMapping("/api/test")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;
    /*
    @GetMapping("/all")
    public MessageResponse allAccess() {
        return new MessageResponse("Public ");
    }

    @GetMapping("/employee")
    @PreAuthorize("hasRole('EMPLOYEE') ")
    public MessageResponse employeeAccess() {

        return new MessageResponse("Employee zone");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse adminAccess() {
        return new MessageResponse("Admin zone");
    }*/

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/roles")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllRoles() {
        try{
            List<Role> roles = userService.getRoles();
            return ResponseEntity.ok(roles);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        System.out.println(user.getRoles());

        try{
       /*     System.out.println("---------------------------------------------");
            System.out.println(user.getRoles());
            Set<Role> roletobeAdded = new HashSet<Role>();
            Role singlerole = roleRepository.findByName(String.valueOf(user.getRoles())).orElse(null);
            System.out.println(singlerole);
            roletobeAdded.add(singlerole);
            System.out.println(roletobeAdded);
            user.setRoles(roletobeAdded);*/
            User user1 = userService.saveUser(user);
            return ResponseEntity.ok(user1);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        try{
            List<User> all_users = userService.getAllUsers();
            return ResponseEntity.ok(all_users);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getAUser(@PathVariable String username) {
        System.out.println(username);
        try{
            Optional<User> user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pending-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllPendingUsers() {
        try{
            List<User> users = userService.getAllPendingUsers();
            return ResponseEntity.ok(users);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/approve/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveUser(@PathVariable String uuid) {

        try{
            User user = userService.approveRepuser(uuid);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/tinapprove/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveUserByTin(@PathVariable String username) {

        try{
            User user = userService.approveRepUserByTin(username);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/reject/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> rejectUser(@PathVariable String uuid) {

        try{
            User user = userService.rejectRepuser(uuid);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
