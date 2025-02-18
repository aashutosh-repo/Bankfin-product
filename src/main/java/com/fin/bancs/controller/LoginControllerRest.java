package com.fin.bancs.controller;

import com.fin.bancs.dto.UserDTO;
import com.fin.bancs.entity.User;
import com.fin.bancs.repository.UserRepository;
import com.fin.bancs.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class LoginControllerRest {
    private UserService userService;

    @PostMapping("/verifyUser")
    public ResponseEntity<UserDTO> verifyUser(@RequestBody UserDTO userDTO) {
        User user= userService.getUserDetails(userDTO);
        UserDTO userResp = new UserDTO();
        userResp.setUsername(user.getUsername());
        userResp.setPassword(user.getPassword());
        return ResponseEntity.ok(userResp);
    }

    // add request mapping for /access-denied

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";
    }

}