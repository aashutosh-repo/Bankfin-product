package com.fin.bancs.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime lastLogin;
}
