package com.fin.bancs.services;


import com.fin.bancs.dto.UserDTO;
import com.fin.bancs.dto.UserResponseDTO;
import com.fin.bancs.entity.User;
import com.fin.bancs.error.CustomErrorMessage;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;


    public UserResponseDTO getUserDetails(UserDTO userDTO){
       User user = userRepository.findByUsernameAndPassword(userDTO.getUsername(),userDTO.getPassword()).orElseThrow(
               () -> new CustomErrorMessage(ErrorCode.USER_NOT_FOUND)
       );
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserName(user.getUsername());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setLastLogin(user.getLastLogin());
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
       return userResponseDTO;
    }
}
