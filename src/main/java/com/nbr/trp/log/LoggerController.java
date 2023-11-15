package com.nbr.trp.log;

import com.nbr.trp.user.controller.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {
    private static final Logger logger =  LoggerFactory.getLogger(LoggerController.class);

    public void Login(String uuid){
        logger.info(uuid + " User is logging in");
    }

    public void Logout(String uuid){
        logger.info(uuid + " User is logging out");
    }

    public void LoginFailedDeny(String uuid){
        logger.info(uuid + " Denied User is logging out");

    }

    public void LoginFailedSuspended(String uuid){
        logger.info(uuid + " Suspended User is logging out");
    }

    public void LoginFailedBlocked(String uuid){
        logger.info(uuid + " Blocked User is logging out");
    }

    public void LoginFailedUnApproved(String uuid){
        logger.info(uuid + " Un Approved User is logging out");
    }

    public void LoginError(String uuid){
        logger.info(uuid + " User is facing login error");
    }

    public void ReRegistration(String uuemail){
        logger.info(uuemail+ " is trying for re registration");
    }

    public void AdminRoleTry(String username){
        logger.warn(username+ " is trying for admin role ");
    }

    public void RoleErrorTry(String username, String role){
        logger.warn(username+ " is trying for role "+ role);
    }

    public void RegistrationSuccess(String username){
        logger.warn(username+ " has successfully registered");
    }
}
