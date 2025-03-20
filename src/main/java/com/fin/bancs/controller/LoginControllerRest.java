package com.fin.bancs.controller;

import com.fin.bancs.dto.UserDTO;
import com.fin.bancs.dto.UserResponseDTO;
import com.fin.bancs.entity.User;
import com.fin.bancs.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class LoginControllerRest {
    private UserService userService;

    @PostMapping("/verifyUser")
    public ResponseEntity<UserResponseDTO> verifyUser(@RequestBody UserDTO userDTO) {
        UserResponseDTO userResponseDTO= userService.getUserDetails(userDTO);
        if (userResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UserResponseDTO("404", "User Not Found")); // Custom error response
        }
        return ResponseEntity.ok(userResponseDTO);
    }

    // add request mapping for /access-denied

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";
    }

}