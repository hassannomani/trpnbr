package com.nbr.trp.action.controller;

import com.nbr.trp.action.entity.Action;
import com.nbr.trp.action.service.ActionService;
import com.nbr.trp.user.entity.Role;
import com.nbr.trp.user.response.MessageResponse;
import com.nbr.trp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/action")
public class ActionController {
    @Autowired
    ActionService actionService;

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveAction(@RequestBody Action act) {
        try{
            Action action = actionService.save(act);
            String actionType = action.getActionType();
            if(actionType.equals("DENY"))
                userService.rejectRepuser(action.getReceiver());
//            else if(actionType.equals("BLOCK"))
//                userService.blockRepuser(action.getReceiver());

            return ResponseEntity.ok(action);
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/status/{username}")
    public ResponseEntity<?> getAction(@PathVariable String username) {
        try{
           List<Action>  action = actionService.getActionTrp(username);
            return ResponseEntity.ok(action);
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/message/{id}")
    public ResponseEntity<?> getMessage(@PathVariable String id) {
        try{
            Action  action = actionService.getAction(id);
            actionService.markRead(id);
            return ResponseEntity.ok(action);
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
