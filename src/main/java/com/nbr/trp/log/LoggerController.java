package com.nbr.trp.log;

import com.nbr.trp.user.controller.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {
    private static final Logger logger =  LoggerFactory.getLogger(LoggerController.class);

    public void Login(String uuid, String ip){
        logger.info(uuid + " User is logging in from ip: "+ip);
    }

    public void Logout(String uuid){
        logger.info(uuid + " User is logging out");
    }

    public void LoginFailedDeny(String uuid, String ip){
        logger.info(uuid + " Denied User is logging out fromm ip:"+ip);

    }

    public void LoginFailedSuspended(String uuid, String ip){
        logger.info(uuid + " Suspended User is logging out from ip: "+ip);
    }

    public void LoginFailedBlocked(String uuid, String ip){
        logger.info(uuid + " Blocked User is logging out from ip: "+ip);
    }

    public void LoginFailedUnApproved(String uuid, String ip){
        logger.info(uuid + " Un Approved User is logging out from ip "+ip);
    }

    public void LoginError(String uuid, String ip){
        logger.info(uuid + " User is facing login error from ip: "+ip);
    }

    public void ReRegistration(String uuemail, String ip){
        logger.info(uuemail+ " is trying for re registration from ip: "+ip);
    }

    public void AdminRoleTry(String username, String ip){
        logger.warn(username+ " is trying for admin role from ip: "+ip);
    }

    public void RoleErrorTry(String username, String role, String ip){
        logger.warn(username+ " is trying for role "+ role+" from ip: "+ip);
    }

    public void RegistrationSuccess(String username, String ip){
        logger.info(username+ " has successfully registered from ip: "+ip);
    }

    public void BillSubmit(String username, String role){
        logger.info(username+ " with role "+role+ " has successfully submitted bill");
    }

    public void ErrorHandler(Exception e){
        logger.error("Error Occurred "+e);
    }

    public void BillCheck(String role, String tin){
        logger.info(tin+ " with role "+role+ " is checking bill");
    }

    public void UnAuthorized(String ip){
        logger.error("Unauthorized attempt from "+ip);
    }

    public void IncomingRequest(String ip, String url){
        logger.info("Incoming request from "+ip+ " for accessing the url "+url);
    }

    public void UserAddition(String tin, String role, String ip){
        logger.info("User with tin "+tin+ " and role "+role+" is being added by admin from ip: "+ip);
    }

    public void UserAdditionFailed(String tin, String role, String ip){
        logger.info("User with tin "+tin+ " and role "+role+" is being attempted from ip: "+ip);
    }

    public void UserApproval(String uuid, String ip){
        logger.info("User with tin/uuid "+uuid+ " is being added by admin from ip: "+ip);
    }

    public void UserRejection(String uuid, String ip){
        logger.info("User with tin/uuid "+uuid+ " is being rejected by admin from ip: "+ip);
    }

    public void ListGeneration(String uname_id, String list, String role, String ip){
        logger.info("User with tin/uuid "+uname_id+ "and role "+role+ " is retrieving list of "+list+ " from ip: "+ip);
    }

    public void ActionSave(String uname_id, String type, String ip){
        logger.info("User with tin/uuid "+uname_id+ " faced action "+type+ " by admin from ip: "+ip);
    }

}