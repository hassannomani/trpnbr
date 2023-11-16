package com.nbr.trp.user.controller;

import com.nbr.trp.action.entity.Action;
import com.nbr.trp.action.service.ActionService;
import com.nbr.trp.common.service.CommonService;
import com.nbr.trp.log.LoggerController;
import com.nbr.trp.user.entity.ERole;
import com.nbr.trp.user.entity.Role;
import com.nbr.trp.user.entity.User;
import com.nbr.trp.user.repository.RoleRepository;
import com.nbr.trp.user.repository.UserRepository;
import com.nbr.trp.user.request.LoginRequest;
import com.nbr.trp.user.request.SignupRequest;
import com.nbr.trp.user.response.JwtResponse;
import com.nbr.trp.user.response.MessageResponse;
import com.nbr.trp.user.service.UserDetailsImpl;
import com.nbr.trp.user.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ActionService actionService;

    @Autowired
    CommonService commonService;

    @Autowired
    LoggerController loggerController;


    private static final Logger logger =  LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl)
                authentication.getPrincipal();
        //System.out.println("status is "+userDetails.getStatus());

        if(userDetails.getStatus().equals("1")){
            loggerController.Login(userDetails.getUuid());
            //logger.debug(userDetails.getUuid()+ " User is logging in");
            List<String> roles = userDetails.getAuthorities()
                    .stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(
                            jwt,
                            userDetails.getUuid(),
                            userDetails.getUsername(),
                            userDetails.getEmail(),
                            roles
                    )
            );
        }else if(userDetails.getStatus().equals("0")){
            //logger.info(userDetails.getUuid()+" Unapproved user trying to access");
            loggerController.LoginFailedUnApproved(userDetails.getUuid());

            return ResponseEntity.status(403).body("Approval Required");
        }else if(userDetails.getStatus().equals("-3")){
            loggerController.LoginFailedDeny(userDetails.getUuid());
            //logger.info("Denied user trying to access");
            //System.out.println("sending");
            Action action = actionService.getActionByTypeAndTin("DENY",userDetails.getUsername());
            return ResponseEntity.status(403).body(action);
        }
        else if(userDetails.getStatus().equals("-2")){
            Action action = actionService.getActionByTypeAndTin("BLOCK",userDetails.getUsername());
            //logger.info(userDetails.getUuid()+ " Blocked user trying to access");
            loggerController.LoginFailedBlocked(userDetails.getUuid());
            return ResponseEntity.status(403).body(action);
        }
        else if(userDetails.getStatus().equals("-1")){
            Action action = actionService.getActionByTypeAndTin("SUSPEND",userDetails.getUsername());
            long timeStart = action.getActionFrom().getTime();
            long timeEnd = action.getActionTo().getTime();
            long now = new Date().getTime();
            if(now>=timeStart&&now<timeEnd){
                //logger.info(userDetails.getUuid()+ " Suspended user trying to access");
                loggerController.LoginFailedSuspended(userDetails.getUuid());
                return ResponseEntity.status(403).body(action);
            }
            else if(now<timeStart){
                //logger.info(userDetails.getUuid()+ " User is loggin in");
                loggerController.Login(userDetails.getUuid());
                List<String> roles = userDetails.getAuthorities()
                        .stream().map(item -> item.getAuthority())
                        .collect(Collectors.toList());

                return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUuid(), userDetails.getUsername(),
                        userDetails.getEmail(), roles)
                );
            }
            else{
                User user = userRepository.getByTin(userDetails.getUsername());
                user.setStatus("1");
                User u = userRepository.save(user);
                loggerController.Login(userDetails.getUuid());
                List<String> roles = userDetails.getAuthorities()
                        .stream().map(item -> item.getAuthority())
                        .collect(Collectors.toList());

                return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUuid(), userDetails.getUsername(),
                        userDetails.getEmail(), roles)
                );
            }
        }else{
            loggerController.LoginError(userDetails.getUuid());

            return ResponseEntity.status(403).body("Error! Please try again");
        }


        //0 is for unapproved user
        //1 is for approved user
        //-1 is for suspended user
        //-2 for blocked user
        // -3 for denied user

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            loggerController.ReRegistration(signUpRequest.getUsername());
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            loggerController.ReRegistration(signUpRequest.getEmail());

            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new employee account
        User employee = new User(
                UUID.randomUUID().toString(),
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getEmail(),
                signUpRequest.getAddedby(),
                signUpRequest.getAddedfromip(),
                signUpRequest.getStatus(),
                null,
                ""
        );

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            logger.error(signUpRequest.getUsername()+ " is trying for blank role ");

            Role repRole = roleRepository.findByName(String.valueOf(ERole.ROLE_REPRESENTATIVE))
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(repRole);

        } else {

            strRoles.forEach(role -> {

                if (role.equals("admin")) {
                    loggerController.AdminRoleTry(signUpRequest.getUsername());
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN.name())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else if (role.equals("agent")){
                    Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT.name())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(agentRole);
                } else if (role.equals("representative")){
                    Role repRole = roleRepository.findByName(ERole.ROLE_REPRESENTATIVE.name())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(repRole);
                } else {
                    //logger.warn(signUpRequest.getUsername()+ " is trying for role "+role);
                    loggerController.RoleErrorTry(signUpRequest.getUsername(),role);

                    Role viewerRole = roleRepository.findByName(ERole.ROLE_REPRESENTATIVE.name())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(viewerRole);
                }

            });
        }

        employee.setRoles(roles);
        userRepository.save(employee);
        loggerController.RegistrationSuccess(employee.getUsername());
        return ResponseEntity.ok(new MessageResponse("Employee registered successfully!"));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {

        UserDetailsImpl userDetails1 = commonService.getDetails();
        //System.out.println("reched here");
        String uuid = "";
        if(userDetails1!=null)
             uuid = userDetails1.getUuid();
        else
            uuid= "";
        loggerController.Logout(uuid);
        //logger.info(uuid+ " User logging out");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
