package com.fin.bancs.services;


import com.fin.bancs.dto.UserDTO;
import com.fin.bancs.entity.User;
import com.fin.bancs.error.CustomErrorMessage;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;


    public User getUserDetails(UserDTO userDTO){
       return userRepository.findByUsernameAndPassword(userDTO.getUsername(),userDTO.getPassword()).orElseThrow(
               () -> new CustomErrorMessage(ErrorCode.USER_NOT_FOUND)
       );
    }
}
