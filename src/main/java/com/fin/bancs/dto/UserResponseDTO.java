package com.fin.bancs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserResponseDTO {
    private String errorId;
    private String message;
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime lastLogin;
    public UserResponseDTO(String errorId, String message) {
        this.errorId = errorId;
        this.message = message;
        this.userId = null;
        this.userName = null;
    }
}

