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
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;


    public UserResponseDTO getUserDetails(UserDTO userDTO){
       Optional<User> user = userRepository.findByUsernameAndPassword(userDTO.getUsername(),userDTO.getPassword());
       if(user.isPresent()) {
           UserResponseDTO userResponseDTO = new UserResponseDTO();
           userResponseDTO.setUserName(user.get().getUsername());
           userResponseDTO.setFirstName(user.get().getFirstName());
           userResponseDTO.setLastName(user.get().getLastName());
           userResponseDTO.setEmail(user.get().getEmail());
           userResponseDTO.setLastLogin(user.get().getLastLogin());
           user.get().setLastLogin(LocalDateTime.now());
           userRepository.save(user.get());
           return userResponseDTO;
       }
       return null;
    }
}
